package com.example.demo.service;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//调用DEMO微服务的hello api
@FeignClient("DEMO")
public interface HelloClient {
    // 3个坑：
    // 1. @GetMapping不支持;
    // 2. @PathVariable得设置value;
    //3.要Headers
    @Headers("Content-Type: application/json")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello();
}
