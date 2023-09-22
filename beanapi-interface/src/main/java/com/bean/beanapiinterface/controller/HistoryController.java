package com.bean.beanapiinterface.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sami
 */

@RestController
@RequestMapping("/history")
public class HistoryController {



    @GetMapping("/todayOnHistory")
    public String todayOnHistory() {
        HttpResponse response = HttpRequest.get("https://tenapi.cn/v2/history")
                .execute();
        return response.body();
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
