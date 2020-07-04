package com.example.moredatademo.mysqlmapper;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-05-29 19:27:51
 * @LastEditTime: 2019-06-14 17:05:37
 * @LastEditors: 麦子
 */

import java.util.HashMap;

public interface SchoolXmlMapper {
    public School getSchoolById(Integer id);

    public void updateById(HashMap<String,Object> map);
    
}