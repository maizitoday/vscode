package app.designmode.workerthread;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-07 18:13:11
 * @LastEditTime: 2019-12-07 18:13:47
 * @LastEditors: 麦子
 */

public class Main {
    public static void main(String[] args) {
        Channel channel = new Channel(5);
        channel.startWorkers();
        new ClientThread("Alice", channel).start();
        new ClientThread("Bobby", channel).start();
        new ClientThread("Chris", channel).start();
    }
}