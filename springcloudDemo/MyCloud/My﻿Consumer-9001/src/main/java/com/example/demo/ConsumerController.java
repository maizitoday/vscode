package com.example.demo;

import entity.MaiziUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.feign.DataService;

/**
 * @author yubo
 * @version V2.0
 * @Description:
 * @date 2019/4/17 下午6:44
 * @Company: cecsm.com
 * @Copyright Copyright (c) 2017
 */
@RestController
public class ConsumerController {

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private DataService dataService;


//    private static final String  URL = "http://PROVIDE-DATA/";

//    @RequestMapping("getData")
//    private MaiziUser getData()
//    {
//        return restTemplate.getForObject(URL+ "provide",MaiziUser.class);
//    }

    @RequestMapping("getDataByFeign")
    private MaiziUser getDataByFeign()
    {
        return dataService.getDataByFeign();
    }
}
