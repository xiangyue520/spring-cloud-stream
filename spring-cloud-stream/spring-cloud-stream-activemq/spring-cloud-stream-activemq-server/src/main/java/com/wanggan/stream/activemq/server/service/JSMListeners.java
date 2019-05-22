/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.activemq.server.service;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/9 13:17
 * @since JDK1.8
 */
@Configuration
@EnableJms
public class JSMListeners {
    /**
     * 点对点
     *
     * @return
     */
    @Bean
    public ActiveMQQueue queue() {
        return new ActiveMQQueue("jms-queue");
    }

    /**
     * 发布/订阅
     *
     * @return
     */
    @Bean
    public ActiveMQTopic topic() {
        return new ActiveMQTopic("custom-destination");
    }
}
