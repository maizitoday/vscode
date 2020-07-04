package app.threadpool;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-16 18:21:30
 * @LastEditTime: 2019-12-16 18:25:29
 * @LastEditors: 麦子
 */

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("thread name is : "+Thread.currentThread().getName());
                return "完成任务";
            }
        });
        Thread t1 = new Thread(task);
        t1.start();
        String result = task.get();
        System.out.println("result--->"+result);
    }
}