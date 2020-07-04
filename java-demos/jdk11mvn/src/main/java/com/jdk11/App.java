package com.jdk11;

import cn.hutool.json.JSONUtil;

/*
 * @Description  : 请输入....
 * @Author       : 麦子
 * @Date         : 2020-04-21 15:31:56
 * @FilePath     : /jdk11mvn/src/main/java/com/jdk11/App.java
 * @LastEditTime : 2020-04-28 17:54:03
 * @LastEditors  : Do not edit
 */
public class App {
    /**
     * @Description: 方法说明....
     * @Date: 2020-04-22 18:34:56
     * @param args
     * @return:
     * @LastEditors: Do not edit
     */
    public static void main(final String[] args) {
        final var str = "hello java 11";
        System.out.println(str);
        App.name("name", "password");
    }

    /**
     * @Deprecated
     * @param name
     * @param password
     * @return
     */
    public final static String name(final String name, final String password) {

        System.out.println(name + "--" + password);

        var a = "2222";
        System.out.println(a);

        System.out.println(a);

        System.out.println("我在测试");

        JSONUtil.createArray();

        return null;

    }

}
