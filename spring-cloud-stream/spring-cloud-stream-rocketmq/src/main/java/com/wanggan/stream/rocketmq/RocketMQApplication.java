/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.rocketmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/14 09:54
 * @since JDK1.8
 */
@SpringBootApplication
@EnableBinding(Processor.class)
public class RocketMQApplication {
    @Autowired
    private Processor processor;
    
    
    @Transformer(inputChannel = Processor.INPUT,outputChannel = Processor.OUTPUT)
    public String trans(String in){
        return in +" world";
    }
}
