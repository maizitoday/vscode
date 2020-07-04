package com.filter;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-04-11 15:35:58
 * @LastEditTime: 2020-04-11 15:42:17
 * @LastEditors: 麦子
 */

import java.util.ArrayList;
import java.util.List;

// 创建过滤器链
public class FilterChain {
    private List<Filter> filters = new ArrayList<Filter>();
    private Target target;

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public void execute(String request) {
        for (Filter filter : filters) {
            filter.execute(request);
        }
        // 这个是真正的业务处理
        target.execute(request);
    }

    public void setTarget(Target target) {
        this.target = target;
    }

}