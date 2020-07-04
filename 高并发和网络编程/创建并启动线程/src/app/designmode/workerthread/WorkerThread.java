package app.designmode.workerthread;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-07 18:11:38
 * @LastEditTime: 2019-12-07 18:34:22
 * @LastEditors: 麦子
 */
public class WorkerThread extends Thread {
    private final Channel channel;
    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }
    public void run() {
        while (true) {
            Request request = channel.takeRequest();
            request.execute();
        }
    }
}