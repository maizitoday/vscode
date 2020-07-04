package com.maizi;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-02-27 12:28:58
 * @LastEditTime: 2020-02-29 16:25:06
 * @LastEditors: 麦子
 */

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        // 声明一个具体的策略
        Strategy strategy = new ConcreteStrategy1();
        // 声明上下文对象
        Context context = new Context(strategy);
        // 执行封装后的方法
        context.doAnythinig();
    }
}
