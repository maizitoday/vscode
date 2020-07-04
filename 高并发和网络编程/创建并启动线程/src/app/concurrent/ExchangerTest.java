package app.concurrent;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-14 09:16:43
 * @LastEditTime: 2019-12-14 09:37:53
 * @LastEditors: 麦子
 */

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class ExchangerTest {
    public static void main(String[] args) throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<String>();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String temp = exchanger.exchange("张三");
                    System.out.println("resultA -> " + temp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(6);
                    String temp = exchanger.exchange("李四");
                    System.out.println("resultB -> " + temp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String temp = exchanger.exchange("王五");
                    System.out.println("resultC -> " + temp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String temp = exchanger.exchange("小明");
                    System.out.println("resultD -> " + temp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        System.out.println("等待exchanger跑完");
    }

}