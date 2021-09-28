package com.wkcat.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/*
@SpringBootApplication
@EnableEurekaClient//开启eureka客户端支持 基本等价enableDiscoveryClient
@EnableCircuitBreaker//开启注解断路器的功能*/
@SpringCloudApplication//此注解等价以上三个
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
        System.out.println("服务调用者启动");

    }

}
