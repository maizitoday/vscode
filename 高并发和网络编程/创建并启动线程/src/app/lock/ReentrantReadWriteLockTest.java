package app.lock;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-17 15:44:21
 * @LastEditTime: 2019-12-17 16:48:23
 * @LastEditors: 麦子
 */

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        WatchTV watch = new WatchTV(lock, "海绵宝宝");
        for (int i = 0; i < 3; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    watch.watchTime();
                }
            });
        }
        service.shutdown();
    }

}

class WatchTV {

    private String tvName;

    private ReentrantReadWriteLock lock;

    private Lock writeLock;
    private Lock readLock;

    public WatchTV(String tvName) {
        this.tvName = tvName;
    }

    public WatchTV(ReentrantReadWriteLock lock, String tvName) {
        this.lock = lock;
        this.tvName = tvName;
        writeLock = lock.writeLock();
        readLock = lock.readLock();
    }

    public WatchTV(ReentrantReadWriteLock lock) {
        this.lock = lock;
    }

    public void watchTime() {
        findTv();
        System.out.println(Thread.currentThread().getName() + " 开始查看有什么好看的动漫");
        try {
            switch (this.tvName) {
            case "海绵宝宝":
                readLock.lock();
                System.out.println(Thread.currentThread().getName() + "-->找到: 海绵宝宝 不看，换台");
                findTv();
                readLock.unlock();
                break;
            case "死神":
                readLock.lock();
                System.out.println(Thread.currentThread().getName() + "-->找到: 死神  不看，换台");
                findTv();
                readLock.unlock();
                break;
            case "进击的巨人":
                writeLock.lock();
                System.out.println(Thread.currentThread().getName() + "-->找到: 进击的巨人--->不要换台，我要看看");
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "-->已经看完: 进击的巨人--->继续换台");
                findTv();
                writeLock.unlock();
                break;
            default:
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void findTv() {
        List<String> names = Arrays.asList("海绵宝宝", "进击的巨人", "死神");
        int index = (int) (Math.random() * names.size());
        this.tvName = names.get(index);
    }
}