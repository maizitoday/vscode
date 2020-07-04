package app.designmode.balking;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-05 18:04:43
 * @LastEditTime: 2019-12-05 18:29:07
 * @LastEditors: 麦子
 */

public class Main {
    public static void main(String[] args) {

        Customer customer = new Customer("顾客1");
        CallServiceThread changer = new CallServiceThread(customer);

        Waiter waiter1 = new Waiter("waiter1", customer);
        Waiter waiter2 = new Waiter("waiter2", customer);

        changer.start();

        waiter1.start();
        waiter2.start();
    }

}