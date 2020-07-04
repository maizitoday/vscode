package com.maizi;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-02-19 11:53:49
 * @LastEditTime: 2020-02-19 11:57:59
 * @LastEditors: 麦子
 */

public class Client {
    public static void main(String[] args) {
        Component component = new ConcreteComponent(); 
        // 第一次修饰
        component = new ConcreteDecorator1(component); 
        // 第二次修饰
        component = new ConcreteDecorator2(component); 
        // 修饰后运行
        component.operate();
    }
}