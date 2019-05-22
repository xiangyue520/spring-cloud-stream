/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.activemq.server.service;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/9 12:50
 * @since JDK1.8
 */

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Override
    public void sendMessage(Destination destination, String message) {
        jmsMessagingTemplate.convertAndSend(destination,message);
    }
}