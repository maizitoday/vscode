package com.example.databasedemo.durid;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-05-29 18:02:43
 * @LastEditTime : 2020-06-20 11:05:20
 * @LastEditors  : Do not edit
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidConfig {
    /***
     * 
     * @return 配置Druid的监控,配置一个管理后台的Servlet
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();

        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "admin");
        initParams.put("allow", "");// IP白名单 (没有配置或者为空，则允许所有访问)
        initParams.put("deny", "192.168.15.21"); // IP黑名单 (存在共同时，deny优先于allow)

        bean.setInitParameters(initParams);
        return bean;
    }

    /***
     * 
     * 配置一个web监控的filter
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}