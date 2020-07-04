package com.example.demo;

import entity.MaiziUser;
import org.springframework.stereotype.Service;

/**
 * @author yubo
 * @version V2.0
 * @Description:
 * @date 2019/4/17 下午6:18
 * @Company: cecsm.com
 * @Copyright Copyright (c) 2017
 */
@Service
public class ProvideService {

    public MaiziUser buildData(String foo)
    {
         MaiziUser user = new MaiziUser();
         user.setName("麦子->"+foo).setAge(28).setSex("男");
         return user;
    }
}
