package app.designmode.readwritermodel;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-04 15:40:49
 * @LastEditTime: 2019-12-04 16:07:03
 * @LastEditors: 麦子
 */

public class ReadWritLockClient {
    public static void main(String[] args) {
        final SharedData sharedData = new SharedData(10);
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();

        
        // new WriterWorker(sharedData, "qwertyuiopasdfg").start();
        new WriterWorker(sharedData, "maizi").start();
    }
}
