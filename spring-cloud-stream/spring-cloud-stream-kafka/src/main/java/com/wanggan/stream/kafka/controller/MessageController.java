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
    
    private static final Random RANDOM = new Random(System.currentTimeMillis());
    
    private static final String[] data = new String[] {
            "foo1", "bar1", "qux1",
            "foo2", "bar2", "qux2",
            "foo3", "bar3", "qux3",
            "foo4", "bar4", "qux4",
    };
    
//    @Resource(name = "hrssorder")
//    private MessageChannel channel;
//
//    @InboundChannelAdapter(channel = Processor.OUTPUT, poller = @Poller(fixedRate = "5000"))
//    public Message<?> generate() {
//        String value = data[RANDOM.nextInt(data.length)];
//        System.out.println("Sending: " + value);
//        return MessageBuilder.withPayload(value)
//                .setHeader("partitionKey", value)
//                .build();
//    }
    
    @RequestMapping("/msg")
    public void msg(HttpServletResponse response) throws IOException {
        processor.output().send(MessageBuilder.withPayload("hello").build());
        response.getWriter().write("ok");
        response.getWriter().flush();
        
//        channel.send(MessageBuilder.withPayload("hello world").build());
//        SubscribableChannel messageChannel = (SubscribableChannel) processor.output();
//        messageChannel.subscribe(msg->{
//            System.out.println("input:"+msg);
//        });
        
    }
}
