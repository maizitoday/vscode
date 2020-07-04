package app.designmode.immutable;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-04 17:09:47
 * @LastEditTime: 2019-12-04 18:08:39
 * @LastEditors: 麦子
 */

public class Person {// 允许同时访问的共享资源类
    private final String name;
    private final String address;

    public Person(final String n, final String a) {
        name = n;
        address = a;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String toString() {
        return "[person:name=" + name + ",address=" + address + "]";
    }

    // 多线程访问者
    public class WorkerThread extends Thread {
        private final Person person;

        public WorkerThread(final Person p) {
            person = p;
        }

        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " prints " + person);
            }
        }// 线程的访问并不会影响资源的状态，这里仅仅将其打印出来

    }

    public static void main(final String[] args) {
        final Person p = new Person("Alice", "Alaska");
        p.new WorkerThread(p).start();
        p.new WorkerThread(p).start();
        p.new WorkerThread(p).start();
    }
}
