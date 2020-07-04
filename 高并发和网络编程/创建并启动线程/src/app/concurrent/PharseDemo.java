package app.concurrent;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-14 18:28:57
 * @LastEditTime: 2019-12-15 16:44:27
 * @LastEditors: 麦子
 */

import java.util.Random;
import java.util.concurrent.Phaser;

public class PharseDemo {

    public static void main(String[] args) throws InterruptedException {
        // 10名运动员，每名运动员在同一时间点的开始
        Phaser pharse = new Phaser(5) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                if (phase == 0) {
                    System.out.println("所有人员已经到赛场");
                } else if (phase == 1) {
                    System.out.println("所有人员准备完毕，开始比赛");
                } else if (phase == 2) {
                    System.out.println("比赛结束");
                }
                return super.onAdvance(phase, registeredParties);
            }
        };
        // 创建10个线程
        Runner run = new Runner(pharse);
        Thread[] thread = new Thread[5];
        for (int i = 0; i < 5; i++) {
            thread[i] = new Thread(run);
        }
        Thread.sleep(3000);
        for (int i = 0; i < 5; i++) {
            thread[i].start();
        }
    }

}

class Runner implements Runnable {

    private Phaser phaser;
    Random random = new Random();

    public Runner(Phaser phaser) {
        super();
        this.phaser = phaser;
    }

    @Override
    public void run() {
        try {
            // 第一阶段 到达赛场 每个运动员情况不一样
            Thread.sleep(random.nextInt(3000));
            System.out.println(Thread.currentThread().getName() + "已经到达赛场");
            phaser.arriveAndAwaitAdvance();
            

            // 第二阶段 开始准备
            Thread.sleep(random.nextInt(3000));
            System.out.println(Thread.currentThread().getName() + "已经准备好");
            phaser.arriveAndAwaitAdvance();

          

            // 第二阶段 到达终点
            Thread.sleep(random.nextInt(3000));
            System.out.println(Thread.currentThread().getName() + "已经到达终点");
            phaser.arriveAndAwaitAdvance();

           
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}