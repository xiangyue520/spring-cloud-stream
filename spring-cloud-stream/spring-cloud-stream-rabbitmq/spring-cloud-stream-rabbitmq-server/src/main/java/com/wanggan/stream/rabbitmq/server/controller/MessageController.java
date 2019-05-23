/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.rabbitmq.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wanggan.stream.common.message.MQObject;
import com.wanggan.stream.rabbitmq.server.service.IMessageProvider;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/9 17:50
 * @since JDK1.8
 */
@RestController
public class MessageController {
    
//    @Autowired
//    IMessageProvider messageProvider;
    
    @Autowired
    private Source output;
    
    @RequestMapping("/msg")
    public void msg(){
        output.output().send(MessageBuilder.withPayload(new MQObject<String>("hello rabbit")).build());
    }
}
