package com.muban;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-04-11 11:26:55
 * @LastEditTime: 2020-04-11 11:42:32
 * @LastEditors: 麦子
 */

public abstract class AbstractClass {

    // 基本方法
    protected abstract void doSomething();

    // 基本方法
    protected abstract void doAnything();

    // 模板方法
    public final void templateMethod() {
        /* * 调用基本方法，完成相关的逻辑 */
        if (this.check()) {
            this.doAnything();
            this.doSomething();
        }else{
            System.out.println("子类 重写 check ");
        }
    }

    protected boolean check() {
        return true;
    }

}