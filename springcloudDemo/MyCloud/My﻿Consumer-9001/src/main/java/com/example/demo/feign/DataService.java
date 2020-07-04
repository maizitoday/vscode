package com.example.demo.feign;

import entity.MaiziUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yubo
 * @version V2.0
 * @Description:
 * @date 2019/4/18 下午2:28
 * @Company: cecsm.com
 * @Copyright Copyright (c) 2017
 */
@Service
@FeignClient(value = "PROVIDE-DATA",fallback = MyHystrixClientFallbackFactory.class)
public interface DataService {

    @RequestMapping(value = "/provide")
    MaiziUser getDataByFeign();
}
