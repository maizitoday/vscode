package com.maizi;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-02-20 12:04:29
 * @LastEditTime: 2020-02-20 12:10:05
 * @LastEditors: 麦子
 */

 /**
  * 目标角色的实现类
  */
public class ConcreteTarget implements Target {
    public void request() {
        System.out.println("if you need any help,pls call me!");
    }
}