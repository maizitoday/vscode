package app.poolthread;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-02 19:34:17
 * @LastEditTime: 2019-12-02 19:42:45
 * @LastEditors: 麦子
 */

public class ThreadStatusDemo implements Runnable {

    @Override
    public void run() {

        System.out.println("当前线程状态:-->" + Thread.currentThread().getState());

    }

    public static void main(String[] args) {
        ThreadStatusDemo statusDemo = new ThreadStatusDemo();
        Thread t = new Thread(statusDemo);
        t.start();

        try {
            Thread.sleep(10_000);
            System.out.println("-->-->-->-->-->-->当前线程状态:-->" + t.isInterrupted());
            System.out.println("当前线程状态:-->" + t.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    
}