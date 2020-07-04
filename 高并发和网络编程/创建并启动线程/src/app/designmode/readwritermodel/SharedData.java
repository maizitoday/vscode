package app.designmode.readwritermodel;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-04 15:17:14
 * @LastEditTime: 2019-12-04 16:14:29
 * @LastEditors: 麦子
 */

public class SharedData {
    // 共享的数据读的操作不断从里面读,
    // 写的数据往里面填数据
    private char[] buffer;
    // 定义一个随机值,要去随机休眠的
    private final ReadWriteLock lock = new ReadWriteLock();

    public SharedData(int size) {
        // 初始化值
        this.buffer = new char[size];
        for (int i = 0; i < size; i++) {
            this.buffer[i] = '*';
        }
    }

    // 提供一个read方法 返回char[] 数据
    public char[] read() throws InterruptedException {
        try {// 获取read这个锁
            lock.readLock();
            // 读的方法
            return this.doRead();
        } finally {
            // 确保释放掉
            lock.readUnlock();
        }
    }

    // 写的操作
    public void write(char[] charData) throws InterruptedException {
        try {
            // 拿锁因为访问的资源是同一个
            lock.writeLock();
            // 去写东西
            this.doWrite(charData);
        } finally {
            // 释放掉这个锁
            lock.writeUnlock();
        }
    }

    private void doWrite(char[] charData) {
        this.buffer = charData;
    }

    private char[] doRead() {
        // 创建一个 副本 吧里面的数据读到newBuf里面
        char[] newBuf = new char[buffer.length];
        // 赋值
        for (int i = 0; i < buffer.length; i++)
            newBuf[i] = buffer[i];
        slowly(1_000);// 休眠一下 模拟读
        return newBuf;
    }

    private void slowly(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }
}
