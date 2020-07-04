package com.filter;
/*
 * @Description: 创建客户端
 * @Author: 麦子
 * @Date: 2020-04-11 15:38:56
 * @LastEditTime: 2020-04-11 15:39:35
 * @LastEditors: 麦子
 */

public class Client {
    FilterManager filterManager;

    public void setFilterManager(FilterManager filterManager) {
        this.filterManager = filterManager;
    }

    public void sendRequest(String request) {
        filterManager.filterRequest(request);
    }
}