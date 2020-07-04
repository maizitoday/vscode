package com.chatroom.client.bean;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-01-15 17:57:27
 * @LastEditTime : 2020-01-16 11:24:27
 * @LastEditors  : 麦子
 */

public class ServerInfo {
    private int port;
    private String address;

    public ServerInfo(int port, String ip) {
        this.port = port;
        this.address = ip;

    }

    /**
     * @return int return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return String return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
