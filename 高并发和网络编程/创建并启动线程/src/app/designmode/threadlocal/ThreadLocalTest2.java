package app.designmode.threadlocal;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-05 16:43:54
 * @LastEditTime: 2019-12-05 16:44:17
 * @LastEditors: 麦子
 */

public class ThreadLocalTest2 {

    public static void main(String[] args) {
        test2();
    }

    private static void test2() {
        Thread t1 = new Thread() {

            @Override
            public void run() {
                fun1();
                fun2();
            }

            private void fun2() {
                System.out.println("在函数2中获取到当前线程的name数据为:" + ThreadUtil.getName());
            }

            private void fun1() {
                ThreadUtil.setName("my own name");
                System.out.println("函数1设置当前线程的name数据为:my own name");
            }

        };
        t1.start();
    }
}
