package com.maizi.rocketmqclient;
/*
 * @Description  : 请输入....
 * @Author       : 麦子
 * @Date         : 2020-05-12 19:01:47
 * @FilePath     : /rocketMq/rocketmqclient/src/main/java/com/maizi/rocketmqclient/Consumer.java
 * @LastEditTime : 2020-05-14 09:01:19
 * @LastEditors  : Do not edit
 */

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import cn.hutool.core.date.DateUtil;

public class Consumer {

    public static void main(String[] args) throws MQClientException {
        // 创建一个消息消费者，并设置一个消息消费者组
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("maizi_producer_group");
        // 指定 NameServer 地址
        consumer.setNamesrvAddr("192.168.0.2:9876");
        // 设置 Consumer 第一次启动时从队列头部开始消费还是队列尾部开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // 订阅指定 Topic 下的所有消息
        consumer.subscribe("topic_maizi_example_java", "*");

        // 注册消息监听器
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
                // 默认 list 里只有一条消息，可以通过设置参数来批量接收消息
                if (list != null) {
                    for (MessageExt ext : list) {
                        System.out.println(DateUtil.now() + new String(ext.getBody()));
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        // 消费者对象在使用之前必须要调用 start 初始化
        consumer.start();
        System.out.println("消息消费者已启动");
    }

}