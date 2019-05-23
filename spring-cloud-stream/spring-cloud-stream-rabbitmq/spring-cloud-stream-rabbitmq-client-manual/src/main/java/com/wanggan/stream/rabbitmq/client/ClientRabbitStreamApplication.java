/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.rabbitmq.client;

import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;

import com.rabbitmq.client.Channel;
import com.wanggan.stream.common.message.MQObject;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/8 09:37
 * @since JDK1.8
 */
@SpringBootApplication
@EnableBinding({Processor.class})
public class ClientRabbitStreamApplication {
    
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ClientRabbitStreamApplication.class,args);
    }
    
    /**
     * 手动确认消息在头部用注解进行处理
     *
     * @param message
     * @param channel
     * @param deliveryTag
     * @throws Exception
     */
//    @StreamListener(Processor.INPUT)
//    public void receive(Message<MQObject> message,
//                        @Header(AmqpHeaders.CHANNEL) Channel channel,
//                        @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws Exception {
//
//        System.out.println("rabbitmq:"+message.getPayload().getContent());
//        try {
//            channel.basicAck(deliveryTag, false);//手动确认
//        } catch (Exception ex) {
//            throw new Exception(ex.getMessage());
//        }
//    }
    
    /**
     * 手动确认消息在代码中进行处理
     *
     * @param message
     * @throws Exception
     */
    @StreamListener(Processor.INPUT)
    public void receive(Message<MQObject> message) throws Exception {
        
        System.out.println("rabbitmq:"+message.getPayload().getContent());
        Channel channel = message.getHeaders().get(AmqpHeaders.CHANNEL,Channel.class);
        long deliveryTag = message.getHeaders().get(AmqpHeaders.DELIVERY_TAG,Long.class);
        try {
            channel.basicAck(deliveryTag, false);//手动确认
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    /**
     * 错误消息绑定
     * @param message
     */
    @StreamListener("errorChannel")
    public void error(Message<?> message) {
        System.out.println("body="+message.getPayload());
        System.out.println("header " + message.getHeaders());
    }
    
}
