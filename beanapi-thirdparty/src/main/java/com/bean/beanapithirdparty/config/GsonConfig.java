package com.bean.beanapithirdparty.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * json序列化和反序列化工具类配置
 * @author sami
 */
@Configuration
public class GsonConfig {


    @Bean
    public Gson gson(){
        return new Gson();
    }
}
