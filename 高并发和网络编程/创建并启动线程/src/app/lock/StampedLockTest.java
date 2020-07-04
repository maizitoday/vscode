package app.lock;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-17 18:26:17
 * @LastEditTime: 2019-12-18 12:16:33
 * @LastEditors: 麦子
 */

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(6);
        StampedLock lock = new StampedLock();
        Book book = new Book("小蝌蚪找妈妈", lock);

        for (int i = 0; i < 3; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    book.update();
                }
            });
        }

        for (int i = 0; i < 3; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    book.watch();
                }
            });
        }

        service.shutdown();

    }

}

class Book {

    private String name;
    private StampedLock lock;// 定义了StampedLock锁,

    public Book(String name, StampedLock lock) {
        this.name = name;
        this.lock = lock;
    }

    public void update() {
        long stamp = lock.writeLock();// 这里的含义和distanceFormOrigin方法中 s1.readLock()是类似的

        try {
            this.name = "大江东去";
            TimeUnit.SECONDS.sleep(2);
            System.out.println("写操作---->" + Thread.currentThread().getName() + " " + this.name +"  "+ new Date().getTime());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("修改完成  " + new Date().getTime());
            lock.unlockWrite(stamp);
        }
    }

    public void watch() {

        long stamp = lock.tryOptimisticRead(); // 试图尝试一次乐观读 返回一个类似于时间戳的邮戳整数stamp 这个stamp就可以作为这一个所获取的凭证
        if (!lock.validate(stamp)) {// 判断这个stamp是否在读过程发生期间被修改过,如果stamp没有被修改过,责任无这次读取时有效的,因此就可以直接return了,反之,如果stamp是不可用的,则意味着在读取的过程中,可能被其他线程改写了数据,因此,有可能出现脏读,如果如果出现这种情况,我们可以像CAS操作那样在一个死循环中一直使用乐观锁,知道成功为止
            try {
                stamp = lock.readLock();//也可以升级锁的级别,这里我们升级乐观锁的级别,将乐观锁变为悲观锁, 如果当前对象正在被修改,则读锁的申请可能导致线程挂起.
                TimeUnit.SECONDS.sleep(2);
                System.out.println("读操作---->" + Thread.currentThread().getName() + " " + this.name +"  "+ new Date().getTime());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("读取完成  " + new Date().getTime());
                lock.unlockRead(stamp);// 退出临界区,释放读锁
            }
        } else {
            // watch();
        }
    }

}