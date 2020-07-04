package app.designmode.countdown;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-06 10:58:30
 * @LastEditTime: 2019-12-06 11:20:07
 * @LastEditors: 麦子
 */

public class Latch {
    // 阀门值
    private final int latchnum;

    // 计数器
    private int count = 0;

    public Latch(int latchnum) {
        this.latchnum = latchnum;
    }

    public synchronized void countDown() {
        this.count++;
        System.out.println("countDown ---> " + Thread.currentThread().getName());
        if (this.count == this.latchnum) {
            this.notifyAll();
        }

    }

    public synchronized void await() {
        System.out.println("wait " + Thread.currentThread().getName());
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Latch latch = new Latch(5);
        System.out.println("线程第一阶段开始工作");

        for (int i = 0; i < 5; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        latch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
        latch.await();
        System.out.println("阶段一全部完成，第二阶段开工");
    }
}
