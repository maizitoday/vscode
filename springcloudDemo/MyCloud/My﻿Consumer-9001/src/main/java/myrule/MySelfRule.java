package myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;

/**
 * @author yubo
 * @version V2.0
 * @Description:
 * @date 2019/4/18 上午5:50
 * @Company: cecsm.com
 * @Copyright Copyright (c) 2017
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule()
    {
        //return new RandomRule();// Ribbon默认是轮询，我自定义为随机
        return new RoundRobinRule();//  轮询
        //return new RandomRule_ZY();// 我自定义为每台机器5次
    }
}
