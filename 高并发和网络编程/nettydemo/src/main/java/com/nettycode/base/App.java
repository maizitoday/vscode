package com.nettycode.base;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-03-20 14:18:09
 * @LastEditTime: 2020-04-03 15:54:01
 * @LastEditors: 麦子
 */
public class App extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyByteToLongDecoder2 被调用"); 
        //在 ReplayingDecoder 不需要判断数据是否足够读取，内部会进行处理判断
        out.add(in.readLong());
    }
 
}
