package app.producerconsumer;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-01 18:34:26
 * @LastEditTime: 2019-12-01 20:20:09
 * @LastEditors: 麦子
 */

public class Consumer implements Runnable {

    private TicketBox ticketBox;

    public Consumer(TicketBox ticketBox) {
        this.ticketBox = ticketBox;
    }

    @Override
    public void run() {
        boolean flag = true;
        while (flag) {
            try {
                Thread.sleep(1_000);
                flag = ticketBox.consumeTicket();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}