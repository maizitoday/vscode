package com.example.securitydemo;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-06-06 22:51:49
 * @LastEditTime: 2019-06-07 02:06:10
 * @LastEditors: 麦子
 */

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http);

        /***
         * 
         * 定值授权规则
         */
        http.authorizeRequests().antMatchers("/myLogin").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/one/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/two/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN");
        /***
         * 
         * 开启自动配置的登录功能，效果，如果没有登录，没有权限就会来到登录页面 1. /login来到登录页面 2. 重定向到/login?error 表示登录失败
         * 3. 还有很多的其他控制
         */
        http.formLogin();

        /***
         *  开启自动配置的注销功能， 访问 /logout 表示用户注销，清空session,
         */
        http.logout().logoutSuccessUrl("/");

        /**
         *  开启记住我功能
         */
        http.rememberMe();

    }

    /**
     * 定义认证规则
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // super.configure(auth);
        // 从数据库获取，或者从内存获取
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("root").password(new BCryptPasswordEncoder().encode("root")).roles("USER", "ADMIN").and()
                .withUser("normal").password(new BCryptPasswordEncoder().encode("normal")).roles("USER");
    }

}