package com.example.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(value = "index")
    public String showMsg(Model model) {
        model.addAttribute("index", "首页");
        return "index";
    }

    @GetMapping(value = "addUser")
    public String addUser() {

        return "/user/addUser";
    }

    @GetMapping(value = "listUser")
    public String listUser() {

        return "/user/listUser";
    }

    @GetMapping(value = "login")
    public String login() {

        return "/login";
    }

    @GetMapping(value = "noAuthor")
    public String unauthorizedUrl( Model model) {
        model.addAttribute("msg", "授权不通过");
        return "/noAuthor";
    }

    

    @GetMapping(value = "loginAction")
    public String loginAction(String username, String password, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        try {
            subject.login(token);
            // 登录成功后， 需要认证的页面就可以登录进入了。 
            model.addAttribute("msg", "登录成功");
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名不存在");
            return "/login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            return "/login";
        }
        return "/index";
    }

}