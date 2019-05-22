/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.activemq.server.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/9 15:05
 * @since JDK1.8
 */
public interface UserMessage {
    String OUTPUT = "output";
    
    @Output(OUTPUT)
    MessageChannel output();
    
    @Output("activemq-output")
    MessageChannel activeMQOut();
}
