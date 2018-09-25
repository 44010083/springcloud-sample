package com.example.demo.api;


import com.example.demo.service.HelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "01.hello", tags = {"hello"})
@RestController

public class RestHelloController {
    @Autowired
    private HelloClient helloClient;

    @ApiOperation(value = "hello", notes = "hello")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return helloClient.hello();
    }
}
