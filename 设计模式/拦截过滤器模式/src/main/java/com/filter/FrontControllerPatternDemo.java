package com.filter;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-04-11 15:39:52
 * @LastEditTime: 2020-04-11 15:48:48
 * @LastEditors: 麦子
 */

public class FrontControllerPatternDemo {

    public static void main(String[] args) {
        
        FilterManager filterManager = new FilterManager(new Target());
        filterManager.setFilter(new AuthenticationFilter());
        filterManager.setFilter(new DebugFilter());

        Client client = new Client();
        client.setFilterManager(filterManager);
        client.sendRequest("HOME");
    }

}