package app.designmode.threadpermessage;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-06 13:13:20
 * @LastEditTime: 2019-12-06 13:19:27
 * @LastEditors: 麦子
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageHandler {

    private static final ExecutorService pool = Executors.newFixedThreadPool(5);

    public void request(final Message message){
        pool.execute(new Runnable() {
            @Override
            public void run() {
                String result = message.getMessage();
                try {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + ">>>>>" + result+"---->"+pool);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}