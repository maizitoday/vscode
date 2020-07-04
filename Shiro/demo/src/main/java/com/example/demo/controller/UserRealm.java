package com.example.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

public class UserRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        System.out.println("执行授权逻辑");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        authorizationInfo.addStringPermission(user.getPermission());
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        System.out.println("执行认证逻辑");

        String addUsername = "1";
        String listUsername = "2";
        String password = "123";

        UsernamePasswordToken token = (UsernamePasswordToken) arg0;
        if (!token.getUsername().equals(addUsername) && !token.getUsername().equals(listUsername)) {
            return null; // 抛出 UnknownAccountException 异常
        }


        User user = new User();
        if (token.getUsername().equals("1")) {
            String permission = "user:addUser";
            user.setUsername(addUsername);
            user.setPassword(password);
            user.setPermission(permission);
        } else if (token.getUsername().equals("2")) {
            String permission = "user:listUser";
            user.setUsername(addUsername);
            user.setPassword(password);
            user.setPermission(permission);
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, "");
        return info;
    }

}