/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.activemq.client;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/9 12:47
 * @since JDK1.8
 */
@SpringBootApplication
@EnableBinding({Sink.class})
public class ClientActivemqStreamApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientActivemqStreamApplication.class,args);
    }
    
    @StreamListener(Sink.INPUT)
    public void handle(String user) throws IOException {
        System.out.println("Subscribe by @StreamListener:"+user);
    }
    
    @StreamListener("errorChannel")
    public void error(Message<?> message) {
        System.out.println("Handling ERROR: " + message);
    }
    
    
}
