package com.nettycode.stickyandunpackingsolutions;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-04-03 17:00:27
 * @LastEditTime: 2020-04-12 10:03:29
 * @LastEditors: 麦子
 */


//协议包
public class MessageProtocol {
    private int len; //关键
    private byte[] content;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
