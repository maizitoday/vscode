package com.maizi;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-02-20 12:03:28
 * @LastEditTime: 2020-02-20 12:04:14
 * @LastEditors: 麦子
 */

/**
 * 目标角色是一个已经在正式运行的角色，你不可能去修改角色中的方法，你能做的就是
 * 如何去实现接口中的方法，而且通常情况下，目标角色是一个接口或者是抽象类，一般不会 是实现类。
 */
public interface Target { // 目标角色有自己的方法
    public void request();
}