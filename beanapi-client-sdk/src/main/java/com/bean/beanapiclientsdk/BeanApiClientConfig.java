package com.bean.beanapiclientsdk;

import com.bean.beanapiclientsdk.client.BeanApiClient;
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
    public BeanApiClient beanApiClient() {
        return new BeanApiClient(accessKey, secretKey);
    }


}
