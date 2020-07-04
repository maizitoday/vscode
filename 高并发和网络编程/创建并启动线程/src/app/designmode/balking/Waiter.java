package app.designmode.balking;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-05 17:55:33
 * @LastEditTime: 2019-12-06 10:49:59
 * @LastEditors: 麦子
 */

import java.util.Random;

public class Waiter extends Thread {

    private Customer m_Customer;
    private static int i = 0; // 满二十次计数线程


    public Waiter(String name, Customer customer) {

        super(name);

        this.m_Customer = customer;

    }

    /**
     * 服务员随机等待一段时间后为顾客提供服务        
     */

     @Override
    public void run() {

        Object obj = new Object();

        synchronized (obj) {
            while (i < 10) {
                // 这里控制客服那边的发过来的请求和服务员这边的处理是一致。
                if (m_Customer.GetService(super.getName(), "No_" + i)) {
                    i++;
                }
                // 随机等待一段时间
                try {
                    Random rand = new Random();
                    Thread.sleep(rand.nextInt(1_000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}