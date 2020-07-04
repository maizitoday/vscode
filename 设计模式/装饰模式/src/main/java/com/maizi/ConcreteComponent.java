package com.maizi;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-02-19 11:51:06
 * @LastEditTime: 2020-02-19 11:51:27
 * @LastEditors: 麦子
 */

public class ConcreteComponent extends Component { 
    // 具体实现
    @Override
    public void operate() {

        System.out.println("do Something");
    }
}