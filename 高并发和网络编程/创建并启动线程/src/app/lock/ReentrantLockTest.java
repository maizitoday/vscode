package app.lock;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-17 15:18:17
 * @LastEditTime: 2019-12-17 15:42:12
 * @LastEditors: 麦子
 */

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(3);
        Lock lock = new ReentrantLock();
        Work work = new Work(lock);
        for (int i = 0; i < 3; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    work.doWork();
                }
            });
        }

    }
}

class Work {

    private String name;
    private Lock lock;

    public Work(Lock lock) {
        this.lock = lock;
    }

    public void doWork() {
        try {
            lock.lock();
            List<String> names = Arrays.asList("小强", "小米", "小明");
            int index = (int) (Math.random() * names.size());
            this.name = names.get(index);
            System.out.println(this.name + ": 被锁定" + Thread.currentThread().getName());
            System.out.println(this.name + ": 努力工作" + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(2);
            System.out.println(this.name + ": 工作已经完成" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(this.name + "锁已经释放");
        }
    }

}
