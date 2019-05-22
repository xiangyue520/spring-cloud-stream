package com.wanggan.stream.rocketmq;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RocketMQApplicationTest {
    @Autowired
    private Processor processor;
    
    @Autowired
    private MessageCollector messageCollector;
    
    @Test
    @SuppressWarnings("unchecked")
    public void testWiring() {
        Message<String> message = new GenericMessage<>("hello");
        processor.input().send(message);
        Message<String> received = (Message<String>) messageCollector.forChannel(processor.output()).poll();
        Assert.assertEquals(received.getPayload(), "hello world");
    }
}