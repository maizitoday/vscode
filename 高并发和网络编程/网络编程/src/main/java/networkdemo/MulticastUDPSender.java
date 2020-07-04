package networkdemo;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-01-12 11:27:54
 * @LastEditTime : 2020-01-13 17:41:07
 * @LastEditors  : 麦子
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MulticastUDPSender {
    public static void main(String[] args) throws Exception {
        int mcPort = 12345;
        String mcIPStr = "230.0.0.0";
        DatagramSocket udpSocket = new DatagramSocket();

        InetAddress mcIPAddress = InetAddress.getByName(mcIPStr);
        byte[] msg = "Hello maizi 007".getBytes();
        DatagramPacket packet = new DatagramPacket(msg, msg.length);
        packet.setAddress(mcIPAddress);
        packet.setPort(mcPort);
        udpSocket.send(packet);

        System.out.println("Sent a  multicast message.");
        System.out.println("Exiting application");
        udpSocket.close();
    }
}