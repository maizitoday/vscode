package app.lockdemo;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-01 13:59:54
 * @LastEditTime: 2019-12-01 14:46:41
 * @LastEditors: 麦子
 */

import java.util.Collection;

public interface Lock {
    static class TimeOutException extends Exception {
        TimeOutException(String message) {
            super(message);
        }
    }

    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException, TimeOutException;

    void unlock();

    Collection<Thread> getBlockedThread();

    int getBlockedSize();
}