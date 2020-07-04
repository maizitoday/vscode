package app.designmode.readwritermodel;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-04 15:13:16
 * @LastEditTime: 2019-12-04 16:03:11
 * @LastEditors: 麦子
 */

public class ReadWriteLock {
    // 定义变量
    private int readingReaders = 0;// 当前有几个线程进行读的操作
    private int waitingReaders = 0;// 有几个线程想读却读不了 方法放进waitset队列
    private int writingWriters = 0;// 当前有几个线程让它写
    private int waitingWriters = 0;// 在等着释放锁去写
    // 为了避免一直读 那么给它一个默认更喜欢
    private boolean preferWriter = true;

    public ReadWriteLock() {
        this(true);
    }

    public ReadWriteLock(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    // 让它去读,
    // 注意这个读的锁并没有直接去读取数据， 只是为了判断在读的时候， 有没有线程对公共数据进行写的操作， 如果没有的话， 后面方法的读都是并发操作的。 
    public synchronized void readLock() throws InterruptedException {
        this.waitingReaders++;
        try {
            // 如果当前有线程在写 同时写的数多
            while (writingWriters > 0 || (preferWriter && waitingWriters > 0)) {
                // 你就不能读了
                System.out.println("有其他线程正在进行写或者等待写的操作，为保证数据的一致性，请稍后在读");
                this.wait();
            }
            // 如果它不写了,你就去读,代表当前有一个线程在读
            this.readingReaders++;
        } finally {
            // 等待读不等了,不在waitset里面了等待读的就少了一个
            this.waitingReaders--;
        }
    }

    // 放弃读的锁
    public synchronized void readUnlock() {
        this.readingReaders--; // 释放了一个read的锁
        this.notifyAll();
    }

    // 写的操作
    public synchronized void writeLock() throws InterruptedException {
        this.waitingWriters++;
        try {
            // 当有读的或者在写的
            while (readingReaders > 0 || writingWriters > 0) {
                // 就不能写了
                System.out.println("有其他线程正在进行读或者进行写的操作，为保证数据的一致性，请稍后在写");
                this.wait();
            }
            // 等带写的jia+ 有人在写
            this.writingWriters++;
        } finally {
            // 如果从阻塞状态退出
            this.waitingWriters--;
        }
    }

    public synchronized void writeUnlock() {
        this.writingWriters--;
        // 通知你其他可以去操作了
        this.notifyAll();
    }
}
