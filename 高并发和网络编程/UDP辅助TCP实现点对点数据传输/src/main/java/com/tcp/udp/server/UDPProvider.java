package com.tcp.udp.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.tcp.udp.clink.utils.JsonSerilizable;
import com.tcp.udp.constants.UDPConstants;

class UDPProvider {
    private static Provider PROVIDER_INSTANCE;

    static void start(int port) {
        stop();
        String sn = UUID.randomUUID().toString();
        Provider provider = new Provider(sn, port);
        provider.start();
        PROVIDER_INSTANCE = provider;
    }

    static void stop() {
        if (PROVIDER_INSTANCE != null) {
            PROVIDER_INSTANCE.exit();
            PROVIDER_INSTANCE = null;
        }
    }

    private static class Provider extends Thread {
        private final String sn;
        private final int port;
        private boolean done = false;
        private DatagramSocket ds = null;
        // 存储消息的Buffer
        final byte[] buffer = new byte[128];

        Provider(String sn, int port) {
            super();
            this.sn = sn;
            this.port = port;
        }

        @Override
        public void run() {
            super.run();
            System.out.println("UDPProvider Started.");
            try {
                // 监听30201 端口
                ds = new DatagramSocket(UDPConstants.PORT_SERVER);
                // 接收消息的Packet
                DatagramPacket receivePack = new DatagramPacket(buffer, buffer.length);
                while (!done) {
                    // 接收
                    ds.receive(receivePack);
                    // 打印接收到的信息与发送者的信息
                    // 发送者的IP地址
                    String clientIp = receivePack.getAddress().getHostAddress();
                    int clientPort = receivePack.getPort();
                    System.out.println(clientPort);
                    byte[] clientData = receivePack.getData();

                    Map<String, Object> map = JsonSerilizable.changeByteToMap(clientData);
                    Iterator<Entry<String, Object>> entries = map.entrySet().iterator();

                    String cmdVal = null;
                    int udpPort = 0;

                    while (entries.hasNext()) {
                        Map.Entry<String, Object> entry = entries.next();
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        if (key.equals("CMD")) {
                            cmdVal = (String) value;
                        } else if (key.equals("port")) {
                            udpPort = (int) value;
                        }
                    }
                    // 判断合法性
                    if (UDPConstants.UDP_PASSWORD_KEY.equals(cmdVal)) {
                        // 构建一份回送数据
                        Map<String, Object> responseMap = new HashMap<String, Object>();
                        // CMD命名
                        responseMap.put("sn", sn);
                        // 回送端口信息
                        responseMap.put("port", port);
                        responseMap.put("CMD", UDPConstants.UDP_PASSWORD_KEY);

                        byte[] responseBytes = JsonSerilizable.changeMapToByte(responseMap);

                        // 直接根据发送者构建一份回送信息
                        DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length,
                                receivePack.getAddress(), udpPort);
                        ds.send(responsePacket);
                        System.out.println("UDPProvider response to:" + clientIp + "\tport:"
                                + udpPort + "\tdataLen:" + responseBytes.length);
                    } else {
                        System.out.println("UDPProvider receive cmd nonsupport; cmd:" + cmdVal + "\tport:" + port);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                close();
            }
            // 完成
            System.out.println("UDPProvider Finished.");
        }

        private void close() {
            if (ds != null) {
                ds.close();
                ds = null;
            }
        }

        /**
         * 提供结束
         */
        void exit() {
            done = true;
            close();
        }
    }
}
