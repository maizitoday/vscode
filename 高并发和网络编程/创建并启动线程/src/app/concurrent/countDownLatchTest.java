package app.concurrent;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-12 11:17:39
 * @LastEditTime: 2019-12-12 11:59:07
 * @LastEditors: 麦子
 */

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class countDownLatchTest {
    public static void main(String[] args) {

        final CountDownLatch latch = new CountDownLatch(2);
        System.out.println("主线程开始执行…… ……");
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " --- start ");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " ---  end ");
                latch.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " ==== start ");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "  ==== end ");
                latch.countDown();
            }
        }).start();

        try {
            latch.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("两天线程运行完成。");

    }
}