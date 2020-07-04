package com.chatroom.client;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-01-15 17:57:27
 * @LastEditTime : 2020-01-16 11:24:41
 * @LastEditors  : 麦子
 */


import java.io.IOException;

import com.chatroom.client.bean.ServerInfo;

public class Client {
    public static void main(String[] args) {
        ServerInfo info = new ServerInfo(30401,"127.0.0.1");
        if (info != null) {
            try {
                TCPClient.linkWith(info);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
