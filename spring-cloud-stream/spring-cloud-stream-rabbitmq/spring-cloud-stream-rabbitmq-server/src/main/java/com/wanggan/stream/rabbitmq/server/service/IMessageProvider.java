/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.rabbitmq.server.service;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/9 16:35
 * @since JDK1.8
 */
public interface IMessageProvider<T> {
     void send(T  msg);
}
