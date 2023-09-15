package com.bean.beanapiinterface;

import com.bean.beanapiclientsdk.client.CommonApiClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class BeanapiInterfaceApplicationTests {

    @Resource
    private CommonApiClient beanApiClient;


}
