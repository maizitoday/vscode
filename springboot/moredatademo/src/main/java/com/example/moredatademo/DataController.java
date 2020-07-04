package com.example.moredatademo;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-06-14 16:25:56
 * @LastEditTime: 2019-06-14 16:47:47
 * @LastEditors: 麦子
 */

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class DataController {

    @Autowired
    private DataService dataservice;

    @GetMapping(value = "alldata")
    public Map<String, Object> getAllData() {
        return dataservice.getAllData();
    }

    @GetMapping(value = "updateData")
    public void updateMysqlData() {
        dataservice.updateData();
    }

}