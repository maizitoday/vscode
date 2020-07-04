package com.example.databasedemo.mybatis;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-05-29 19:27:51
 * @LastEditTime: 2019-05-30 13:45:03
 * @LastEditors: 麦子
 */

import java.util.List;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

public interface SchoolMapper {
    
    @Select("select * from school where id=#{id}")
    public School getSchoolById(Integer id);

    @Delete("delete from school where id=#{id}")
    public int deleteSchoolById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into school(name,address) values(#{name},#{address})")
    public int insertSchool(School school);

    @Transactional
    @Update("update school set name=#{name} where id=#{id}")
    public int updateSchool(School school);
}