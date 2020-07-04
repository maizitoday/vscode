package app.producerconsumer;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-01 18:33:06
 * @LastEditTime: 2019-12-01 20:38:02
 * @LastEditors: 麦子
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 生产和消费的业务代码
 */
public class TicketBox {
    private volatile int currentTicket;
    private volatile List<Integer> produceTicketList = new ArrayList<>();
    private volatile List<Integer> consumerTicketList = new ArrayList<>();
    private volatile boolean flag = true;
    private static final int MAX_TICKET_COUNT = 15;

    /**
     * 生产票
     */
    public synchronized boolean produceTicket() {
        while (currentTicket != 0 && currentTicket % 5 == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (produceTicketList.size() < MAX_TICKET_COUNT) {
            ++currentTicket;
            produceTicketList.add(currentTicket);
            System.out.println(Thread.currentThread().getName() + " 生产第" + produceTicketList.size() + "张票");
        } else {
            try {
                wait();
                // 这个flag是为了控制多个线程到这里来，打印多次下面语句，通过volatile boolean flag来控制，打印一句出来正常就OK了。
                if (flag) {
                    System.out.println("票已经卖完了.....");
                    flag = false;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;

    }

    /**
     * 消费票
     */
    public synchronized boolean consumeTicket() {
        if (currentTicket == 0) {
            notifyAll();
            System.out.println("\n");
        }
        if (currentTicket > 0) {
            currentTicket--;
            consumerTicketList.add(currentTicket);
            System.out.println(Thread.currentThread().getName() + " 消费第" + consumerTicketList.size() + "张票 ");
        } else {
            // 这里需要加入一个判断不然，会导致currentTicket多线程情况的时候为0，也到了这里来了。
            if(consumerTicketList.size() == MAX_TICKET_COUNT){
                notifyAll();
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TicketBox ticketBox = new TicketBox();

        Producer product = new Producer(ticketBox);
        Consumer consumer = new Consumer(ticketBox);

        Thread producerThread = new Thread(product);
        Thread producerThread2 = new Thread(product);
        Thread producerThread3 = new Thread(product);
        producerThread.start();
        producerThread3.start();
        producerThread2.start();

        Thread consumerThread = new Thread(consumer);
        Thread consumerThread2 = new Thread(consumer);
        Thread consumerThread3 = new Thread(consumer);
        consumerThread3.start();
        consumerThread2.start();
        consumerThread.start();

    }

}