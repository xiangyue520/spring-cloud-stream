/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.activemq.server.service;


import javax.jms.Destination;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/9 12:51
 * @since JDK1.8
 */
public interface ProductService {
    void sendMessage(Destination destination, String message);
}
