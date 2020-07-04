package app.designmode.guardedsuspension;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-05 15:04:21
 * @LastEditTime: 2019-12-07 18:24:20
 * @LastEditors: 麦子
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * 此类用于依次存放请求，RequestQueue通过putRequest放入Request实例，并按顺序使用getRequest取出Request实例。
 * 这种结构通常称为队列（queue）或者 FIFO(First In First Out ,先进先出)
 *
 */
public class RequestQueue {

    private final Queue<Request> queue = new LinkedList<>();

    // 取出最先放在RequestQueue的中的一个请求，作为返回值，如果一个请求也没有就一直等待，直到其他线程执行putRequest.
    public synchronized Request getRequest() {
        while (queue.peek() == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return queue.remove();

    }

    // 添加一个请求
    public synchronized void putRequest(Request request) {
        queue.offer(request);
        notifyAll();
    }
}
