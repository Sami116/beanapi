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

    @Test
    void contextLoads() {
        String result = beanApiClient.getNameByGet("sami");
        User user = new User();
        user.setUsername("sami");
        String usernameByPost = beanApiClient.getUsernameByPost(user);
        System.out.println(result);
        System.out.println(usernameByPost);
    }

}
