package app.designmode.threadpermessage;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-06 13:14:04
 * @LastEditTime: 2019-12-06 13:14:24
 * @LastEditors: 麦子
 */

public class ThreadPerMessageTest {
    public static void main(String[] args) {
        MessageHandler messageHandler = new MessageHandler();
        for (int i = 0; i < 5; i++){
            messageHandler.request(new Message("信息：" + i));
        }
    }
}