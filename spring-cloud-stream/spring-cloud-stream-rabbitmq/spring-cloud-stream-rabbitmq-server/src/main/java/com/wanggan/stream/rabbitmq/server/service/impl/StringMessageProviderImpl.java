/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.rabbitmq.server.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.wanggan.stream.rabbitmq.server.service.IMessageProvider;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/9 16:36
 * @since JDK1.8
 */
@Component
public class StringMessageProviderImpl implements IMessageProvider<String> {
    @Autowired
    private MessageChannel output;
    
    @Override
    public void send(String msg) {
        output.send(MessageBuilder.withPayload(msg).build());
    }
}
