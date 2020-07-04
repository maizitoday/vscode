package app.poolthread;
/*
 * @Description: 自定义阻塞队列
 * @Author: 麦子
 * @Date: 2019-12-02 16:15:14
 * @LastEditTime: 2019-12-03 10:59:51
 * @LastEditors: 麦子
 */

import java.util.LinkedList;
import java.util.List;

public class BlockingQueue<T> {
    /**
     * 使用链表实现一个阻塞队列(数据结构定义数据存储和获取方式，所以只要满足这两点，阻塞队列可以用链表，也可以使用数组等来实现)
     */
    private volatile List<T> queue = new LinkedList();
    /**
     * limit用来限制提交任务的最大数，默认10
     */
    private int limit = 10;

    public BlockingQueue(int limit) {
        this.limit = limit;
    }

    /**
     *
     * @param item enqueue是一个同步方法，当任务到达上限，便会调用wait方法进行阻塞，否则将任务放入队列中，并唤醒dequeue()任务线程
     */
    public synchronized void enqueue(T item) {
        while (this.queue.size() == this.limit) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (this.queue.size() <= limit) {
            this.notifyAll();
        }
        this.queue.add(item);
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * dequeue也是一个同步方法，当队列中没有任务时便会调用wait方法进入阻塞，当任务到达最大容量是唤醒其他dequeue()线程 ，并出列一个任务。
     */
    public synchronized T dequeue() {
        while (this.queue.size() == 0) {
            try {
                // 当是0的时候， 5条线程都在wait状态
                System.out.println("等待线程："+Thread.currentThread().getName());
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (this.queue.size() == this.limit) {
            this.notifyAll();
        }
        T t = this.queue.remove(0);
        return t;
    }

    public synchronized int size() {
        return queue.size();
    }
}