package com.example.smart_doc_demo.doc;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-11-01 16:46:04
 * @LastEditTime: 2019-11-01 16:46:40
 * @LastEditors: 麦子
 */

import lombok.Data;

@Data
public class User {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户地址
     */
    private String userAddress;

    /**
     * 用户年龄
     */
    private int userAge;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * ipv6
     */
    private String ipv6;

    /**
     * 固定电话
     */
    private String telephone;
}