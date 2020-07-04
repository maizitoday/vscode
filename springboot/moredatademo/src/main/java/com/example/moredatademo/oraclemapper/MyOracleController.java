package com.example.moredatademo.oraclemapper;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-06-14 16:16:45
 * @LastEditTime: 2019-06-14 16:18:11
 * @LastEditors: 麦子
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyOracleController {
    @Autowired
    private UserXmlMapper userXmlMapper;

    @GetMapping(value = "/oracle/{id}")
    public User getMethodName(@PathVariable("id") Integer id) {
        User user = userXmlMapper.getUserById(id);
        return user;
    }
}