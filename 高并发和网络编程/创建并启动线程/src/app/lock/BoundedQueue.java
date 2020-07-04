package app.lock;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-18 12:23:20
 * @LastEditTime: 2019-12-18 14:03:02
 * @LastEditors: 麦子
 */

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedQueue {

    private final LinkedList<Object> buffer; // 生产者容器
    private final int maxSize; // 容器最大值是多少
    private final Lock lock;
    private final Condition produceCondition;
    private final Condition consumerCondition;

    BoundedQueue(final int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<Object>();
        lock = new ReentrantLock();
        produceCondition = lock.newCondition();
        consumerCondition = lock.newCondition();
    }

    /**
     * 生产者
     * 
     * @param obj
     * @throws InterruptedException
     */
    public void put(final Object obj) throws InterruptedException {
        lock.lock(); // 获取锁
        TimeUnit.SECONDS.sleep(1);
        System.out.println("生产方： " + Thread.currentThread().getName() + " 获取到锁 ");
        try {
            while (maxSize == buffer.size()) {
                System.out.println("生产已经满了.....");
                produceCondition.await(); // 满了，添加的线程进入等待状态
            }
            buffer.add(obj);
            System.out.println(obj);
            consumerCondition.signal(); // 通知
        } finally {
            lock.unlock();
        }
    }

    /**
     * 消费者
     * 
     * @return
     * @throws InterruptedException
     */
    public Object get() throws InterruptedException {
        lock.lock();
        Object obj;
        TimeUnit.SECONDS.sleep(1);
        System.out.println("消费方：" + Thread.currentThread().getName() + " 获取到锁 ");
        try {
            while (buffer.size() == 0) { // 队列中没有数据了 线程进入等待状态
                System.out.println("产品已经卖完了.....");
                consumerCondition.await();
            }
            obj = buffer.poll();
            System.out.println("卖掉产品: " + obj);
            produceCondition.signalAll(); // 通知
        } finally {
            lock.unlock();
        }
        return obj;
    }

    public static void main(final String[] args) {
        final BoundedQueue queue = new BoundedQueue(5);
        final ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            final int index = i;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        queue.put("生成-" + index);
                    } catch (final InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        for (int i = 0; i < 5; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        queue.get();
                    } catch (final InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        service.shutdown();
    }
}