package com.example.mybatisplus.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.user.entity.Student;
import com.baomidou.dynamic.datasource.annotation.DS;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-09-09 17:46:07
 * @LastEditTime: 2019-09-09 17:52:13
 * @LastEditors: 麦子
 */
@DS("oracle")
public interface StudentMapper extends BaseMapper<Student> {
    

}