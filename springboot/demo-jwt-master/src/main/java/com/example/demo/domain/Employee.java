package com.example.demo.domain;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2017-11-16 19:14:18
 * @LastEditTime: 2017-11-16 19:14:18
 * @LastEditors: 麦子
 */

import lombok.Data;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 * Date: 2017/11/16
 * Time: 10:31
 * Email: hyf_spring@163.com
 *
 * @author huyunfan
 */
@Data
@ToString
public class Employee {
    private String id;
    private String username;
    private String password;

    public Employee() {
        this.setId("testId");
        this.setUsername("testUsername");
        this.setPassword("testPassword");
    }
}
