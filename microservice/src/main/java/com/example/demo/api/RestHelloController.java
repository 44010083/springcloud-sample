package com.example.demo.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "01.hello", tags = {"hello"})
@RestController

public class RestHelloController {
    @ApiOperation(value = "hello", notes = "hello")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    //@HystrixCommand(fallbackMethod="helloFallbackMethod")
    public String sayHello() {
        return "hello";
    }
}

