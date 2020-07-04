package app.deallock;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-03 11:46:31
 * @LastEditTime: 2019-12-03 12:02:33
 * @LastEditors: 麦子
 */

public class DealLock implements Runnable {
    private Object Alock = new Object();
    private Object Block = new Object();

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        if ("A".equals(name)) {
            a();
        } else {
            b();
        }
    }

    public void a() {
        synchronized (Alock) {
            System.out.println("我已经获取了A锁，休息一下获取B锁");
            try {
                Thread.sleep(1_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (Block) {
                System.out.println("我已经获取了B锁，执行完成");
            }
        }
    }

    public void b() {
        synchronized (Block) {
            System.out.println("我已经获取了B锁，休息一下获取A锁");
            try {
                Thread.sleep(1_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (Alock) {
                System.out.println("我已经获取了A锁,执行完成");
            }
        }
    }
 
    public static void main(String[] args) {
        DealLock deallock = new DealLock();

        Thread thread = new Thread(deallock,"A");
        thread.start();   

        Thread thread2 = new Thread(deallock,"B");        
        thread2.start();
    }
}