package app.designmode.guardedsuspension;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-05 15:05:55
 * @LastEditTime: 2019-12-05 15:24:29
 * @LastEditors: 麦子
 */

import java.util.Random;

/**
 * 表示发送请求的线程。ClientThread持有RequestQueue的实例，并连续调用该实例的putRequest，放入请求。
 * 
 */
public class ClientThread extends Thread {
    private final Random random;
    private final RequestQueue requestQueue;

    public ClientThread(RequestQueue requestQueue, String name, long seed) {
        super(name);
        this.requestQueue = requestQueue;
        this.random = new Random(seed);

    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Request request = new Request("No." + i);
            System.out.println(Thread.currentThread().getName() + " requests " + request+"\n");
            requestQueue.putRequest(request);
            try {
                // 为了错开发送请求的执行点，使用java.util.Random类随机生成0-1000之间的数
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
