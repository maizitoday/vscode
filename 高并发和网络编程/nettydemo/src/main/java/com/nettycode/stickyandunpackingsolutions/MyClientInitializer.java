package com.nettycode.stickyandunpackingsolutions;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-04-03 17:00:28
 * @LastEditTime: 2020-04-05 16:09:07
 * @LastEditors: 麦子
 */

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MyMessageEncoder()); //加入编码器
        pipeline.addLast(new MyMessageDecoder()); //加入解码器
        pipeline.addLast(new MyClientHandler());
    }
}
