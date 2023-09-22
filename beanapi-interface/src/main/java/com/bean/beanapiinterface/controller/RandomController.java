package com.bean.beanapiinterface.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.bean.beanapiinterface.entity.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/random")
public class RandomController {

    @GetMapping("/word")
    public String getRandomWord(){
        HttpResponse response = HttpRequest.get("https://tenapi.cn/v2/yiyan")
                .execute();
        return response.body();
    }

    @PostMapping("/image")
    public String getRandomImageUrl(){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("format","json");
        HttpResponse response = HttpRequest.post("https://tenapi.cn/v2/acg")
                .form(paramMap)
                .execute();
        String body = response.body();
        ImageResponse imageResponse = JSONUtil.toBean(body, ImageResponse.class);
        return imageResponse.getData().getUrl();
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
