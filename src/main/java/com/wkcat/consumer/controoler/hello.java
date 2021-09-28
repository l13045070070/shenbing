package com.wkcat.consumer.controoler;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Auther 刘爽
 * @Date 2021/6/1
 */
@RestController

public class hello {
    @Autowired
    RestTemplate res;

    @RequestMapping("/hello")
    public String hello() {

       // return "调用"+res.getForEntity("http://localhost:8080/sever/hello" ,String.class).getBody();
        return "consumer调用微服务  "+res.getForEntity("http://PROVIDER/sever/hello" ,String.class).getBody();


    }
@RequestMapping("/hystrix")
@HystrixCommand(fallbackMethod = "error",ignoreExceptions = Exception.class

        )
    public String hystrix() {
        return res.getForEntity("http://PROVIDER/sever/hello",String.class).getBody();
    }

    @RequestMapping("/hystrix1")
    public String hys() throws ExecutionException, InterruptedException {
        hystrix hystrix = new hystrix(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(" ")), res);
    //同步调用 ：执行 方法 返回调用的结果;
      //  return hystrix.execute();


        //异步调用 返回一个 未来的结果；
        Future<String> queue = hystrix.queue();
        //阻塞 方法  会一直等 获取结果
        return queue.get();


    }


  public String  error( Throwable t
    ) {
      System.out.println(t.getMessage());
      return "error:调用远程服务失败";

    }

}
