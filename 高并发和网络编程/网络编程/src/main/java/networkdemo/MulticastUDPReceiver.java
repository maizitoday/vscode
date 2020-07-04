package networkdemo;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-01-12 11:28:40
 * @LastEditTime : 2020-01-12 12:12:58
 * @LastEditors  : 麦子
 */

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastUDPReceiver {
    public static void main(String[] args) throws Exception {
        // System.setProperty("java.net.preferIPv4Stack", "true");
        
        int mcPort = 12345;
        String mcIPStr = "230.0.0.0";
        MulticastSocket mcSocket = null;
        InetAddress mcIPAddress = null;
        mcIPAddress = InetAddress.getByName(mcIPStr);
        mcSocket = new MulticastSocket(mcPort);
        System.out.println("Multicast Receiver running at:" + mcSocket.getLocalSocketAddress());
        mcSocket.joinGroup(mcIPAddress);

        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

        System.out.println("Waiting for a  multicast message...");
        mcSocket.receive(packet);
        String msg = new String(packet.getData(), packet.getOffset(), packet.getLength());
        System.out.println("[Multicast  Receiver] Received:" + msg);

        mcSocket.leaveGroup(mcIPAddress);
        mcSocket.close();
    }
}