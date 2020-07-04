package app.designmode.workerthread;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-07 18:12:26
 * @LastEditTime: 2019-12-07 18:35:44
 * @LastEditors: 麦子
 */

import java.util.LinkedList;
import java.util.Queue;

public class Channel {
    private static final int MAX_REQUEST = 100; // 最大请求数
    private volatile  Queue<Request> requestQueue; 
    private final WorkerThread[] threadPool;

    public Channel(int threads) {
        this.requestQueue = new LinkedList<>();
        threadPool = new WorkerThread[threads];
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i] = new WorkerThread("Worker-" + i, this);
        }
    }

    public void startWorkers() {
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i].start();
        }
    }

    public synchronized void putRequest(Request request) {
        requestQueue.offer(request);
        notifyAll();
    }

    public synchronized Request takeRequest() {
        while (null == requestQueue.peek()) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        Request request = requestQueue.remove();
        notifyAll();
        return request;
    }
}