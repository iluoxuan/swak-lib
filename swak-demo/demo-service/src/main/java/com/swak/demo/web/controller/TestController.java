package com.swak.demo.web.controller;

import com.swak.lib.client.entity.ApiRes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ljq
 * @date: 2024/10/24
 */
@RequestMapping("/test")
@RestController
public class TestController {


    @GetMapping("/testApi")
    public ApiRes<String> testApi(){

        return ApiRes.success("ok");
    }
}
