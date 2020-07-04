package com.example.databasedemo.mybatis;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-05-29 19:33:14
 * @LastEditTime: 2019-06-03 18:43:32
 * @LastEditors: 麦子
 */

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class MyBatisController {

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private SchoolXmlMapper schoolXmlMapper;


    @GetMapping(value="/get/{id}")
    public School getMethodName(@PathVariable("id") Integer id) {
        School school = schoolXmlMapper.getSchoolById(id);
        return school;
    }
    


    @GetMapping("/school/{id}")
    public School getSchool(@PathVariable("id") Integer id) {
        School school = schoolMapper.getSchoolById(id);
        return school;
    }

    @DeleteMapping("/school/{id}")
    public int delSchool(@PathVariable("id") int id) {
        int count = schoolMapper.deleteSchoolById(id);
        return count;
    }

    @PostMapping(value = "school")
    public int addSchool(@RequestBody School school) {
        int count = schoolMapper.insertSchool(school);
        return count;
    }

    @PutMapping(value = "school")
    public int putMethodName(@RequestBody School school) {
        int count = schoolMapper.updateSchool(school);
        return count;
    }

}