package app.concurrent;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-14 09:44:05
 * @LastEditTime: 2019-12-14 11:24:25
 * @LastEditors: 麦子
 */

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest implements Runnable {

    private Semaphore semaphore;

    public SemaphoreTest(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + "开始工作");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前可用的许可证---"+semaphore.availablePermits());
            System.out.println(Thread.currentThread().getName() + "结束工作");
            semaphore.release();
            System.out.println("当前可用的许可证---"+semaphore.availablePermits());
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        SemaphoreTest test = new SemaphoreTest(semaphore);
        Thread t = new Thread(test);
        Thread t2 = new Thread(test);
        t.start();
        t2.start();

    }

}