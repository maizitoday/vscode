package app.poolthread;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-02 16:34:57
 * @LastEditTime: 2019-12-03 11:12:55
 * @LastEditors: 麦子
 */

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class ThreadPool implements Service {

    /**
     * 任务队列，用来存储提交的任务
     */
    private BlockingQueue<MyWork> taskQueue = null;

    /**
     * 线程池中存储线程的容器。
     */
    private final Queue<PoolThread> threads = new ArrayDeque<PoolThread>();

    private boolean isShutdown = false;
    /**
     * 活跃线程
     */
    private final int activeCount;

    private final static int initSize = 2;

    public ThreadPool(final int activeCount, final int maxNoOfTasks) {
        this.activeCount = activeCount;
        taskQueue = new BlockingQueue<MyWork>(maxNoOfTasks);

        // 初始化线程池
        for (int i = 0; i < initSize; i++) {
            threads.add(new PoolThread(taskQueue,i));
        }

        // 如果初始化的线程，没有到达活跃的线程数，继续添加
        if (initSize < activeCount) {
            for (int i = initSize; i < activeCount; i++) {
                threads.add(new PoolThread(taskQueue,i));
            }
        }
        // 启动线程池线程
        threads.forEach(thread -> thread.start());
    }

    @Override
    public synchronized void execute(final MyWork task) {
        if (this.isShutdown()) {
            throw new IllegalStateException("ThreadPool is stopped");
        }
        // 任务入列
        taskQueue.enqueue(task);
    }

    @Override
    public void execute(final List<MyWork> myWorkList) {
        if (this.isShutdown()) {
            throw new IllegalStateException("ThreadPool is stopped");
        }
        // 任务入列
        if (myWorkList != null && myWorkList.size() > 0) {
            for (final MyWork myWork : myWorkList) {
                taskQueue.enqueue(myWork);
            }
        }
    }

    @Override
    public synchronized void shutdown() {
        this.isShutdown = true;
        threads.forEach(thread -> thread.doStop());
    }

    /**
     * 关掉几个多余线程
     */
    @Override
    public synchronized void shutdownCount(final int closeCount) {
        for (int i = 0; i < closeCount; i++) {
            threads.element().doStop();
        }
    }

    @Override
    public boolean isShutdown() {
        return isShutdown;
    }

}