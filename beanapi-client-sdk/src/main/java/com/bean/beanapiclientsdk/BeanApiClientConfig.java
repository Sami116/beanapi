package com.bean.beanapiclientsdk;

import com.bean.beanapiclientsdk.client.CommonApiClient;
import com.bean.beanapiclientsdk.client.DailyWallpaperApiClient;
import com.bean.beanapiclientsdk.client.NameApiClient;
import com.bean.beanapiclientsdk.client.RandomWordApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.security.PublicKey;

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
    public DailyWallpaperApiClient dailyWallpaperApiClient() {
        return new DailyWallpaperApiClient(accessKey, secretKey);
    }

}
