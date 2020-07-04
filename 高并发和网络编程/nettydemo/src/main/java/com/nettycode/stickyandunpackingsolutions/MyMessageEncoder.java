package com.nettycode.stickyandunpackingsolutions;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-04-03 17:00:28
 * @LastEditTime: 2020-04-05 17:25:34
 * @LastEditors: 麦子
 */

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyMessageEncoder extends MessageToByteEncoder<MessageProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, ByteBuf out) throws Exception {
        System.out.println("MyMessageEncoder encode 方法被调用");
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getContent());
    }
}
