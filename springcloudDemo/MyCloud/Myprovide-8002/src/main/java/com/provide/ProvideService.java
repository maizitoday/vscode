package com.provide;

import entity.MaiziUser;
import org.springframework.stereotype.Service;

/**
 * @author yubo
 * @version V2.0
 * @Description:
 * @date 2019/4/18 上午5:31
 * @Company: cecsm.com
 * @Copyright Copyright (c) 2017
 */
@Service
public class ProvideService {

	public MaiziUser buildData(String foo) {
		MaiziUser user = new MaiziUser();
		user.setName("小强++++" + foo).setAge(28).setSex("女");
		return user;
	}
}
