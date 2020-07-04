package app.lockdemo;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-01 14:02:12
 * @LastEditTime: 2019-12-01 15:14:18
 * @LastEditors: 麦子
 */

import java.util.Optional;
import java.util.stream.Stream;

public class LockTest {
    public static void main(String[] args) throws InterruptedException {

        final BooleanLock booleanLock = new BooleanLock(false);
        Stream.of("T1", "T2", "T3", "T4").forEach(name -> new Thread(() -> {
            try {
                booleanLock.lock(10_000L);
                Optional.of(Thread.currentThread().getName() + " have the lock Monitor").ifPresent(System.out::println);
                work();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Lock.TimeOutException e) {
                System.out.println(e.getMessage());
                // Optional.of(Thread.currentThread().getName() + " time out")
                // .ifPresent(System.out::println);
            } finally {
                booleanLock.unlock();
            }
        }, name).start());
    }

    private static void work() throws InterruptedException {
        Optional.of(Thread.currentThread().getName() + " is Working...").ifPresent(System.out::println);
        Thread.sleep(1_000);
    }

}