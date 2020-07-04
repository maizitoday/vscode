package com.filter;
/*
 * @Description: 创建实体过滤器
 * @Author: 麦子
 * @Date: 2020-04-11 15:28:46
 * @LastEditTime: 2020-04-13 15:18:40
 * @LastEditors: 麦子
 */
public class AuthenticationFilter implements Filter{

    @Override
    public void execute(String request) {
         
        System.out.println("Authenticating request: " + request);

    }
    
}