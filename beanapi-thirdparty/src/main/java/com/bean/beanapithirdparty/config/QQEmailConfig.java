package com.bean.beanapithirdparty.config;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author sami
 */
@Configuration
@ConfigurationProperties(prefix = "msm")
@Data
public class QQEmailConfig implements InitializingBean {

    private String email;

    private String host;

    private String port;

    private String password;

    public static String EMAIL;
    public static String HOST;
    public static String PORT;
    public static String PASSWORD;

    @Override
    public void afterPropertiesSet() throws Exception {
        EMAIL = email;
        HOST = host;
        PORT = port;
        PASSWORD = password;
    }

}