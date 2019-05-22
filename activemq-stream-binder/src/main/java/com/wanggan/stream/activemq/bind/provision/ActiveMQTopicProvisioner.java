/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.activemq.bind.provision;

import org.springframework.cloud.stream.binder.ExtendedConsumerProperties;
import org.springframework.cloud.stream.binder.ExtendedProducerProperties;
import org.springframework.cloud.stream.provisioning.ConsumerDestination;
import org.springframework.cloud.stream.provisioning.ProducerDestination;
import org.springframework.cloud.stream.provisioning.ProvisioningException;
import org.springframework.cloud.stream.provisioning.ProvisioningProvider;
import org.springframework.util.StringUtils;

import com.wanggan.stream.activemq.bind.properties.ActiveMQConsumerProperties;
import com.wanggan.stream.activemq.bind.properties.ActiveMQProducerProperties;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/10 11:34
 * @since JDK1.8
 */
public class ActiveMQTopicProvisioner implements ProvisioningProvider<ExtendedConsumerProperties<ActiveMQConsumerProperties>, ExtendedProducerProperties<ActiveMQProducerProperties>> {
    
    @Override
    public ProducerDestination provisionProducerDestination(String name, ExtendedProducerProperties<ActiveMQProducerProperties> properties) throws ProvisioningException {
        checkTopic(name);
        return new ActiveMQProducerDestination(name);
    }
    
    @Override
    public ConsumerDestination provisionConsumerDestination(String name, String group, ExtendedConsumerProperties<ActiveMQConsumerProperties> properties) throws ProvisioningException {
        checkTopic(name);
        return new ActiveMQConsumerDestination(name);
    }
    
    private static void checkTopic(String topic){
        if(StringUtils.isEmpty(topic) || "".equals(topic.trim())){
            throw new RuntimeException("activemq topic is null or empty or blank");
        }
    }
    private static final class ActiveMQProducerDestination implements ProducerDestination {
        
        private final String producerDestinationName;
    
        ActiveMQProducerDestination(String destinationName) {
            this.producerDestinationName = destinationName;
        }
        
        @Override
        public String getName() {
            return producerDestinationName;
        }
        
        @Override
        public String getNameForPartition(int partition) {
            return producerDestinationName;
        }
        
    }
    
    private static final class ActiveMQConsumerDestination implements ConsumerDestination {
        
        private final String consumerDestinationName;
    
        ActiveMQConsumerDestination(String consumerDestinationName) {
            this.consumerDestinationName = consumerDestinationName;
        }
        
        @Override
        public String getName() {
            return this.consumerDestinationName;
        }
        
    }
}
