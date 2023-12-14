package com.bean.beanapi.utils;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * @author sami
 */

@Configuration
@ConfigurationProperties(prefix = "aliyun.oss.file")
@Data
public class OOSConstantPropertiesUtils implements InitializingBean {


    private String endpoint;

    private String keyId;

    private String keySecret;

    private String bucketName;

    //定义公开静态常量
    public static String END_POIND;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;


    @Override
    public void afterPropertiesSet() throws Exception {
        END_POIND = endpoint;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
