package com.nettycode.chartgroup;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-03-31 16:55:07
 * @LastEditTime: 2020-03-31 16:55:59
 * @LastEditors: 麦子
 */

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class GroupChatClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        
        System.out.println(msg.trim());

    }

}