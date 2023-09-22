package com.bean.beanapiclientsdk.client;

import cn.hutool.http.HttpRequest;

/**
 * @author sami
 */
public class HistoryApiClient extends CommonApiClient{
    public HistoryApiClient(String accessKey, String secretKey) {
        super(accessKey, secretKey);
    }

    public String todayOnHistory() {
        return HttpRequest.get(GATEWAY_HOST + "/api/interface/history/todayOnHistory")
                .addHeaders(getHeaderMap(""))
                .execute().body();
    }

    /**
     * 用于发布接口前，测试调用
     *
     * @return
     */
    public String testMethod() {
        return HttpRequest.get(GATEWAY_HOST + "/api/interface/history/testMethod")
                .addHeaders(getHeaderMap(""))
                .execute().body();
    }

}
