package app;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-11-14 17:39:12
 * @LastEditTime: 2019-12-19 14:31:53
 * @LastEditors: 麦子
 */
public class App implements Runnable {

    // 从这里可以看到，在接口里面这个值是共享处理的，所以在接口中一般直接来处理业务逻辑
    private static int TICKET_COUNT = 10;
    private static final Object LOCK = new Object();

    @Override
    public void run() {
        for (int i = 0; i < TICKET_COUNT; i++) {
            synchronized (LOCK) {
                if (TICKET_COUNT > 0) {
                    System.out.println(Thread.currentThread().getName() + "卖第 " + --TICKET_COUNT + " 张票");
                }
            }
        }
    }

    public static void main(final String[] args) throws Exception {
        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(5);
       
        new ArrayList<>();

        final Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1_000);
                        System.out.println(Thread.currentThread().getName() + "->>我是子线程");
                    } catch (final InterruptedException e) {
                        System.out.println("收到打断信号");
                        e.printStackTrace();
                        // 
                        return;
                    }
                }

            }
        });

        t.start();
        t.interrupt();
        System.out.println("我是主线程");

    }
}