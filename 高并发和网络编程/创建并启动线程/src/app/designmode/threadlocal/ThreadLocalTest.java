package app.designmode.threadlocal;

/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-05 16:00:39
 * @LastEditTime: 2019-12-05 16:14:02
 * @LastEditors: 麦子
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Thread t1 = new Thread() {

            @Override
            public void run() {
                System.out.println(this.getName() + "开始值:" + ThreadUtil.getName());
                ThreadUtil.setName("线程0");
                sleepTime(1000 * 1);
                System.out.println(this.getName() + "设定后:" + ThreadUtil.getName());
            }

        };
        Thread t2 = new Thread() {

            @Override
            public void run() {
                sleepTime(1000 * 1);
                System.out.println(this.getName() + "开始值:" + ThreadUtil.getName());
                ThreadUtil.setName("线程1");
                System.out.println(this.getName() + "设定后:" + ThreadUtil.getName());
            }

        };
        t1.start();
        t2.start();
    }

    private static void sleepTime(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
