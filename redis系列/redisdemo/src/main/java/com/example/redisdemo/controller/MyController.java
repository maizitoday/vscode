package com.example.redisdemo.controller;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-09-09 23:09:49
 * @LastEditTime: 2020-02-29 18:08:03
 * @LastEditors: 麦子
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MyController {
    

    @Autowired
    private StringRedisTemplate redisTemplate;
   
    @GetMapping(value = "showMsg")
    public String showMsg() {
        String val = redisTemplate.opsForValue().get("maizi");
        System.out.println(val);
        return "hello controller";
    }
}