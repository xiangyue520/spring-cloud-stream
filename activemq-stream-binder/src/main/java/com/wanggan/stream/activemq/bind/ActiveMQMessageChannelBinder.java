/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.activemq.bind;

import java.util.HashMap;
import java.util.Map;

import javax.jms.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.cloud.stream.binder.*;
import org.springframework.cloud.stream.provisioning.ConsumerDestination;
import org.springframework.cloud.stream.provisioning.ProducerDestination;
import org.springframework.integration.core.MessageProducer;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.wanggan.stream.activemq.bind.properties.ActiveMQBinderConfigurationProperties;
import com.wanggan.stream.activemq.bind.properties.ActiveMQConsumerProperties;
import com.wanggan.stream.activemq.bind.properties.ActiveMQExtendedBindingProperties;
import com.wanggan.stream.activemq.bind.properties.ActiveMQProducerProperties;
import com.wanggan.stream.activemq.bind.provision.ActiveMQTopicProvisioner;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/9 14:55
 * @since JDK1.8
 */
public class ActiveMQMessageChannelBinder extends AbstractMessageChannelBinder<ExtendedConsumerProperties<ActiveMQConsumerProperties>,ExtendedProducerProperties<ActiveMQProducerProperties>,ActiveMQTopicProvisioner> implements ExtendedPropertiesBinder<MessageChannel, ActiveMQConsumerProperties, ActiveMQProducerProperties> {
    @Autowired(required = false)
    private JmsTemplate jmsTemplate;
    
    private ActiveMQExtendedBindingProperties extendedBindingProperties = new ActiveMQExtendedBindingProperties();
    private final ActiveMQBinderConfigurationProperties activeMQBinderConfigurationProperties;
    private final ActiveMQProperties activeMQProperties;
    
    private Map<String, String> topicInUse = new HashMap<>();
    
    public ActiveMQMessageChannelBinder(ActiveMQTopicProvisioner provisioningProvider,
                                        ActiveMQExtendedBindingProperties extendedBindingProperties,
                                        ActiveMQBinderConfigurationProperties activeMQBinderConfigurationProperties,
                                        ActiveMQProperties activeMQProperties) {
        super(null, provisioningProvider);
        this.extendedBindingProperties = extendedBindingProperties;
        this.activeMQBinderConfigurationProperties = activeMQBinderConfigurationProperties;
        this.activeMQProperties = activeMQProperties;
    }
    //    /**
//     * 接收 ActiveMQ 消息
//     * @param name
//     * @param group
//     * @param inboundBindTarget
//     * @param consumerProperties
//     * @return
//     */
//    @Override
    public Binding<MessageChannel> bindConsumer(String name, String group, MessageChannel inboundBindTarget, ConsumerProperties consumerProperties) {
        //todo:实现消息消费
        ConnectionFactory connectionFactory = jmsTemplate.getConnectionFactory();
        try {
            // 创造 JMS 链接
            Connection connection = connectionFactory.createConnection();
            // 启动连接
            connection.start();
            // 创建会话 Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建消息目的
            Destination destination = session.createQueue(name);
            // 创建消息消费者
            MessageConsumer messageConsumer = session.createConsumer(destination);

            messageConsumer.setMessageListener(message -> {
                // message 来自于 ActiveMQ
                if (message instanceof ObjectMessage) {
                    ObjectMessage objectMessage = (ObjectMessage) message;
                    try {
                        Object object = objectMessage.getObject();
                        inboundBindTarget.send(new GenericMessage<Object>(object));
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return () -> { };
    }
    
    @Override
    protected MessageHandler createProducerMessageHandler(ProducerDestination destination, ExtendedProducerProperties<ActiveMQProducerProperties> producerProperties, MessageChannel errorChannel) throws Exception {
        if(producerProperties.getExtension().getEnabled()) {
            Assert.state(!HeaderMode.embeddedHeaders.equals(producerProperties.getHeaderMode()), "the ActiveMQ binder does not support embedded headers since ActiveMQ supports headers natively");
            String des = destination.getName();
            ActiveMQProducerProperties producer = producerProperties.getExtension();
            //获取组
            String extendedProducerGroup = producer.getGroup();
            //如果为空,则使用默认的地址名称
            String producerGroup = StringUtils.isEmpty(extendedProducerGroup)
                    ? destination.getName()
                    : extendedProducerGroup;
            //MessageChannel 必须是 SubscribableChannel 类型
            Assert.isInstanceOf(SubscribableChannel.class, errorChannel, "Binding is supported only for SubscribableChannel instances");
            //强转为 SubscribableChannel类型
            SubscribableChannel subscribableChannel = (SubscribableChannel)errorChannel;
            ((SubscribableChannel) errorChannel).subscribe(message -> {
                //判断是否开启事务,如果有,则开启事务消息,否则关闭
                jmsTemplate.setSessionTransacted(producer.getTransactional());
                //设置持久化
                jmsTemplate.setDeliveryMode(producer.getDeliveryMode());
                //发送消息
                jmsTemplate.convertAndSend(des, errorChannel);
            });
            return (message) -> System.out.println("Unbind");
        }else {
            throw new RuntimeException("Binding for channel"+destination.getName()+"has been disabled,message can't be deliverd");
        }
        
    }
    
    @Override
    protected MessageProducer createConsumerEndpoint(ConsumerDestination destination, String group, ExtendedConsumerProperties<ActiveMQConsumerProperties> properties) throws Exception {
        ActiveMQConsumerProperties consumerProperties = properties.getExtension();
        if(consumerProperties.getEnabled()){
            
            jmsTemplate.setPubSubDomain(consumerProperties.isPubSubDomain());
            ConnectionFactory connectionFactory = jmsTemplate.getConnectionFactory();
            try {
                // 创造 JMS 链接
                Connection connection = connectionFactory.createConnection();
                // 启动连接
                connection.start();
                int acknowledgeMode = consumerProperties.getAcknowledgeMode();
                boolean transacted = consumerProperties.isTransacted();
                // 创建会话 Session
                Session session = connection.createSession(transacted, acknowledgeMode);
                // 创建消息目的
                Destination des = session.createQueue(destination.getName());
                // 创建消息消费者
                MessageConsumer messageConsumer = session.createConsumer(des);
        
                messageConsumer.setMessageListener(message -> {
                    // message 来自于 ActiveMQ
                    if (message instanceof ObjectMessage) {
                        ObjectMessage objectMessage = (ObjectMessage) message;
                        try {
                            Object object = objectMessage.getObject();
                            System.out.println("object message:"+object);
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("activemq message:"+message);
                });
            } catch (JMSException e) {
                e.printStackTrace();
            }
            return null;
        }else{
            throw new RuntimeException(" Customer Binding for channel"+destination.getName()+"has been disabled,message can't be deliverd");
        }
    }
    
    @Override
    public ActiveMQConsumerProperties getExtendedConsumerProperties(String channelName) {
        return this.extendedBindingProperties.getExtendedConsumerProperties(channelName);
    }
    
    @Override
    public ActiveMQProducerProperties getExtendedProducerProperties(String channelName) {
        return this.extendedBindingProperties.getExtendedProducerProperties(channelName);
    }
}
