package com.client.config.clientconfig;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-05-17 00:46:11
 * @LastEditTime: 2019-05-17 00:46:11
 * @LastEditors: 麦子
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping("/getmessage")
    public String message() {
        return "hello world";
    }
    
}