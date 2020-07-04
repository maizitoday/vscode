package app.designmode.balking;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-05 17:51:55
 * @LastEditTime: 2019-12-06 10:18:04
 * @LastEditors: 麦子
 */

import java.util.Random;

public class CallServiceThread extends Thread {

    private Customer m_Customer;

    public CallServiceThread(Customer customer) {

        this.m_Customer = customer;

    }

    /**
     * 线程使得顾客发出一百次服务要求
     */
    @Override
    public void run() {

        int i = 0;

        Object obj = new Object();

        synchronized (obj) {

            while (i < 10) {
                if (m_Customer.CallService("No_" + i)) {
                    i++;
                }
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