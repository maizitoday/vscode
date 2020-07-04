package app.waitdemo;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-01 18:04:43
 * @LastEditTime: 2019-12-01 18:13:01
 * @LastEditors: 麦子
 */

import java.util.stream.Stream;

public class WaitTest {

    public synchronized void lock() throws InterruptedException {
        System.out.println("current thread is " + Thread.currentThread().getName());
        this.wait();
        System.out.println("current thread is " + Thread.currentThread().getName() + " unlock");
    }

    public static void main(String[] args) {
        WaitTest wait = new WaitTest();

        Stream.of("T1", "T2", "T3", "T4").forEach(name -> new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    wait.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, name).start());

    }

}