package com.chatroom.server;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-01-15 17:58:08
 * @LastEditTime : 2020-01-16 11:13:02
 * @LastEditors  : 麦子
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Server {
    public static void main(String[] args) throws IOException {
        TCPServer tcpServer = new TCPServer(30401);
        boolean isSucceed = tcpServer.start();
        if (!isSucceed) {
            System.out.println("Start TCP server failed!");
            return;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        do {
            str = bufferedReader.readLine();
            tcpServer.broadcast(str);
        } while (!"00bye00".equalsIgnoreCase(str));

        tcpServer.stop();
    }
}
