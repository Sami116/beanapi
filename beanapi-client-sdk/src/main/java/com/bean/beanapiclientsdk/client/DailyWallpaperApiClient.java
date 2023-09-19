package com.bean.beanapiclientsdk.client;

import cn.hutool.http.HttpRequest;

/**
 * @author sami
 */
public class DailyWallpaperApiClient extends CommonApiClient{
    public DailyWallpaperApiClient(String accessKey, String secretKey) {
        super(accessKey, secretKey);
    }

    /**
     * 获取每日壁纸
     * @return
     */
    public String getDailyWallpaperUrl(){
        return HttpRequest.post(GATEWAY_HOST+"/api/interface/daily/wallpaper")
                .addHeaders(getHeaderMap(""))
                .execute().body();
    }

    /**
     * 用于发布接口前，测试调用
     * @return
     */
    public String testMethod() {
        return HttpRequest.get(GATEWAY_HOST+"/api/interface/daily/testMethod")
                .addHeaders(getHeaderMap(""))
                .execute().body();
    }


}
