package com.example.mybatisplus.user.entity;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-09-07 21:10:46
 * @LastEditTime: 2019-09-09 16:11:26
 * @LastEditors: 麦子
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.mybatisplus.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user")
public class UserEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type=IdType.AUTO)
    private Long  id; 

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    @TableLogic
    private Integer flag;


}
