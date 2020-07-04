package app.threadpool;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-16 16:45:51
 * @LastEditTime: 2019-12-17 15:17:52
 * @LastEditors: 麦子
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class executorsTest {
    public static void main(final String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(5);

        List<Work> workers = Work.buildData();
        List<FutureTask<String>> tasks = new ArrayList<>();
        for (Work worker : workers) {
            FutureTask<String> task = new FutureTask<String>(worker);
            service.submit(task);
            tasks.add(task);
        }

        for (FutureTask<String> task : tasks) {
            System.out.println(task.get());
        }
        service.shutdown();
    }
}

class Work implements Callable<String> {

    public String name;

    public Work(final String name) {
        this.name = name;
    }

    public Work() {
    }

    public void doWork() {
        System.out.println(this.name + " 开始工作 ");
    }

    public static List<Work> buildData() {
        final List<Work> data = new ArrayList<Work>();
        for (int i = 0; i < 10; i++) {
            data.add(new Work("小明" + i));
        }
        return data;
    }

    public static void name() {
        new ReentrantReadWriteLock(true);
    }

    @Override
    public String call() throws Exception {
         System.out.println(Thread.currentThread().getName()+"---"+this.name);
        return this.name+": 任务已经完成";
    }

}