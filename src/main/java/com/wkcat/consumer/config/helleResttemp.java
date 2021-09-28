package com.wkcat.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther 刘爽
 * @Date 2021/6/1
 */
@Configuration
public class helleResttemp {
    @LoadBalanced
    @Bean
    public RestTemplate template(){
        return new RestTemplate();
    }
}
