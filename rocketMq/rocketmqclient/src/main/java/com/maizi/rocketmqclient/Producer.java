package com.maizi.rocketmqclient;
/*
 * @Description  : 请输入....
 * @Author       : 麦子
 * @Date         : 2020-05-12 17:33:39
 * @FilePath     : /rocketMq/rocketmqclient/src/main/java/com/maizi/rocketmqclient/Producer.java
 * @LastEditTime : 2020-05-12 18:55:17
 * @LastEditors  : Do not edit
 */

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class Producer {

    public static void main(String[] args)
            throws MQClientException, RemotingException, MQBrokerException, InterruptedException {
        // 创建一个消息生产者，并设置一个消息生产者组
        DefaultMQProducer producer = new DefaultMQProducer("maizi_producer_group");

        // 指定 NameServer 地址
        // producer.setSendMsgTimeout(6000);
        producer.setNamesrvAddr("192.168.0.2:9876");

        // 初始化 Producer，整个应用生命周期内只需要初始化一次
        producer.start();

        for (int i = 0; i < 10; i++) {
            // 创建一条消息对象，指定其主题、标签和消息内容
            Message msg = new Message("topic_maizi_example_java" /* 消息主题名 */, "TagA" /* 消息标签 */,
                    ("Hello Java demo RocketMQ " + i).getBytes() /* 消息内容 */
            );
            SendResult sendResult = producer.send(msg);
            System.out.println(sendResult);
        }
        // 一旦生产者实例不再被使用则将其关闭，包括清理资源，关闭网络连接等
        // 把这个隐掉后，才控制台才可以看到你的生产组
        // producer.shutdown(); // 自动将这个组删掉了
    }

}
