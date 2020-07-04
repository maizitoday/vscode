package com.example.logdemo.entity;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-05-25 20:59:23
 * @LastEditTime: 2019-08-10 12:15:12
 * @LastEditors: 麦子
 */

public class Student {
    public String name; 
    public int age;     

    public static void main(String[] args) {
        int count = 0; 
        for (int i = 0; i < 10; i++) {
            count = count + i;
            System.out.println(count);
        }
    }
    
}