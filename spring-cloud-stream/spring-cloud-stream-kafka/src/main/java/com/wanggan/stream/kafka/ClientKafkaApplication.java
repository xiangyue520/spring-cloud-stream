/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.kafka;

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
 * @date 2019/5/9 15:35
 * @since JDK1.8
 */
@SpringBootApplication
//@EnableBinding({Processor.class, MohrssService.class})
@EnableBinding({Processor.class})
public class ClientKafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientKafkaApplication.class, args);
    }
    
    /**
     * 自动确认消息
     * @param message
     */
    @StreamListener(Processor.INPUT)
    public void listen(Message<MQObject> message) {
        System.out.println("kafka content:"+message.getPayload().getContent());
    }
    
//    /**
//     * 手动确认消息,配置文件见application-kafka-manual.yml
//     * @param message
//     */
//    @StreamListener(Processor.INPUT)
//    public void process(Message<MQObject<String>> message) {
//
//        System.out.println("kafka manual confirm msg:"+message.getPayload().getContent());
//        Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
//        if (acknowledgment != null) {
//            System.out.println("Acknowledgment provided");
//            acknowledgment.acknowledge();
//        }
//    }
    
    @StreamListener("errorChannel")
    public void error(Message<?> message) {
        System.out.println("kafka error");
        System.out.println("body="+message.getPayload());
        System.out.println("header " + message.getHeaders());
    }
    
}
