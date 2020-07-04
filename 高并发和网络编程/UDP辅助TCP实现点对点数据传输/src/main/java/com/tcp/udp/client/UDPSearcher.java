package com.tcp.udp.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.tcp.udp.client.bean.ServerInfo;
import com.tcp.udp.clink.utils.JsonSerilizable;
import com.tcp.udp.constants.UDPConstants;

public class UDPSearcher {
    private static final int LISTEN_PORT = UDPConstants.PORT_CLIENT_RESPONSE;

    public static ServerInfo searchServer(int timeout) {
        System.out.println("UDPSearcher Started.");

        // 成功收到回送的栅栏
        CountDownLatch receiveLatch = new CountDownLatch(1);
        Listener listener = null;
        try {
            // 发送广播
            listener = listen(receiveLatch);
            // 搜索10秒
            sendBroadcast();
            receiveLatch.await(timeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 完成
        System.out.println("UDPSearcher Finished.");
        if (listener == null) {
            return null;
        }
        List<ServerInfo> devices = listener.getServerAndClose();
        if (devices.size() > 0) {
            return devices.get(0);
        }
        return null;
    }

    private static Listener listen(CountDownLatch receiveLatch) throws InterruptedException {
        System.out.println("UDPSearcher start listen.");
        CountDownLatch startDownLatch = new CountDownLatch(1);
        Listener listener = new Listener(LISTEN_PORT, startDownLatch, receiveLatch);
        listener.start();
        startDownLatch.await();
        return listener;
    }

    private static void sendBroadcast() throws IOException {
        System.out.println("UDPSearcher sendBroadcast started.");

        // 作为搜索方，让系统自动分配端口
        DatagramSocket ds = new DatagramSocket();
        Map<String, Object> map = new HashMap<String, Object>();
        // CMD命名
        map.put("CMD", UDPConstants.UDP_PASSWORD_KEY);
        // 回送端口信息
        map.put("port", LISTEN_PORT);
        byte[] bytes = JsonSerilizable.changeMapToByte(map);

        // 直接构建packet
        DatagramPacket requestPacket = new DatagramPacket(bytes, bytes.length);
        // 广播地址
        requestPacket.setAddress(InetAddress.getByName("255.255.255.255"));
        // 设置服务器端口
        requestPacket.setPort(UDPConstants.PORT_SERVER);

        // 发送
        ds.send(requestPacket);
        ds.close();

        // 完成
        System.out.println("UDPSearcher sendBroadcast finished.");
    }

    private static class Listener extends Thread {
        private final int listenPort;
        private final CountDownLatch startDownLatch;
        private final CountDownLatch receiveDownLatch;
        private final List<ServerInfo> serverInfoList = new ArrayList<>();
        private final byte[] buffer = new byte[128];
        private boolean done = false;
        private DatagramSocket ds = null;

        private Listener(int listenPort, CountDownLatch startDownLatch, CountDownLatch receiveDownLatch) {
            super();
            this.listenPort = listenPort;
            this.startDownLatch = startDownLatch;
            this.receiveDownLatch = receiveDownLatch;
        }

        @Override
        public void run() {
            super.run();
            // 通知已启动
            startDownLatch.countDown();

            try {
                // 监听30202回送端口
                ds = new DatagramSocket(listenPort);
                // 构建接收实体
                DatagramPacket receivePack = new DatagramPacket(buffer, buffer.length);

                while (!done) {
                    // 接收
                    ds.receive(receivePack);

                    // 打印接收到的信息与发送者的信息
                    // 发送者的IP地址
                    String ip = receivePack.getAddress().getHostAddress();
                    byte[] data = receivePack.getData();

                    Map<String, Object> map = JsonSerilizable.changeByteToMap(data);
                    Iterator<Entry<String, Object>> entries = map.entrySet().iterator();

                    String cmdVal = null;
                    String snVal = null;
                    Integer serverPort = null;

                    while (entries.hasNext()) {
                        Map.Entry<String, Object> entry = entries.next();
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        if (key.equals("CMD")) {
                            cmdVal = (String) value;
                        } else if (key.equals("sn")) {
                            snVal = (String) value;
                        } else if (key.equals("port")) {
                            serverPort = (Integer) value;
                        }
                    }
                    if (UDPConstants.UDP_PASSWORD_KEY.equals(cmdVal)) {
                        ServerInfo info = new ServerInfo(serverPort, ip, snVal);
                        serverInfoList.add(info);
                    }
                    // 成功接收到一份
                    receiveDownLatch.countDown();
                    done = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                close();
            }
            System.out.println("UDPSearcher listener finished.");
        }

        private void close() {
            if (ds != null) {
                ds.close();
                ds = null;
                done = true;
            }
        }

        List<ServerInfo> getServerAndClose() {
            done = true;
            close();
            return serverInfoList;
        }
    }
}
