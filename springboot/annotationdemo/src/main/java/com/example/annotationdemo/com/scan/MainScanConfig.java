package com.example.annotationdemo.com.scan;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-06-11 18:39:34
 * @LastEditTime: 2019-06-11 18:48:04
 * @LastEditors: 麦子ASDFAFD
 */

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value="com.example.annotationdemo.com.scan")
public class MainScanConfig {
    
}