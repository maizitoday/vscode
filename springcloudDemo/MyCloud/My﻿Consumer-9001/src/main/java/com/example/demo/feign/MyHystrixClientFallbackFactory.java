package com.example.demo.feign;

import entity.MaiziUser;
import org.springframework.stereotype.Component;

/**
 * @author yubo
 * @version V2.0
 * @Description:
 * @date 2019/4/18 下午10:56
 * @Company: cecsm.com
 * @Copyright Copyright (c) 2017
 */
@Component
public class MyHystrixClientFallbackFactory implements DataService{
    public MaiziUser getDataByFeign() {
         return new MaiziUser().setName("服务已经停止").setAge(11).setSex("男");
    }
}
