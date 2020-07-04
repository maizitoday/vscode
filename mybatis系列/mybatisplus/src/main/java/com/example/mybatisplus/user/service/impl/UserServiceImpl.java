package com.example.mybatisplus.user.service.impl;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-09-07 21:10:46
 * @LastEditTime: 2019-09-09 17:02:15
 * @LastEditors: 麦子
 */

import com.example.mybatisplus.user.entity.UserEntity;
import com.example.mybatisplus.user.mapper.UserMapper;
import com.example.mybatisplus.user.service.IUserService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author maizi
 * @since 2019-09-07
 */
@Service
@DS("master")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

}
