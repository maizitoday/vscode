package com.filter;

/*
 * @Description: 创建过滤管理器
 * @Author: 麦子
 * @Date: 2020-04-11 15:38:10
 * @LastEditTime: 2020-04-11 15:45:52
 * @LastEditors: 麦子
 */
public class FilterManager {

    FilterChain filterChain;

    public FilterManager(Target target) {
        filterChain = new FilterChain();
        filterChain.setTarget(target);
    }

    public void setFilter(Filter filter) {
        filterChain.addFilter(filter);
    }

    public void filterRequest(String request) {
        filterChain.execute(request);
    }
}