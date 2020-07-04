package app.designmode.guardedsuspension;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-05 15:06:50
 * @LastEditTime: 2019-12-05 15:08:24
 * @LastEditors: 麦子
 */

import java.util.Random;

/**
 * 用于表示接收请求的线程，该类也持有RuquestQueue的实例，ServerThread使用该实例的getRequst方法来接受请求。
 */
public class ServerThread extends Thread {

    private final Random random;
    private final RequestQueue requestQueue;

    public ServerThread(RequestQueue requestQueue, String name, long seed) {
        super(name);
        this.requestQueue = requestQueue;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Request request = requestQueue.getRequest();
            System.out.println(Thread.currentThread().getName() + " handles " + request);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
