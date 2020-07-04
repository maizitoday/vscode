package com.maizi;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-02-20 12:05:45
 * @LastEditTime: 2020-02-20 12:06:36
 * @LastEditors: 麦子
 */

 /**
  * 源角色也是已经在服役状态
  */
public class Adaptee { 
    // 原有的业务逻辑
    public void doSomething() {
        System.out.println("I'm kind of busy,leave me alone,pls!");
    }
}