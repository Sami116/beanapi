package com.bean.beanapiinterface.controller;


import com.bean.beanapiclientsdk.model.User;
import com.bean.beanapiclientsdk.utils.SignUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 名称 API
 *
 * @author Sami
 */
@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/get")
    public String getNameByGet(String name) {

        return "GET 你的名字是" + name;
    }

    @PostMapping("/post")
    public String getNameByPost(@RequestParam String name) {
        return "POST 你的名字是" + name;
    }

    @PostMapping("/user")
    public String getUsernameByPost(@RequestBody User user, HttpServletRequest request) {
        String accessKey = request.getHeader("accessKey");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        String body = request.getHeader("body");
        String sign = request.getHeader("sign");
        // todo 实际情况应该是去数据库中查询是否已分配给用户 已在网关实现
        if (!accessKey.equals("sami")) {
            throw new RuntimeException("无权限");
        }
        if (Long.parseLong(nonce) > 10000) {
            throw new RuntimeException("无权限");
        }
        // todo 时间和当前时间不能超过5分钟 已在网关实现
//        if (timestamp) {
//
//        }
        // todo 实际情况中应该是从数据库中查询到secretKey 已在网关实现
        String serverSign = SignUtil.genSign(body, "12345678");
        if (!sign.equals(serverSign)) {
            throw new RuntimeException("无权限");
        }
        // todo 调用次数加 1 已在网关实现
        return "POST 用户名是" + user.getUsername();
    }

}
