/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.common.service;

import java.io.IOException;

import org.springframework.messaging.Message;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/23 13:37
 * @since JDK1.8
 */
public interface AckMessageService {
    void ackMessage(Message<?> message) throws IOException;
}
