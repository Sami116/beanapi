package com.bean.beanapiinterface;

import com.bean.beanapiclientsdk.client.BeanApiClient;
import com.bean.beanapiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class BeanapiInterfaceApplicationTests {

    @Resource
    private BeanApiClient beanApiClient;


}
