///**
// * Company
// * Copyright (C) 1992-2019 All Rights Reserved.
// */
//package com.wanggan.stream.rabbitmq.client.config;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author wanggan@yinhai.com
// * @version 1.0
// * @date 2019/5/9 11:49
// * @since JDK1.8
// */
//@Configuration
//public class RabbitConfig {
//    @Bean
//    public Queue helloQueue(){
//        return new Queue("rabbitmq.hello");
//    }
//
//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange("rabbitmq");
//    }
//
////    //Topic交换机的绑定
//    @Bean
//    Binding bindingExchangeMessages(Queue queueMessage, TopicExchange exchange){
//        return BindingBuilder.bind(queueMessage).to(exchange).with("rabbitmq.#");
//    }
//
//
//}
