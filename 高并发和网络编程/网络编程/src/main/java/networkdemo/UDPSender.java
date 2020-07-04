package networkdemo;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-01-11 11:29:32
 * @LastEditTime : 2020-01-11 17:50:21
 * @LastEditors  : 麦子
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPSender {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket();
            String msgBody = "Hello word ";
            DatagramPacket dp = new DatagramPacket(msgBody.getBytes(), 0, msgBody.length(),
                    InetAddress.getByName("127.0.0.1"), 3000);
            ds.send(dp);
            ds.close();
            System.out.println("发送完成");
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}