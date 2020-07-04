package com.maizi;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-02-19 11:52:24
 * @LastEditTime: 2020-02-19 11:55:11
 * @LastEditors: 麦子
 */

public class ConcreteDecorator1 extends Decorator {
    
    // 定义被修饰者
    public ConcreteDecorator1(Component _component) {

        super(_component);
    }

    // 定义自己的修饰方法
    private void method1() {
        System.out.println("method1 修饰");
    }

    // 重写父类的Operation方法
    public void operate() {
        this.method1();
        super.operate();
    }
}