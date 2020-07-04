package app.designmode.future;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-05 09:52:38
 * @LastEditTime: 2019-12-05 10:59:43
 * @LastEditors: 麦子
 */

public class Main {
    public static void main(final String[] args) {
        final Client client = new Client();
        // 这里会立即返回，因为获取的是FutureData，而非RealData
        final Data data = client.request("name=maizi");
        final FutureData future = (FutureData) data;
        future.setCallback(new FutureTaskCallBack() {
            @Override
            public String messageCallback(final RealData realData) {
                System.out.println("回调数据--->" + realData.data);
                return realData.data;
            }

        });
        System.out.println("请求完毕");
        // 使用真实数据
        try {
            // System.out.println("数据=" + data.getResult());

            // 回调
            future.getCallBackResult();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}