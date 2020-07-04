package com.nettycode.chartgroup;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-03-31 16:50:40
 * @LastEditTime: 2020-03-31 17:38:19
 * @LastEditors: 麦子
 */

import java.util.Scanner;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class GroupChatClient {
    // 属性
    private final String host;
    private final int port;

    public GroupChatClient(final String host, final int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
        final EventLoopGroup group = new NioEventLoopGroup();
        try {
            final Bootstrap bootstrap = new Bootstrap().group(group).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(final SocketChannel ch) throws Exception {

                            // 得到 pipeline
                            final ChannelPipeline pipeline = ch.pipeline();
                            // 加入相关 handler
                            pipeline.addLast("decoder", new StringDecoder());
                            pipeline.addLast("encoder", new StringEncoder());
                            // 加入自定义的 handler
                            pipeline.addLast(new GroupChatClientHandler());

                        }

                    });

            final ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            // 得到 channel
            final Channel channel = channelFuture.channel();
            System.out.println("-------" + channel.localAddress() + "--------");
            // 客户端需要输入信息，创建一个扫描器
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                final String msg = scanner.nextLine();
                // 通过 channel 发送到服务器端
                channel.writeAndFlush(msg + "\r\n");
            }

        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(final String[] args) throws Exception {

        new GroupChatClient("127.0.0.1", 7000).run();
    }
}