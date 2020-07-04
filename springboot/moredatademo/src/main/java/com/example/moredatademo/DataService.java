package com.example.moredatademo;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-06-14 16:31:15
 * @LastEditTime: 2019-06-14 17:57:06
 * @LastEditors: 麦子
 */

import java.util.HashMap;
import java.util.Map;

import com.example.moredatademo.mysqlmapper.School;
import com.example.moredatademo.mysqlmapper.SchoolXmlMapper;
import com.example.moredatademo.oraclemapper.User;
import com.example.moredatademo.oraclemapper.UserXmlMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DataService {

    @Autowired
    private SchoolXmlMapper schoolXmlMapper;

    @Autowired
    private UserXmlMapper userXmlMapper;

    
    public Map<String, Object> getAllData() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        School school = schoolXmlMapper.getSchoolById(2);
        User user = userXmlMapper.getUserById(2);
        resultMap.put("school", school);
        resultMap.put("user", user);
        return resultMap;
    }

    @Transactional
    public void updateData() {

        // mysql 这一条执行成功
        extracted();

        // oracle 这一条执行不成功
        extracted2();

    }

    private void extracted2() {
        HashMap<String, Object> oracleMap = new HashMap<String, Object>();
        oracleMap.put("id", 3);
        oracleMap.put("name111", "2");
        oracleMap.put("address", "2");
        userXmlMapper.updateById(oracleMap);
    }

    
    private void extracted() {
        HashMap<String, Object> mysqlMap = new HashMap<String, Object>();
        mysqlMap.put("id", 3);
        mysqlMap.put("name", "4444");
        mysqlMap.put("address", "4444");
        schoolXmlMapper.updateById(mysqlMap);
    }

}