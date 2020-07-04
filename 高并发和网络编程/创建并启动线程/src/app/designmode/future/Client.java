package app.designmode.future;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-05 09:53:03
 * @LastEditTime: 2019-12-05 10:34:12
 * @LastEditors: 麦子
 */

public class Client {
    public Data request(final String string) {
        final FutureData futureData = new FutureData();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // RealData的构建很慢，所以放在单独的线程中运行
                RealData realData = new RealData();
                realData.handleBusiness(string);
                futureData.setRealData(realData);
            }
        }).start();
        return futureData; // 先直接返回FutureData
    }
}