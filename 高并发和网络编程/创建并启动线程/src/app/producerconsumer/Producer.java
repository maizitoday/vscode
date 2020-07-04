package app.producerconsumer;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-01 18:34:06
 * @LastEditTime: 2019-12-01 20:36:33
 * @LastEditors: 麦子
 */

public class Producer implements Runnable {

    private TicketBox ticketBox;

    public Producer(TicketBox ticketBox) {
        this.ticketBox = ticketBox;

    }

    @Override
    public void run() {
        boolean flag = true;
        while (flag) {
            try {
                // 这个时间的控制，可以让其先生产一批，然后在消费，也可以一边生产一边消费，这个时间主要和消费者那边的时间比较就可以了
                Thread.sleep(1_00);
                flag = ticketBox.produceTicket();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}