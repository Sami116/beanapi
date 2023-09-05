package com.bean.beanapigateway;

import com.bean.beanapi.provider.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class
})
@EnableDubbo
@Service
public class BeanapiGatewayApplication {

    @DubboReference
    private DemoService demoService;

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(BeanapiGatewayApplication.class, args);
//        BeanapiGatewayApplication application = context.getBean(BeanapiGatewayApplication.class);
//        String result = application.doSayHello("sami");
//        System.out.println("result: " + result);

    }


    public String doSayHello(String name) {
        return demoService.sayHello(name);
    }

}
