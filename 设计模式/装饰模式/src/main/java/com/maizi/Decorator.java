package com.maizi;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-02-19 11:51:38
 * @LastEditTime: 2020-02-19 11:55:40
 * @LastEditors: 麦子
 */

public abstract class Decorator extends Component {
    private Component component = null;

    // 通过构造函数传递被修饰者
    public Decorator(Component _component) {
        this.component = _component;
    }

    // 委托给被修饰者执行
    @Override
    public void operate() {
        this.component.operate();
    }
}