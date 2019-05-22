/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.rabbitmq.client;

import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/8 09:37
 * @since JDK1.8
 */
@SpringBootApplication
@EnableBinding({Sink.class})
public class ClientRabbitStreamApplication {
    
    public static void main(String[] args) {
//        SubscribableChannel messageChannel = new DirectChannel(); // 1
//
//        messageChannel.subscribe(msg -> { // 2
//            System.out.println("receive: " + msg.getPayload());
//        });
//
//        messageChannel.send(MessageBuilder.withPayload("msg from alibaba").build()); // 3
    
        ApplicationContext context = SpringApplication.run(ClientRabbitStreamApplication.class,args);
    }
    
    
    
    @StreamListener("input")
    public void receive(Message<String> message,
                        @Header(AmqpHeaders.CONSUMER_QUEUE) String queue,
                        @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag,@Header(AmqpHeaders.RECEIVED_DELIVERY_MODE) String mode) throws Exception {

//        if (EntityMessage.getPayload() == null ||
//                StringUtils.isBlank(EntityMessage.getPayload().getId())) {
//            throw new Exception("数据有误");
//        }
        System.out.println("queue"+queue);
        System.out.println("tag"+deliveryTag);
        System.out.println("mode"+mode);
    
        System.out.println("message=" + message.getPayload());
//        try {
//            channel.basicAck(deliveryTag, false);//手动确认
//        } catch (Exception ex) {
//            throw new Exception(ex.getMessage());
//        }
    }
    
//    @StreamListener(Sink.INPUT)
//    public void handle(String msg) throws IOException {
//        System.out.println("Received: " + msg);
////        channel.basicAck(deliveryTag,false);
//
//
////        if(person instanceof Person){
////            System.out.println(((Person) person).getName());
////        }else {
////            System.out.println("Received: " + person);
////        }
//    }
//    @ServiceActivator(inputChannel = Sink.INPUT + ".hello.errors") //channel name 'input.myGroup.errors'
    @StreamListener("errorChannel")
    public void error(Message<?> message) {
        System.out.println("body="+message.getPayload());
        System.out.println("header " + message.getHeaders());
    }
    
}
