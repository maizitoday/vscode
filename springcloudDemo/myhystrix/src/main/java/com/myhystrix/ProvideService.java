package com.myhystrix;

import entity.MaiziUser;
import org.springframework.stereotype.Service;

/**
 * @author yubo
 * @version V2.0
 * @Description:
 * @date 2019/4/18 下午8:33
 * @Company: cecsm.com
 * @Copyright Copyright (c) 2017
 */
@Service
public class ProvideService{

    public MaiziUser buildData()
    {
        MaiziUser user = new MaiziUser();
        user.setName("麦子").setAge(28).setSex("男");
        return user;
    }
}

