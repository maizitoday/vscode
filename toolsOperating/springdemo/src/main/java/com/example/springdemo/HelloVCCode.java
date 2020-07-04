package com.example.springdemo;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @LastEditors: 麦子
 * @Date: 2019-05-05 17:50:23
 * @LastEditTime: 2019-05-06 04:28:49
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloVCCode {

    @RequestMapping("/vscode")
    public String show() {
        String catt = "catting";
        return catt;
        
    }
    
}