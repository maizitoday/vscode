package com.tcp.udp.server;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-01-14 11:01:00
 * @LastEditTime : 2020-01-14 16:25:50
 * @LastEditors  : 麦子
 */

import java.io.IOException;

import com.tcp.udp.constants.TCPConstants;

public class Server {
    public static void main(String[] args) {
        TCPServer tcpServer = new TCPServer(TCPConstants.PORT_SERVER);
        boolean isSucceed = tcpServer.start();
        if (!isSucceed) {
            System.out.println("Start TCP server failed!");
            return;
        }

        UDPProvider.start(TCPConstants.PORT_SERVER);

        try {
            //noinspection ResultOfMethodCallIgnored
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        UDPProvider.stop();
        tcpServer.stop();
    }
}
