package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.standard.StandardDialect;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactory(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactory = new ShiroFilterFactoryBean();
        shiroFilterFactory.setSecurityManager(securityManager);

        /****
         * 
         * shiro内置过滤器，可以实现权限相关的拦截器 
         * 常用的过滤器 
         * anon: 无需认证（登录）可以访问 
         * authc: 必须认证才可以访问
         * user:如果使用rememberMe的功能可以直接访问 
         * perms: 该资源必须得到资源权限才可以访问 
         * role: 该资源必须得到角色权限才可以访问
         * 
         */

        Map<String, String> filtermap = new LinkedHashMap<String, String>();
        filtermap.put("/addUser", "perms[user:addUser]");
        filtermap.put("/listUser", "perms[user:listUser]");
        shiroFilterFactory.setFilterChainDefinitionMap(filtermap);

        /**
         * 修改返回登录页面地址
         */
        shiroFilterFactory.setLoginUrl("login");

        /***
         * 设置未授权页面
         */
        shiroFilterFactory.setUnauthorizedUrl("noAuthor");
        return shiroFilterFactory;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean(name = "userRealm")
    public UserRealm getRealm() {
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }

    /**
     * 用于处理在网页中使用标签
     * @return
     */
    @Bean
    public ShiroDialect getDialect() {

        return new ShiroDialect();
    }

}