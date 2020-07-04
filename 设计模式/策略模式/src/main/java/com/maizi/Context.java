package com.maizi;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-02-29 16:24:03
 * @LastEditTime: 2020-02-29 16:24:41
 * @LastEditors: 麦子
 */

public class Context { // 抽象策略
    private Strategy strategy = null;

    // 构造函数设置具体策略
    public Context(Strategy _strategy) {

        this.strategy = _strategy;
    }

    // 封装后的策略方法
    public void doAnythinig() {

        this.strategy.doSomething();
    }
}