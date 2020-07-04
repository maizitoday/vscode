package com.muban;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-04-11 11:33:50
 * @LastEditTime: 2020-04-11 11:46:50
 * @LastEditors: 麦子
 */

public class Car extends AbstractClass{

    @Override
    protected void doAnything() {
        
          System.out.println("do anything");
    }

    @Override
    protected void doSomething() {
         System.out.println("do something");
    }

    @Override
    protected boolean check() {
         return false;
    }
    

    public static void main(final String[] args) {
        final Car car = new Car();
         car.templateMethod();
    }

    
}