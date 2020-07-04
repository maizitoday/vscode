package com.example.mybatisplus.user.controller;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-09-07 21:10:46
 * @LastEditTime: 2020-02-23 23:29:55
 * @LastEditors: 麦子
 */

import com.example.mybatisplus.common.BaseController;
import com.example.mybatisplus.user.entity.UserEntity;
import com.example.mybatisplus.user.mapper.StudentMapper;
import com.example.mybatisplus.user.mapper.UserMapper;
import com.example.mybatisplus.user.service.impl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StudentMapper studentMapper;

    @GetMapping(value = "showMsg")
    public String showMsg() {

        UserEntity userEntity = new UserEntity();
        userEntity.setAge(100);
        userEntity.setName("麦子-100");
        userEntity.setEmail("maizi@sina.com-100");
        boolean flag = userService.save(userEntity);
        System.out.println(userEntity.getId());

        // boolean bloolean = userService.removeById(10);
        // System.out.println(bloolean);

        // List<UserEntity> list = userService.list();
        // list.forEach(System.out::println);

        // UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<UserEntity> ();
        // updateWrapper.set("name", "小强---0001");
        // updateWrapper.set("age", 1);
        // updateWrapper.set("email", "0001@sina.com");
        // updateWrapper.eq("id", 1);
        // boolean flag = userService.update(null, updateWrapper);

        // UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<UserEntity> ();
        // UserEntity userEntity = new UserEntity();
        // userEntity.setName("name") ;
        // userEntity.setEmail("email@sina.com");
        // userEntity.setAge(1000);
        // updateWrapper.eq("id", 1);

        // boolean flag = userService.update(userEntity, updateWrapper);
        // System.out.println(flag);

        // QueryWrapper<UserEntity> wrapper = new QueryWrapper<UserEntity>();
        // wrapper.eq("id", 9).select("name");
        // List<UserEntity> list = userService.list(wrapper);
        // list.forEach(System.out::println);

        // System.out.println("欢迎你");

        // UserEntity userEntity = new UserEntity();
        // userEntity.setAge(10);
        // userEntity.setName("麦子-10");
        // userEntity.setEmail("maizi@sina.com-10");
        // // int count = userMapper.insert(userEntity);
        // // System.out.println("插入返回主键： "+userEntity.getId());

        // UserEntity userEntity2 = userMapper.selectById(8);
        // System.out.println("查询返回对象:"+userEntity2);

        // userEntity.setId(new Long(1));
        // int count = userMapper.updateById(userEntity);
        // System.out.println("updateById:"+count+"---"+userEntity.getName());

        // int index = userMapper.deleteById(new Long(8));
        // System.out.println("index:"+index);

        // List<UserEntity> users = userMapper.selectList(null);
        // users.forEach(System.out::println);

        // Page<UserEntity> page = new Page<UserEntity>();
        // page.setSize(4);
        // page.setCurrent(1L);

        // List<UserEntity> users = userMapper.queryAll(page);
        // users.forEach(System.out::println);

        // Page<UserEntity> userEntityPage =
        // (Page<UserEntity>)userMapper.selectPage(page, null);
        // userEntityPage.getRecords().forEach(System.out::println);
        // System.out.println(userEntityPage.getTotal()+"=="+userEntityPage.getPages());

        // Page<UserEntity> userEntityPage = (Page<UserEntity>)userService.page(page);
        // userEntityPage.getRecords().forEach(System.out::println);

        // int count = userMapper.deleteById(1);
        // System.out.println(count);

       /*  boolean flag = userService.removeById(3);
        System.out.println(flag); */

        // userService.list().forEach(System.out::println);

        // studentMapper.selectList(null).forEach(System.out::println);

        return "hello controller";
    }

}
