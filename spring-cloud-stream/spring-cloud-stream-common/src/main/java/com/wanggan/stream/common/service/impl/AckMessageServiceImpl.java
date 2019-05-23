/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.common.service.impl;

import java.io.IOException;

import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;
import com.wanggan.stream.common.service.AckMessageService;

/**
 * 进行判断消息属于哪种类型的message,然后进行手动确认
 *
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/23 13:38
 * @since JDK1.8
 */
@Service
public class AckMessageServiceImpl implements AckMessageService {
    /**
     * rabbitmq消息头特征字段
     */
    private static final String RABBIT_HEADER = "amqp_channel";
    private static final String RABBIT_DELIVERY_TAG = "amqp_deliveryTag";
    /**
     * kafka消息头特征字段
     */
    private static final String KAFKA_ACKNOWLEDGMENT = "kafka_acknowledgment";
    /**
     * activemq消息头特征字段
     */
    private static final String ACTIVEMQ_ACKNOWLEDGE = "kafka_acknowledgment";
    
    @Override
    public void ackMessage(Message<?> message) throws IOException {
        MessageHeaders headers = message.getHeaders();
        //判断消息头是否为rabbitmq
        if (headers.containsKey(RABBIT_HEADER)){
            Long deliveryTag = headers.get(RABBIT_DELIVERY_TAG, Long.class);
            Channel channel = headers.get(RABBIT_HEADER,Channel.class);
            if(null !=channel) {
                channel.basicAck(deliveryTag, false);
            } else if(null != deliveryTag){
                throw new RuntimeException("rabbitmq manual ack message,please config properties: " +
                        "spring.cloud.stream.rabbit.bindings.input.consumer.acknowledge-mode=manual");
            }
            return;
        }
        //判断消息头是否为kafka
        if(headers.containsKey(KAFKA_ACKNOWLEDGMENT)){
            Acknowledgment acknowledgment = headers.get(KAFKA_ACKNOWLEDGMENT, Acknowledgment.class);
            if(null != acknowledgment){
                acknowledgment.acknowledge();
            }else{
                throw new RuntimeException("kafka manual ack message,please config proprties:" +
                        "spring.cloud.stream.kafka.bindings.input.consumer.autoCommitOffset=false");
            }
            return;
        }
        //判断消息头是否为activemq
        if(headers.containsKey(ACTIVEMQ_ACKNOWLEDGE)){
            //TODO 进行activemq的相关处理
        }
        
    }
}
