package com.nettycode.base;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-03-31 16:22:36
 * @LastEditTime: 2020-03-31 17:45:29
 * @LastEditors: 麦子
 */

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class NettyByteBuf01 {

    public static void main(String[] args) {
        // 创建一个 ByteBuf
        // 1. 创建 对象，该对象包含一个数组 arr , 是一个 byte[10]
        // 2. 在 netty 的 buffer 中，不需要使用 flip 进行反转 底层维护了 readerindex 和 writerIndex
        // 3. 通过 readerindex 和 writerIndex 和 capacity， 将 buffer 分成三个区域
        // 0---readerindex 已经读取的区域
        // readerindex---writerIndex ， 可读的区域
        // writerIndex -- capacity, 可写的区域

        ByteBuf buffer = Unpooled.buffer(10);
        for (int i = 0; i < 10; i++) {

            buffer.writeByte(i);
        }
        System.out.println("capacity=" + buffer.capacity());// 10

        for (int i = 0; i < buffer.capacity(); i++) {

            System.out.println(buffer.readByte());
        }
        System.out.println("执行完毕");
    }

}