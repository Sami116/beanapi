package com.bean.beanapiclientsdk;

import com.bean.beanapiclientsdk.client.CommonApiClient;
import com.bean.beanapiclientsdk.client.DailyWallpaperApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sami
 */
@Configuration
@ConfigurationProperties("beanapi.client")
@Data
@ComponentScan
public class BeanApiClientConfig {

    private String accessKey;
    private String secretKey;

    @Bean
    public CommonApiClient dailyWallpaperApiClient() {
        return new CommonApiClient(accessKey, secretKey);
    }

}
