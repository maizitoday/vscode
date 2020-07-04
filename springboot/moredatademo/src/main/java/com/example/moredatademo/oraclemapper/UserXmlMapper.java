package com.example.moredatademo.oraclemapper;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-06-14 16:14:33
 * @LastEditTime: 2019-06-14 16:16:27
 * @LastEditors: 麦子
 */

import java.util.HashMap;

public interface UserXmlMapper {
    
    public User getUserById(Integer id);

    public void updateById(HashMap<String,Object> map);
}