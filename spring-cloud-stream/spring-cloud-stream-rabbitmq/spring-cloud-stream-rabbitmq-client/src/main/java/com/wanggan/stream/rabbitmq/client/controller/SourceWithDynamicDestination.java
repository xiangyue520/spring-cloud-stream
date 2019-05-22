///**
// * Company
// * Copyright (C) 1992-2019 All Rights Reserved.
// */
//package com.wanggan.stream.rabbitmq.client.controller;
//
//import java.util.Collections;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
//import org.springframework.context.annotation.Bean;
//import org.springframework.expression.spel.standard.SpelExpressionParser;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.integration.annotation.ServiceActivator;
//import org.springframework.integration.channel.DirectChannel;
//import org.springframework.integration.router.ExpressionEvaluatingRouter;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.MessageHeaders;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
///**
// * @author wanggan@yinhai.com
// * @version 1.0
// * @date 2019/5/13 16:36
// * @since JDK1.8
// */
//@EnableBinding
//@Controller
//public class SourceWithDynamicDestination {
//
//    @Autowired
//    private BinderAwareChannelResolver resolver;
//
//
//    @RequestMapping(path = "/", method = RequestMethod.POST, consumes = "application/json")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public void handleRequest(@RequestBody String body, @RequestHeader(HttpHeaders.CONTENT_TYPE) Object contentType) {
//        sendMessage(body, contentType);
//    }
//
//    private void sendMessage(Object body, Object contentType) {
//        routerChannel().send(MessageBuilder.createMessage(body,
//                new MessageHeaders(Collections.singletonMap(MessageHeaders.CONTENT_TYPE, contentType))));
//    }
//
//    @Bean(name = "routerChannel")
//    public MessageChannel routerChannel() {
//        return new DirectChannel();
//    }
//
//    @Bean
//    @ServiceActivator(inputChannel = "routerChannel")
//    public ExpressionEvaluatingRouter router() {
//        ExpressionEvaluatingRouter router =
//                new ExpressionEvaluatingRouter(new SpelExpressionParser().parseExpression("payload.target"));
//        router.setDefaultOutputChannelName("default-output");
//        router.setChannelResolver(resolver);
//        return router;
//    }
//}