/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.rabbitmq.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;

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
        SpringApplication.run(ClientRabbitStreamApplication.class,args);
    }
    
    @StreamListener(Processor.INPUT)
    public void receive(Message<MQObject> message) {
        System.out.println("rabbitmq:"+message.getPayload().getContent());
    }

    

    @StreamListener("errorChannel")
    public void error(Message<?> message) {
        System.out.println("body="+message.getPayload());
        System.out.println("header " + message.getHeaders());
    }
    
}
