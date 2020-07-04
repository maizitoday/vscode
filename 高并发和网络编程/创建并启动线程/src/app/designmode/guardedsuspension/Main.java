package app.designmode.guardedsuspension;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-05 15:07:21
 * @LastEditTime: 2019-12-05 15:25:23
 * @LastEditors: 麦子
 */

public class Main {
    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();
        new ClientThread(requestQueue, "发送请求-->", 3141592L).start();
        new ServerThread(requestQueue, "处理请求-->", 6535897L).start();
    }
}