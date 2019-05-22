/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.activemq.bind.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.binder.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import com.wanggan.stream.activemq.bind.ActiveMQMessageChannelBinder;
import com.wanggan.stream.activemq.bind.properties.ActiveMQBinderConfigurationProperties;
import com.wanggan.stream.activemq.bind.properties.ActiveMQExtendedBindingProperties;
import com.wanggan.stream.activemq.bind.provision.ActiveMQTopicProvisioner;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/9 15:02
 * @since JDK1.8
 */
@Configuration
@ConditionalOnMissingBean(Binder.class)
@EnableConfigurationProperties({ ActiveMQBinderConfigurationProperties.class,
        ActiveMQExtendedBindingProperties.class, ActiveMQProperties.class })
public class ActiveMQStreamBinderAutoConfiguration {
    
    
    
    private final ActiveMQExtendedBindingProperties extendedBindingProperties;
    
    private final ActiveMQBinderConfigurationProperties activeMQBinderConfigurationProperties;
    
    private final ActiveMQProperties activeMQProperties;
    
    @Autowired(required = false)
    public ActiveMQStreamBinderAutoConfiguration(ActiveMQExtendedBindingProperties extendedBindingProperties,
                                                 ActiveMQBinderConfigurationProperties activeMQBinderConfigurationProperties,
                                                 ActiveMQProperties activeMQProperties) {
        this.extendedBindingProperties = extendedBindingProperties;
        this.activeMQBinderConfigurationProperties = activeMQBinderConfigurationProperties;
        this.activeMQProperties = activeMQProperties;
    }
    @Bean
    public ConnectionFactory connectionFactory(){
        return new ActiveMQConnectionFactory();
    }
    
    @Bean
    @ConditionalOnMissingBean(JmsTemplate.class)
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory){
        return new JmsTemplate(connectionFactory);
    }
    
    @Bean
    public ActiveMQTopicProvisioner provisioningProvider(){
        return new ActiveMQTopicProvisioner();
    }
    /**
     * 暴露自定义的 ActiveMQMessageChannelBinder Bean
     */
    @Bean
    public ActiveMQMessageChannelBinder activeMQMessageChannelBinder(ActiveMQTopicProvisioner provisioningProvider) {
        return new ActiveMQMessageChannelBinder(provisioningProvider,extendedBindingProperties,activeMQBinderConfigurationProperties,activeMQProperties);
    }
}