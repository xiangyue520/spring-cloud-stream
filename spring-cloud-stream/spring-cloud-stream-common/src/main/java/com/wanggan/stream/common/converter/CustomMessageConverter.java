/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.common.converter;


import org.springframework.messaging.Message;
import org.springframework.messaging.converter.AbstractMessageConverter;
import org.springframework.util.MimeType;

import com.wanggan.stream.common.message.MQObject;

/**
 * 自定义消息转换器
 *
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/23 09:20
 * @since JDK1.8
 */
public class CustomMessageConverter extends AbstractMessageConverter {
    public CustomMessageConverter() {
        super(new MimeType("application", "json"));
    }
    
    @Override
    protected boolean supports(Class<?> clazz) {
        return (MQObject.class.equals(clazz));
    }
    
    @Override
    protected Object convertFromInternal(Message<?> message, Class<?> targetClass, Object conversionHint) {
        Object payload = message.getPayload();
        return (payload instanceof MQObject ? payload : new MQObject((byte[]) payload));
    }
}
