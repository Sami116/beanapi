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

/**
 * 每日壁纸API
 *
 * @author sami
 */

@RestController
@RequestMapping("/daily")
public class DailyWallPaperController {

    @PostMapping("/wallPaper")
    public String getDailyWallPaperUrl() {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("format", "json");
        HttpResponse response = HttpRequest.post("https://tenapi.cn/v2/bing")
                .form(paramMap)
                .execute();
        String body = response.body();
        ImageResponse imageResponse = JSONUtil.toBean(body, ImageResponse.class);

        return imageResponse.getData().getUrl();

    }
}
