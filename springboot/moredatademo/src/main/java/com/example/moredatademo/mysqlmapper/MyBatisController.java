package com.example.moredatademo.mysqlmapper;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-05-29 19:33:14
 * @LastEditTime: 2019-06-14 16:18:18
 * @LastEditors: 麦子
 */

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class MyBatisController {

    @Autowired
    private SchoolXmlMapper schoolXmlMapper;

    @GetMapping(value = "/mysql/{id}")
    public School getMethodName(@PathVariable("id") Integer id) {
        School school = schoolXmlMapper.getSchoolById(id);
        return school;
    }

}