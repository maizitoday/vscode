package com.nio.chargroup;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-03-19 15:10:49
 * @LastEditTime: 2020-03-20 13:52:47
 * @LastEditors: 麦子
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class GroupChatServer {

    // 定义属性
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 6667;

    // 初始化工作
    public GroupChatServer() {

        try {
            // 得到选择器
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            // 绑定端口
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            // 设置非阻塞模式
            listenChannel.configureBlocking(false);
            // 将该 listenChannel 注册到 selector
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 监听
    public void listen() {
        try {
            while (true) {
                int count = selector.select();
                if (count > 0) {// 有事件处理
                    // 遍历得到 selectionKey 集合
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        // 取出 selectionkey
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()) {
                            SocketChannel sc = listenChannel.accept();
                            sc.configureBlocking(false);
                            // 将该 sc 注册到 seletor
                            sc.register(selector, SelectionKey.OP_READ);
                            // 提示
                            System.out.println(sc.getRemoteAddress() + " 上线 ");
                        }
                        if (key.isReadable()) {// 通道发送 read 事件，即通道是可读的状态
                            // 处理读 (专门写方法..)
                            readData(key);
                        }
                        // 当前的 key 删除，防止重复处理
                        iterator.remove();
                    }
                } else {
                    System.out.println("等待....");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally { // 发生异常处理....

        }
    }

    // 读取客户端消息
    private void readData(SelectionKey key) {
        // 取到关联的 channle
        SocketChannel channel = null;

        try { 
            // 得到 channel
            channel = (SocketChannel) key.channel(); 
            // 创建 buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 根据 count 的值做处理
            int count = channel.read(buffer); 
            if (count > 0) { 
                // 把缓存区的数据转成字符串
                String msg = new String(buffer.array()); 
                // 输出该消息
                System.out.println("form 客户端: " + msg);
                // 向其它的客户端转发消息(去掉自己), 专门写一个方法来处理
                sendInfoToOtherClients(msg, channel);
            }

        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + " 离线了.."); // 取消注册
                key.cancel(); // 关闭通道
                channel.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    // 转发消息给其它客户(通道)
    private void sendInfoToOtherClients(String msg, SocketChannel self) throws IOException {
        System.out.println("服务器转发消息中..."); 
        // 遍历 所有注册到 selector 上的 SocketChannel,并排除 self
        for (SelectionKey key : selector.keys()) {
            // 通过 key 取出对应的 SocketChannel
            Channel targetChannel = key.channel();
            // 排除自己
            if (targetChannel instanceof SocketChannel && targetChannel != self) {
                // 转型
                SocketChannel dest = (SocketChannel) targetChannel; // 将 msg 存储到 buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                // 将 buffer 的数据写入 通道
                dest.write(buffer);
            }
        }
    }

    public static void main(String[] args) {
        // 创建服务器对象
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}