/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.rabbitmq.client.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/13 10:26
 * @since JDK1.8
 */
public interface MohrssService {
    @Input("myuser")
    SubscribableChannel user();
    
    @Output("hrssorder")
    MessageChannel order();
    
    @Output
    MessageChannel log();
    
}
