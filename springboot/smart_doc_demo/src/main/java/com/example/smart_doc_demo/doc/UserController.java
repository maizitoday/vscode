package com.example.smart_doc_demo.doc;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-11-01 16:46:58
 * @LastEditTime: 2019-11-01 17:32:15
 * @LastEditors: 麦子
 */

import org.springframework.web.bind.annotation.GetMapping;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 添加用户
     * @param user
     * @return
     */
    @GetMapping("/add")
    public User addUser(User user){
        return new User();
    }
}