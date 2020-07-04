package app.atomicity;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-11 12:20:32
 * @LastEditTime: 2019-12-11 12:27:40
 * @LastEditors: 麦子
 */

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongTest {
 
    public static void main(String[] args) {
        AtomicLong count = new AtomicLong(10L);
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
        boolean flag = count.compareAndSet(11, 100);
        System.out.println(count.get()+""+flag);

        // 以原子方式将给定值与当前值相加，返回旧值
        Long currentCount = count.getAndAdd(10L);
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