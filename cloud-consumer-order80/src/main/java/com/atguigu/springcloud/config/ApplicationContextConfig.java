package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean //相當於 applicationContext.xml <bean id="" class="">
    @LoadBalanced // 配合 http://CLOUD-PAYMENT-SERVICE 加上負載均衡機制
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

