package com.maizi;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-02-19 11:53:11
 * @LastEditTime: 2020-02-19 11:55:26
 * @LastEditors: 麦子
 */

public class ConcreteDecorator2 extends Decorator {
    // 定义被修饰者
    public ConcreteDecorator2(Component _component) {
        super(_component);
    }

    // 定义自己的修饰方法
    private void method2() {
        System.out.println("method2修饰");
    }

    // 重写父类的Operation方法
    public void operate() {
        super.operate();
        this.method2();
    }
}