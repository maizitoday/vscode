package app.concurrent;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-14 11:29:32
 * @LastEditTime: 2019-12-14 17:31:18
 * @LastEditors: 麦子
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RaskDemo extends RecursiveAction {
    /**
     * 每个"小任务"最多只打印20个数
     */
    private static final int MAX = 5;

    private int start;
    private int end;

    public RaskDemo(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        // 当end-start的值小于MAX时，开始打印
        if ((end - start) < MAX) {
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + "-->i的值" + i);
            }
        } else {
            // 将大任务分解成两个小任务
            int middle = (start + end) / 2;
            RaskDemo left = new RaskDemo(start, middle);
            RaskDemo right = new RaskDemo(middle, end);
            left.fork();
            right.fork();
        }
    }

    public static void main(String[] args) throws Exception {
        // 创建包含Runtime.getRuntime().availableProcessors()返回值作为个数的并行线程的ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        // 提交可分解的PrintTask任务
        forkJoinPool.submit(new RaskDemo(0, 10));

        // 阻塞当前线程直到 ForkJoinPool 中所有的任务都执行结束
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);

        // 关闭线程池
        forkJoinPool.shutdown();
    }
}