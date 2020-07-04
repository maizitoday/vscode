package com.example.mybatisplus.user.entity;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-09-09 17:46:27
 * @LastEditTime: 2019-09-09 19:12:10
 * @LastEditors: 麦子
 */

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("stu")
public class Student {
    private int id;
    private String name;
    private String sex;
    private String age;
}