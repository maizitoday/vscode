package app.singletondemo;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-02 15:34:55
 * @LastEditTime: 2019-12-02 15:45:31
 * @LastEditors: 麦子
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum EnumSingleton {
    INSTANCE;

    private static EnumSingleton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        EnumSingleton instance = EnumSingleton.getInstance();
        System.out.println(EnumSingleton.INSTANCE == instance);


        Constructor<EnumSingleton> constructor=EnumSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        EnumSingleton newinstance=constructor.newInstance();
        System.out.println(instance+"\n"+newinstance);
        System.out.println("通过反射攻击单例模式情况下，实例化两个实例是否相同："+(instance==newinstance));
    }

}