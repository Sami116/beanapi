package com.bean.beanapigateway.filter;

import com.bean.beanapiclientsdk.utils.SignUtil;
import com.bean.beanapicommon.model.entity.InterfaceInfo;
import com.bean.beanapicommon.model.entity.User;
import com.bean.beanapicommon.model.vo.UserInterfaceInfoMessage;
import com.bean.beanapicommon.service.ApiBackendService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.reactivestreams.Publisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.bean.beanapicommon.constant.RabbitmqConstant.EXCHANGE_INTERFACE_CONSISTENT;
import static com.bean.beanapicommon.constant.RabbitmqConstant.ROUTING_KEY_INTERFACE_CONSISTENT;

/**
 * 自定义全局过滤器
 */
@Slf4j
@Component
public class InterfaceInvokeFilter implements GatewayFilter, Ordered {

    @DubboReference
    private ApiBackendService apiBackendService;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

//    private static List<String> IP_WHITE_LIST = Arrays.asList("127.0.0.1");

    private static final String INTERFACE_HOST = "http://localhost:8123";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //1.打上请求日志
        //2.黑白名单(可做可不做)
        //3.用户鉴权(API签名认证)
        //4.远程调用判断接口是否存在以及获取调用接口信息
        //5.判断接口是否还有调用次数，如果没有则直接拒绝
        //6.发起接口调用
        //7.获取响应结果，打上响应日志
        //8.接口调用次数+1


        // 请求日志
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String path = INTERFACE_HOST + request.getPath().value();
        String method = request.getMethod().toString();

        log.info("InterfaceInvokeFilter");
        log.info("请求的唯一标识: " + request.getId());
        log.info("请求路径: " + request.getPath().value());
        log.info("请求方法: " + request.getMethod());
        log.info("请求参数: " + request.getQueryParams());
        String sourceAddress = request.getRemoteAddress().getHostString();
        log.info("请求来源地址: " + sourceAddress);


//        // 访问控制 - 黑白名单 可做可不做
//        if (!IP_WHITE_LIST.contains(sourceAddress)) {
//            response.setStatusCode(HttpStatus.FORBIDDEN);
//            return response.setComplete();
//        }
        // 用户鉴权 (判断 ak, sk 是否合法)
        HttpHeaders headers = request.getHeaders();
        String accessKey = headers.getFirst("accessKey");
        String nonce = headers.getFirst("nonce");
        String timestamp = headers.getFirst("timestamp");
        String body = headers.getFirst("body");
        String sign = headers.getFirst("sign");


        // 请求时间和当前时间不能超过5分钟,也就是说请求有效期 5 分钟
        Long currentTime = System.currentTimeMillis() / 1000;
        Long FIVE_MINUTES = 5L * 60;
        if ((currentTime - Long.parseLong(timestamp)) >= FIVE_MINUTES) {
            return handleNoAuth(response);
        }
        //防重放，使用redis存储请求的唯一标识，随机时间，并定时淘汰，那使用什么redis结构来实现嗯？
        //既然是单个数据，这样用string结构实现即可
        Boolean success = stringRedisTemplate.opsForValue().setIfAbsent(nonce, "1", 5, TimeUnit.MINUTES);
        if (success == null) {
            log.error("触发放重复机制，随机数存储失败！！！！");
            return handleNoAuth(response);
        }


        // //根据accessKey获取secretKey，判断accessKey是否合法
        User invokeUser = null;
        try {
            invokeUser = apiBackendService.getInvokeUser(accessKey);
        } catch (Exception e) {
            log.error("getInvokeUser error", e);
        }
        if (invokeUser == null) {  // 非法accessKey，无权调用接口
            return handleNoAuth(response);
        }

        // 从数据库中查询到secretKey
        String secretKey = invokeUser.getSecretKey();
        // 生成服务端签名
        String serverSign = SignUtil.genSign(body, secretKey);
        // 比对用户端签名和服务端签名
        if (sign == null || !sign.equals(serverSign)) {
            return handleNoAuth(response);
        }


        // 判断请求的接口是否存在,以及请求方式是否匹配
        InterfaceInfo interfaceInfo = null;
        try {
            interfaceInfo = apiBackendService.getInterfaceInfo(path, method);
        } catch (Exception e) {
            log.error("远程调用获取被调用接口信息失败!!!", e);
        }
        if (interfaceInfo == null) { // 请求的接口不存在或请求方式不匹配
            log.error("请求的接口不存在或请求方式不匹配!!!");
            return handleNoAuth(response);
        }
        // todo 是否还有调用次数
        // 判断接口是否还有调用次数，并且统计接口调用，将二者转化成原子性操作(backend本地服务的本地事务实现)，解决二者数据一致性问题
        boolean result = false;
        try {
            result = apiBackendService.invokeCount(interfaceInfo.getId(), invokeUser.getId());
        } catch (Exception e) {
            log.error("统计接口出现问题或者用户恶意调用不存在的接口");
            e.printStackTrace();
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return response.setComplete();
        }

        if (!result) {
            log.error("接口剩余调用次数不足");
            return handleNoAuth(response);
        }

        //6.发起接口调用，网关路由实现
        Mono<Void> filter = chain.filter(exchange);
        // 请求转发，调用模拟接口 + 响应日志
        return handleResponse(exchange, chain, interfaceInfo.getId(), invokeUser.getId());
    }


    public Mono<Void> handleResponse(ServerWebExchange exchange, GatewayFilterChain chain, long interfaceInfoId, long userId) {
        try {
            ServerHttpResponse originalResponse = exchange.getResponse();
            // 缓存的数据
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();
            // 拿到响应码
            HttpStatus statusCode = originalResponse.getStatusCode();

            if (statusCode == HttpStatus.OK) {
                // 装饰，增强类的能力
                ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {

                    @Override
                    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                        log.info("body instanceof Flux: {}", (body instanceof Flux));
                        if (body instanceof Flux) {
                            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                            // 往返回值里写数据
                            // 拼接字符串
                            return super.writeWith(
                                    fluxBody.map(dataBuffer -> {
                                        byte[] content = new byte[dataBuffer.readableByteCount()];
                                        dataBuffer.read(content);
                                        DataBufferUtils.release(dataBuffer);//释放掉内存

                                        //7.获取响应结果，打上响应日志
                                        // 构建日志
                                        log.info("接口调用响应状态码：" + originalResponse.getStatusCode());
                                        //responseBody
                                        String responseBody = new String(content, StandardCharsets.UTF_8);

                                        //8.接口调用失败，利用消息队列实现接口统计数据的回滚；因为消息队列的可靠性所以我们选择消息队列而不是远程调用来实现
                                        if (!(originalResponse.getStatusCode() == HttpStatus.OK)) {
                                            log.error("接口异常调用-响应体:" + responseBody);
                                            UserInterfaceInfoMessage vo = new UserInterfaceInfoMessage(userId, interfaceInfoId);
                                            rabbitTemplate.convertAndSend(EXCHANGE_INTERFACE_CONSISTENT, ROUTING_KEY_INTERFACE_CONSISTENT, vo);
                                        }
                                        return bufferFactory.wrap(content);
                                    }));
                        } else {
                            log.error("<--- {} 响应code异常", getStatusCode());
                        }
                        return super.writeWith(body);
                    }
                };
                // 设置 response 对象为装饰过的
                return chain.filter(exchange.mutate().response(decoratedResponse).build());
            }
            return chain.filter(exchange);//降级处理返回数据
        } catch (Exception e) {
            log.error("网关处理响应异常" + e);
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return -2;
    }

    public Mono<Void> handleNoAuth(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }

}
