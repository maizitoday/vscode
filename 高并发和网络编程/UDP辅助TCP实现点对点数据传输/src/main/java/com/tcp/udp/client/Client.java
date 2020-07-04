package com.tcp.udp.client;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-01-14 11:00:33
 * @LastEditTime : 2020-01-14 16:22:24
 * @LastEditors  : 麦子
 */

import java.io.IOException;

import com.tcp.udp.client.bean.ServerInfo;

public class Client {
    public static void main(String[] args) {
        ServerInfo info = UDPSearcher.searchServer(10000);
        System.out.println("Server:" + info);

        if (info != null) {
            try {
                TCPClient.linkWith(info );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
