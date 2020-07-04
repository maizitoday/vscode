package com.example.demo.controller;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2017-11-16 19:14:18
 * @LastEditTime: 2019-06-06 15:12:12
 * @LastEditors: 麦子
 */

import java.util.Date;

import com.example.demo.domain.Employee;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Created with IntelliJ IDEA.
 * Date: 2017/11/16
 * Time: 10:32
 * Email: hyf_spring@163.com
 *
 * @author huyunfan
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @GetMapping("/greeting")
    public String greeting() {
        return "Hello,World!";
    }



    public static void main(String[] args) {
        System.out.println("开始测试jwt加密");

        String token = Jwts.builder()
                .setSubject(new Employee().toString())
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, "PrivateSecret")
                .compact();
        System.out.println(token);


        System.out.println("开始测试jwt Subject 解密");
        String user = Jwts.parser()
                    .setSigningKey("PrivateSecret")
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        System.out.println(user);

        System.out.println("显示所有的信息");
        Object object = Jwts.parser()
                    .setSigningKey("PrivateSecret")
                    .parseClaimsJws(token)
                    .getBody();
        System.out.println(object);  

    }
}
