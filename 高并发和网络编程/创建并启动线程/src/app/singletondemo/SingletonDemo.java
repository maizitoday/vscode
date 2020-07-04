package app.singletondemo;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-02 15:23:44
 * @LastEditTime: 2019-12-02 15:28:00
 * @LastEditors: 麦子
 */

public class SingletonDemo {

    private volatile static SingletonDemo instance;

    private SingletonDemo() {
    }

    public static SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

}