package com.bean.beanapiinterface.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.bean.beanapiclientsdk.model.City;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 天气预报接口
 * @author sami
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {


    @PostMapping("/dailyWeather")
    public String getWeather(@RequestBody City city) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("city", city.getCityName());
        HttpResponse response = HttpRequest.post("https://tenapi.cn/v2/weather")
                .form(paramMap)
                .execute();
        String body = response.body();
        return body;
    }

    /**
     * 用于发布接口前，测试调用
     * @return
     */
    @GetMapping("/testMethod")
    public String test() {
        return "接口运行正常";
    }
}
