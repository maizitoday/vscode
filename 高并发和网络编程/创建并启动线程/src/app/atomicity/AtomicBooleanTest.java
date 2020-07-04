package app.atomicity;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-11 12:30:22
 * @LastEditTime: 2019-12-11 15:31:33
 * @LastEditors: 麦子
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanTest implements Runnable {

    private static AtomicBoolean exists =  new AtomicBoolean(false);
    private String name;

    public AtomicBooleanTest(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        if(!exists.get()) {
            System.out.println(name + ":enter");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + ":leave");
            exists.set(false);
        }else{
            System.out.println(name +":give up");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicBooleanTest bar1 = new AtomicBooleanTest("bar1");
        AtomicBooleanTest bar2 = new AtomicBooleanTest("bar2");
        new Thread(bar1).start();
        new Thread(bar2).start();
    }

}