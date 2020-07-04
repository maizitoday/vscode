package com.provide;

import entity.MaiziUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yubo
 * @version V2.0
 * @Description:
 * @date 2019/4/18 上午5:31
 * @Company: cecsm.com
 * @Copyright Copyright (c) 2017
 */
@RestController
public class ProvideController {
	@Autowired
	private ProvideService service;

	@RequestMapping("provide")
	public MaiziUser provide(String foo) {

		return service.buildData(foo);
	}
}
