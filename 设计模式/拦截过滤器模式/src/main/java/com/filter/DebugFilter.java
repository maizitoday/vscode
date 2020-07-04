package com.filter;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-04-11 15:29:59
 * @LastEditTime: 2020-04-11 15:34:30
 * @LastEditors: 麦子
 */

public class DebugFilter implements Filter {

    @Override
    public void execute(String request) {

        System.out.println("request log: " + request);
        
    }
    
}