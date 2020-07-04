package com.myhystrix;

import entity.MaiziUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author yubo
 * @version V2.0
 * @Description:
 * @date 2019/4/18 下午8:16
 * @Company: cecsm.com
 * @Copyright Copyright (c) 2017
 */
@RestController
public class ConsumerController {

	@Autowired
	private ProvideService provideService;

	@RequestMapping(value = "provide", method = RequestMethod.GET)
	private MaiziUser provide() {
		return provideService.buildData();
	}


}
