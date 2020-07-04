package app.designmode.readwritermodel;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-04 15:20:58
 * @LastEditTime: 2019-12-04 16:16:47
 * @LastEditors: 麦子
 */

import java.util.Random;

public class WriterWorker extends Thread {
    // 随机的因子
    private static final Random random = new Random(System.currentTimeMillis());
    // 肯定有数据
    private final SharedData data;

    private final String filler;
    // 去filler里面拿那些东西写到buffer里面去
    private int index = 0;

    public WriterWorker(SharedData data, String filler) {
        this.data = data;
        this.filler = filler;
    }
    
    private boolean writeFlag;
    

    @Override
    public void run() {
        try {
            while (!writeFlag) {
                char[] c = filler.toCharArray();
                data.write(c);
                System.out.println("I am write worker ......... " + Thread.currentThread() + c);
                // 休眠
                Thread.sleep(random.nextInt(1000));
                writeFlag = true;
                System.out.println("数据写入完毕");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
