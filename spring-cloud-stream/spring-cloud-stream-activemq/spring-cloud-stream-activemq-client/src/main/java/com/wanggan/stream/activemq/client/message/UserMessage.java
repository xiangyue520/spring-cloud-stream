/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.activemq.client.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/9 15:05
 * @since JDK1.8
 */
public interface UserMessage {
    String INPUT = "input";
    
    @Input(INPUT)
    SubscribableChannel input();
    
//    @Input("activemq-in")
//    SubscribableChannel activeMQIn();
}
