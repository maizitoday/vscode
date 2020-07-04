package app.designmode.workerthread;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-07 18:11:01
 * @LastEditTime: 2019-12-07 18:31:56
 * @LastEditors: 麦子
 */
import java.util.Random;

public class ClientThread extends Thread {
    private final Channel channel;
    private static final Random random = new Random();
    public ClientThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                Request request = new Request(getName(), i);
                channel.putRequest(request);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
        }
    }
}