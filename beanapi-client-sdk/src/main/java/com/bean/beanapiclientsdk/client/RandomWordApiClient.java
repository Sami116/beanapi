package com.bean.beanapiclientsdk.client;

import cn.hutool.http.HttpRequest;

/**
 * @author sami
 */
public class RandomWordApiClient extends CommonApiClient {
    public RandomWordApiClient(String accessKey, String secretKey) {
        super(accessKey, secretKey);
    }

    /**
     * 获取随机文本
     *
     * @return
     */
    public String getRandomWork() {
        return HttpRequest.get(GATEWAY_HOST + "/api/interface/random/word")
                .addHeaders(getHeaderMap(""))
                .execute().body();
    }

    /**
     * 获取随机动漫图片
     *
     * @return
     */
    public String getRandomImageUrl() {
        return HttpRequest.post(GATEWAY_HOST + "/api/interface/random/image")
                .addHeaders(getHeaderMap(""))
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
