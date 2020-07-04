package com.nettycode.stickingandunpacking;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-04-03 16:49:26
 * @LastEditTime: 2020-04-03 16:49:58
 * @LastEditors: 麦子
 */

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MyClientHandler());
    }
}
