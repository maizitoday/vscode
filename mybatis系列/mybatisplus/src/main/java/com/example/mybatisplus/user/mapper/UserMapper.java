package com.example.mybatisplus.user.mapper;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-09-07 21:10:46
 * @LastEditTime: 2019-09-09 17:42:13
 * @LastEditors: 麦子
 */

import com.example.mybatisplus.user.entity.UserEntity;

import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@DS("mysql")
public interface UserMapper extends BaseMapper<UserEntity> {

    List<UserEntity> queryAll(Page<UserEntity> page);
}
