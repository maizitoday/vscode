package com.example.databasedemo.jpa;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-05-30 14:41:42
 * @LastEditTime: 2019-05-30 16:02:31
 * @LastEditors: 麦子
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//继承JpaRepository来完成对数据库的操作
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
}