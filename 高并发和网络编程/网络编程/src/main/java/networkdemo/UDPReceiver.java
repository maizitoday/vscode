package networkdemo;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-01-11 11:29:45
 * @LastEditTime : 2020-01-12 11:42:15
 * @LastEditors  : 麦子
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPReceiver {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket(12345);
            byte[] buf = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buf, buf.length);

            ds.receive(dp);
            System.out.println(dp.getData().length);
            System.out.println(dp.getSocketAddress());
            System.out.println(ds.getInetAddress());
            String data = " form :" + dp.getAddress().getHostAddress()+" "+ new String(dp.getData(), 0, dp.getLength());
            System.out.println(data);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}