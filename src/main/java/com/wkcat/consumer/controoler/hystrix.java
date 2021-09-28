package com.wkcat.consumer.controoler;

import com.netflix.hystrix.HystrixCommand;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther 刘爽
 * @Date 2021/7/3
 * 测试 hystrix  自定义熔断 降级
 */
public class hystrix extends HystrixCommand<String> {

    private RestTemplate restTemplate;

    public hystrix(Setter setter, RestTemplate restTemplate) {
        super(setter);
        this.restTemplate = restTemplate;

    }

    @Override
    protected String run() throws Exception {
        //调用远程服务的地方
        return restTemplate.getForEntity("http://PROVIDER/sever/hello", String.class).getBody();
    }

    @Override
    public String getFallback() {
        return "error2222";
    }

}
