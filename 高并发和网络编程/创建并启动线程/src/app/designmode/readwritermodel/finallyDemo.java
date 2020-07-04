package app.designmode.readwritermodel;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-04 16:23:21
 * @LastEditTime: 2019-12-04 16:35:14
 * @LastEditors: 麦子
 */

public class finallyDemo implements Runnable {

    private int current = 0;

    @Override
    public synchronized void run() {
        while (current < 10) {
            current++;
        }
        System.out.println("current is " + current);
        try {
            if (current == 10) {
                try {
                    System.out.println("开始自我等待");
                    wait();
                    System.out.println("我的等待自动介绍了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            System.out.println("开始finally模式");
            if (current == 10) {
                notifyAll();
            }
        }
    }

    public synchronized void call() {
        this.notifyAll();
    }

    public static void main(String[] args) {
        finallyDemo demo = new finallyDemo();
        Thread t = new Thread(demo);
        t.start();
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        demo.call();
    }

}