package com.swak.demo.web.controller;

import com.swak.lib.client.entity.ApiRes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author: ljq
 * @date: 2024/10/24
 */
@RequestMapping("/test")
@RestController
public class TestController {


    @GetMapping("/testApi")
    public ApiRes<Date> testApi(){
        return ApiRes.success(new Date());
    }
}
