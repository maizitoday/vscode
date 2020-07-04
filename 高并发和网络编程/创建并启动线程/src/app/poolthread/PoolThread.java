package app.poolthread;
/*
 * @Description: 新建一个线程池线程类，用来执行提交的任务。结构体中传入任务队列，run()方中发现taskQueue有任务时，获取任务并执行，没有任务就阻塞。
 * @Author: 麦子
 * @Date: 2019-12-02 16:30:23
 * @LastEditTime: 2019-12-03 11:23:20
 * @LastEditors: 麦子
 */

public class PoolThread extends Thread {

    private volatile BlockingQueue<MyWork> taskQueue = null;

    private volatile int ativeThread;

    private volatile int threadCount = 5;

    private boolean isStopped = false;

    public PoolThread(BlockingQueue<MyWork> taskQueue, int ativeThread) {
        this.taskQueue = taskQueue;
        this.ativeThread = ativeThread;
    }

    public void run() {
        System.out.println("---->" + ativeThread);
        // 这里通过判断线程没有被中断的判断， 进行线程的死循环。
        while (!isStopped() && !Thread.currentThread().isInterrupted()) {
            try {
                // 从任务队列获取任务并执行
                MyWork myWork = (MyWork) taskQueue.dequeue();
                myWork.work();
            } catch (Exception e) {
                isStopped = true;
                break;
            }
            // 当拿出一个任务完成后， 最后默认保存2条存活线程，在wait中。 
            System.out.println("队列当前数:-->" + this.taskQueue.size());
            if (threadCount - ativeThread > 2) {
                doStop();
            }

        }
        System.out.println(Thread.currentThread().getName() + " 线程已经退出");
    }

    public synchronized void doStop() {
        isStopped = true;
        // this.interrupt();
    }

    public synchronized boolean isStopped() {
        return isStopped;
    }
}