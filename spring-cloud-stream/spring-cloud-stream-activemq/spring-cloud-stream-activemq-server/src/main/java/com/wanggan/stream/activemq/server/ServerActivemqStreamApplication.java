/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.activemq.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/9 12:47
 * @since JDK1.8
 */
@SpringBootApplication
@EnableBinding(Source.class)
public class ServerActivemqStreamApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ServerActivemqStreamApplication.class,args);
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        jmsTemplate.convertAndSend("custom-destination","hello world");
    }
}
