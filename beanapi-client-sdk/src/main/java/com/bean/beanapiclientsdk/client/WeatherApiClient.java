package com.bean.beanapiclientsdk.client;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.bean.beanapiclientsdk.model.City;

import java.nio.charset.StandardCharsets;

/**
 * 天气预报接口下线维护中
 * @author sami
 */
public class WeatherApiClient extends CommonApiClient {
    public WeatherApiClient(String accessKey, String secretKey) {
        super(accessKey, secretKey);
    }


    /**
     * 获取天气
     */
    public String getWeather(City city) {
        String jsonStr = JSONUtil.toJsonStr(city);
        return HttpRequest.post(GATEWAY_HOST + "/api/interface/weather/dailyWeather")
                .charset(StandardCharsets.UTF_8)
                .addHeaders(getHeaderMap(jsonStr))
                .execute().body();
    }

    /**
     * 用于发布接口前，测试调用
     *
     * @return
     */
    public String testMethod() {
        return HttpRequest.get(GATEWAY_HOST + "/api/interface/weather/testMethod")
                .addHeaders(getHeaderMap(""))
                .execute().body();
    }
}
