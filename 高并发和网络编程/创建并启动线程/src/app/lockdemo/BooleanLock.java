package app.lockdemo;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-01 14:00:54
 * @LastEditTime: 2019-12-01 15:12:37
 * @LastEditors: 麦子
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class BooleanLock implements Lock {

    public BooleanLock(boolean initValue) {
        this.initValue = initValue;
    }

    // false means is free
    // true means having been used
    // 通过这个变量来控制时间的长度。 
    private volatile boolean initValue;

    private Thread currentThread;

    private Collection<Thread> blockedThreads = new ArrayList<>();

    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue) {
            blockedThreads.add(Thread.currentThread());
            this.wait();
        }

        blockedThreads.remove(Thread.currentThread());
        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException {

        System.out.println("current thread :" + Thread.currentThread().getName() + " 共享变量 " + this.initValue);

        if (mills <= 0)
            lock();
        long hasRemaining = mills;
        long endTime = System.currentTimeMillis() + mills;
        while (initValue) {
            if (hasRemaining <= 0)
                throw new TimeOutException("time out, and the Thread[" + Thread.currentThread().getName()+ "] do not get the lock--->" + this.initValue);
            blockedThreads.add(Thread.currentThread());
            this.wait(mills);
            System.out.println("current thread ->"+Thread.currentThread().getName()+" initValue ->" + this.initValue);
            hasRemaining = endTime - System.currentTimeMillis();
        }
        blockedThreads.remove(Thread.currentThread());
        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void unlock() {
        if (currentThread != null && Thread.currentThread() == currentThread) {
            this.initValue = false;
            Optional.of(Thread.currentThread().getName() + " release the lock...").ifPresent(System.out::println);
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(blockedThreads);
    }

    @Override
    public int getBlockedSize() {
        return blockedThreads.size();
    }
}