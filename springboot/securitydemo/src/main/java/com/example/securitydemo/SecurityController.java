package com.example.securitydemo;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-06-06 23:07:25
 * @LastEditTime: 2019-06-11 16:21:17
 * @LastEditors: 麦子
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class SecurityController {

    @ResponseBody
    @GetMapping(value="/one")
    public String showOne() {
        return "one";
    }
    
    @GetMapping(value="/myLogin")
    public String userLogin() {
        return "userLogin";
    }
}