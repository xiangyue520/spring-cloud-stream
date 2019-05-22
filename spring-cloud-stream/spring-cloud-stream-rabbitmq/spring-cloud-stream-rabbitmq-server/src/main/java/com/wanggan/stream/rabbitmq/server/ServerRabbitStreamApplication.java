/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.rabbitmq.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.ApplicationContext;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/8 09:37
 * @since JDK1.8
 */
@SpringBootApplication
@EnableBinding(Source.class)
public class ServerRabbitStreamApplication {
    
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ServerRabbitStreamApplication.class,args);
//        AmqpTemplate template = context.getBean(AmqpTemplate.class);
//        template.convertAndSend("rabbitmq","rabbitmq.hello","hello world");
//        template.convertAndSend("input","input.hello","hello world");
    }
}
