package app.designmode.readwritermodel;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-04 15:19:55
 * @LastEditTime: 2019-12-04 15:39:53
 * @LastEditors: 麦子
 */

public class ReaderWorker extends Thread {
    // 共享的数据
    private final SharedData data;

    public ReaderWorker(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char[] readBuf = data.read();
                System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(readBuf));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
