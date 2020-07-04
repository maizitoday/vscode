package com.filter;
/*
 * @Description: 创建 Target， 这个类是具体的业务处理类
 * @Author: 麦子
 * @Date: 2020-04-11 15:34:43
 * @LastEditTime: 2020-04-13 15:19:10
 * @LastEditors: 麦子
 */
public class Target {

    public void execute(String request) {

        System.out.println("真正业务处理类： Executing request: " + request);
    
    }
}