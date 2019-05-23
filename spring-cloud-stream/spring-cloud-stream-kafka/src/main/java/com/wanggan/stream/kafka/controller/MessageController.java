/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.kafka.controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wanggan.stream.common.message.MQObject;


/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/9 17:50
 * @since JDK1.8
 */
@RestController
public class MessageController {
    
    @Autowired
    Processor processor;
    
    @RequestMapping("/msg")
    public void msg(HttpServletResponse response) throws IOException {
        processor.output().send(MessageBuilder.withPayload(new MQObject<String>("hello kafka")).build());
        response.getWriter().write("ok");
        response.getWriter().flush();
    }
}
