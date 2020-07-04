package app.threadgroup;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-02 10:19:41
 * @LastEditTime: 2019-12-02 12:26:55
 * @LastEditors: 麦子
 */

public class ThreadGroupDemo implements Runnable {

    @Override
    public synchronized void run() {
        System.out.println("ThreadName = " + Thread.currentThread().getName() + "准备开始死循环了 \n");
        while (!Thread.currentThread().isInterrupted()) {
            // System.out.println("循环处理业务中.....");
        }
        try {
            wait();
        } catch (InterruptedException e) {
            System.out.println("\n wait 方法被打断了 \n");
        }

        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) { 
            System.out.println("sleep 方法被打断了 \n");
        }

        System.out.println("ThreadName = " + Thread.currentThread().getName() + "结束了");
    }

    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("maizi-group");
        ThreadGroupDemo runnable = new ThreadGroupDemo();
        Thread thread1 = new Thread(threadGroup, runnable, "麦子1-thread");
        Thread thread2 = new Thread(threadGroup, runnable, "麦子2-thread");
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(1_000);
            int totalThread = threadGroup.activeCount();
            System.out.println("线程组的存活个数:" + totalThread + "\n");

            Thread[] lstThreads = new Thread[totalThread];
            threadGroup.enumerate(lstThreads);
            for (Thread thread : lstThreads) {
                System.out.println("活动线程数量一共：" + totalThread + " 线程id：" + thread.getId() + " 线程名称：" + thread.getName()+ " 线程状态：" + thread.getState());
            }

            //注意这个只能打断wait的方法， sleep的是无法打断的。 而且这个打断后， 线程不会马上就无法工作了，需要等一段时间线程才会死亡。 
            threadGroup.interrupt();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}