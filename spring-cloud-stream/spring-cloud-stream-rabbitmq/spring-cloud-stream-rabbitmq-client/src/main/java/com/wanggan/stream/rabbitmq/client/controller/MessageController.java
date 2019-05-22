/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.rabbitmq.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
   private BinderAwareChannelResolver resolver;
   
//   @Autowired
//   private Source source;
    
    @RequestMapping("/msg")
    public void msg(@RequestParam("path") String path,@RequestParam("msg") String msg){
        resolver.resolveDestination(path).send(MessageBuilder.withPayload(msg).build());
    }
    
//    @RequestMapping("/hello")
//    public void hello(@RequestParam("msg") String msg){
//        source.output().send(MessageBuilder.withPayload(new Person(msg)).build());
//    }
    
}
