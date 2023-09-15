package com.bean.beanapiclientsdk.client;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.bean.beanapiclientsdk.model.User;

import java.nio.charset.StandardCharsets;

/**
 * @author sami
 */
public class NameApiClient extends CommonApiClient{

    public NameApiClient(String accessKey, String secretKey) {
        super(accessKey, secretKey);
    }

    public String getUsernameByPost(User user) {
        String json = JSONUtil.toJsonStr(user);
        return HttpRequest.post(GATEWAY_HOST + "/api/name/user")
                .charset(StandardCharsets.UTF_8)
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute().body();
    }

    /**
     * 用于发布接口前，测试调用
     * @return
     */
    public String test() {
        return "接口运行正常";
    }
}
