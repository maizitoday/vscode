package app.concurrent;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-14 17:34:06
 * @LastEditTime: 2019-12-14 18:18:13
 * @LastEditors: 麦子
 */

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class RecursiveTaskDemo extends RecursiveTask<Integer> {
    private static final long serialVersionUID = 1L;
    /**
     * 每个"小任务"最多只打印70个数
     */
    private static final int MAX = 4;
    private int arr[];
    private int start;
    private int end;

    public RecursiveTaskDemo(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        // 当end-start的值小于MAX时候，开始打印
        if ((end - start) < MAX) {
            System.err.println(Thread.currentThread().getName() + "=============");
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            return sum;
        } else {
            System.err.println(Thread.currentThread().getName() + "=====任务分解======");
            // 将大任务分解成两个小任务
            int middle = (start + end) / 2;
            RecursiveTaskDemo left = new RecursiveTaskDemo(arr, start, middle);
            RecursiveTaskDemo right = new RecursiveTaskDemo(arr, middle, end);
            // 并行执行两个小任务
            left.fork();
            right.fork();
            // 把两个小任务累加的结果合并起来
            return left.join() + right.join();
        }
    }

    public static void main(String[] args) {
        int arr[] = new int[10];

        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }

        // 创建包含Runtime.getRuntime().availableProcessors()返回值作为个数的并行线程的ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer integer = forkJoinPool.invoke(new RecursiveTaskDemo(arr, 0, arr.length));
        System.out.println("计算出来的总和=" + integer);

        // 关闭线程池
        forkJoinPool.shutdown();

    }

}