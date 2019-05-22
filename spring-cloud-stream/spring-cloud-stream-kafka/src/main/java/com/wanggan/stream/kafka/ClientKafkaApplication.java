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
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;

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
    
//    @StreamListener(Processor.INPUT)
//    public void listen(@Payload String in, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
//        System.out.println(in + " received from partition " + partition);
//    }
//
    @StreamListener(Processor.INPUT)
    public void process(Message<?> message) {
        System.out.println(message.getPayload());
        Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
        if (acknowledgment != null) {
            System.out.println("Acknowledgment provided");
            acknowledgment.acknowledge();
        }
    }
    
//    @StreamListener(Processor.INPUT)
//    @SendTo(Processor.OUTPUT)
//    public String process(String message) {
//        System.out.println("receive kafka message:"+message);
//        return "i have receive msg,this is callback msg";
//    }
    
    
//    @StreamListener("myuser")
//    public void process1(String message) {
//        System.out.println("receive order message:"+message);
//    }

//    @StreamListener(Sink.INPUT)
//    public void process(Message<?> message) {
//        Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
//        if (acknowledgment != null) {
//            System.out.println("Acknowledgment provided");
//            acknowledgment.acknowledge();
//        }
//    }
}
