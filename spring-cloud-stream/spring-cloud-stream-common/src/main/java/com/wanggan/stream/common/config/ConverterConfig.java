/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.common.config;

import org.springframework.cloud.stream.annotation.StreamMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;

import com.wanggan.stream.common.converter.CustomMessageConverter;

/**
 * 自定义消息转换器注册bean
 *
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/23 09:22
 * @since JDK1.8
 */
@Configuration
public class ConverterConfig {
    @Bean
    @StreamMessageConverter
    public MessageConverter customMessageConverter() {
        return new CustomMessageConverter();
    }
}
