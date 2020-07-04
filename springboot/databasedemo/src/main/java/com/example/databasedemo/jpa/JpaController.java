package com.example.databasedemo.jpa;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-05-30 14:58:11
 * @LastEditTime: 2019-05-30 15:28:17
 * @LastEditors: 麦子
 */

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class JpaController {
    
    @Autowired
    private UserRepository  userRepository;

    @GetMapping(value="/jpa/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.get();
    }
    
    @PostMapping(value="/jpa")
    public User addUser(@RequestBody User user) {
        User resultUser = userRepository.save(user);
        return resultUser;
    }

}