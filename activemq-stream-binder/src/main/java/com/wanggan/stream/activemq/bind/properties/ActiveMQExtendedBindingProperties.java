/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.activemq.bind.properties;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.stream.binder.ExtendedBindingProperties;

/**
 * 配置activemq的扩展绑定属性
 *
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/11 18:50
 * @since JDK1.8
 */
@ConfigurationProperties("spring.cloud.stream.activemq")
public class ActiveMQExtendedBindingProperties implements ExtendedBindingProperties<ActiveMQConsumerProperties, ActiveMQProducerProperties> {
    
    private Map<String, ActiveMQBindingProperties> bindings = new HashMap<>();

    public Map<String, ActiveMQBindingProperties> getBindings() {
        return bindings;
    }
    
    public void setBindings(Map<String, ActiveMQBindingProperties> bindings) {
        this.bindings = bindings;
    }
    
    @Override
    public ActiveMQConsumerProperties getExtendedConsumerProperties(String channelName) {
        synchronized (this) {
            if (bindings.containsKey(channelName)) {
                if (bindings.get(channelName).getConsumer() != null) {
                    //如果包含且不为空,则直接返回
                    return bindings.get(channelName).getConsumer();
                } else {
                    //如果包含,但是为空,则重新设置然后返回
                    ActiveMQConsumerProperties consumerProperties = new ActiveMQConsumerProperties();
                    this.bindings.get(channelName).setConsumer(consumerProperties);
                    return consumerProperties;
                }
            } else {
                //如果不包含,则设置,并放入map,然后进行返回
                ActiveMQConsumerProperties consumerProperties = new ActiveMQConsumerProperties();
                ActiveMQBindingProperties properties = new ActiveMQBindingProperties();
                properties.setConsumer(consumerProperties);
                bindings.put(channelName, properties);
                return consumerProperties;
            }
        }
    }
    
    @Override
    public ActiveMQProducerProperties getExtendedProducerProperties(String channelName) {
        synchronized (this) {
            if (bindings.containsKey(channelName)) {
                if (bindings.get(channelName).getConsumer() != null) {
                    //如果包含且不为空,则直接返回
                    return bindings.get(channelName).getProducer();
                } else {
                    //如果包含,但是为空,则重新设置然后返回
                    ActiveMQProducerProperties producerProperties = new ActiveMQProducerProperties();
                    bindings.get(channelName).setProducer(producerProperties);
                    return producerProperties;
                }
            } else {
                //如果不包含,则设置,并放入map,然后进行返回
                ActiveMQProducerProperties producerProperties = new ActiveMQProducerProperties();
                ActiveMQBindingProperties properties = new ActiveMQBindingProperties();
                properties.setProducer(producerProperties);
                bindings.put(channelName, properties);
                return producerProperties;
            }
        }
    }
}
