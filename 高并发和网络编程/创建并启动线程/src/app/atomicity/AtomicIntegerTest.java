package app.atomicity;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-11 11:34:17
 * @LastEditTime: 2019-12-11 12:19:45
 * @LastEditors: 麦子
 */

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    public static void main(String[] args) {

        AtomicInteger count = new AtomicInteger(10);
        // 以原子方式将给定值与当前值相加，返回相加后的新值
        count.addAndGet(2);
        System.out.println(count.get());

        // 以原子方式将当前值减 1，返回新值
        count.decrementAndGet();
        System.out.println(count.get());

        // 以原子方式将当前值加 1，返回新值
        count.incrementAndGet();
        System.out.println(count.get());

        // 如果当前值 == expect，则以原子方式将该值设置为给定的更新值（update）
        count.compareAndSet(11, 100);
        System.out.println(count.get());

        // 以原子方式将给定值与当前值相加，返回旧值
        int currentCount = count.getAndAdd(10);
        System.out.println(currentCount + "---" + count.get());

        // 以原子方式将当前值减 1，返回旧值
        currentCount = count.getAndDecrement();
        System.out.println(currentCount + "---" + count.get());

        // 以原子方式将当前值加 1，返回旧值
        currentCount = count.getAndIncrement();
        System.out.println(currentCount + "---" + count.get());

        // 以原子方式设置为给定值，并返回旧值
        currentCount = count.getAndSet(10);
        System.out.println(currentCount + "---" + count.get());

        count.set(1000);
        System.out.println(count.get());

    }

}