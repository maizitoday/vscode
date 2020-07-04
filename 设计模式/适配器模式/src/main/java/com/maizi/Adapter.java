package com.maizi;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-02-20 12:07:02
 * @LastEditTime: 2020-02-20 12:23:31
 * @LastEditors: 麦子
 */

/**
 * 适配器角色
 */
public class Adapter extends Adaptee implements Target {

    /**
     * 可以看到， 这里实现了目标接口，但是在实现的方法中调用的是 已经服役的业务逻辑。
     * 
     * 说明： 如果你需要对接原来的业务， 我们直接在Adaptee类中直接 继承 ConcreteTarget（目标角色的实现类） 或者直接把
     * ConcreteTarget（目标角色的实现类）作为一个私有属性， 如此就和原来的业务逻辑直接挂钩了。对外层 如果直接接口的话， 后期改动非常少了。
     * 
     * 因为最外面暴露的只是一个  Target接口， 外面的只要是这个接口即可。 感觉也是这个设计模式核心。 
     */
    public void request() {

        super.doSomething();
    }
}