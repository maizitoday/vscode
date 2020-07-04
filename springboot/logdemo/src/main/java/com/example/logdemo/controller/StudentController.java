package com.example.logdemo.controller;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-05-25 20:59:57
 * @LastEditTime: 2019-05-25 21:08:32
 * @LastEditors: 麦子
 */

import com.example.logdemo.entity.Student;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class StudentController {

    @RequestMapping("/msg")
    public Student msg() {
        Student student = new Student();
        student.name = "麦子";
        student.age = 20;
        log.info("我是日志");
        return student;
    }

}