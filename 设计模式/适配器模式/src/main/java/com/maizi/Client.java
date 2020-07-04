package com.maizi;

/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-02-19 11:29:25
 * @LastEditTime: 2020-02-20 12:08:42
 * @LastEditors: 麦子
 */
public class Client {
    public static void main(String[] args) {
        // 原有的业务逻辑
        Target target = new ConcreteTarget();
        target.request();
        // 现在增加了适配器角色后的业务逻辑
        Target target2 = new Adapter();
        target2.request();
    }
}
