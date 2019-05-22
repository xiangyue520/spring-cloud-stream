/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.activemq.bind.properties;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/10 11:28
 * @since JDK1.8
 */
public class ActiveMQBindingProperties{
    private ActiveMQConsumerProperties consumer = new ActiveMQConsumerProperties();
    private ActiveMQProducerProperties producer = new ActiveMQProducerProperties();
    
    public ActiveMQConsumerProperties getConsumer() {
        return consumer;
    }
    
    public void setConsumer(ActiveMQConsumerProperties consumer) {
        this.consumer = consumer;
    }
    
    public ActiveMQProducerProperties getProducer() {
        return producer;
    }
    
    public void setProducer(ActiveMQProducerProperties producer) {
        this.producer = producer;
    }
}
