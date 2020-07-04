package app.threadexception;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-02 09:26:58
 * @LastEditTime: 2019-12-02 09:40:24
 * @LastEditors: 麦子
 */

public class ThreadException implements Runnable {

    @Override
    public void run() {
        System.out.println(" currentThread is  "+Thread.currentThread().getName());
        int count = 10 / 0;
    }

    private static final class ThreadExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("Thread id=" + t.getId());
            System.out.println("Thread name=" + t.getName());
            e.printStackTrace(System.out);
        }
    }

    public static void main(String[] args) {
        ThreadException t = new ThreadException();
        Thread thread = new Thread(t);
        ThreadExceptionHandler threadExceptionHandler = new ThreadExceptionHandler();
        // 相当于加入了一个线程回调处理类。 
        thread.setUncaughtExceptionHandler(threadExceptionHandler);
        thread.start();
    }
}