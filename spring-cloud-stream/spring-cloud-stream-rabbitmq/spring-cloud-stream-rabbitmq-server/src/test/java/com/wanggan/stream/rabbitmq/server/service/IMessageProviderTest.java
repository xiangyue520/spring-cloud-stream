package com.wanggan.stream.rabbitmq.server.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IMessageProviderTest {
    
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
//        assertThat(received.getPayload(), equalTo("hello world"));
        System.out.println(received.getPayload());
    }
    
    
    @SpringBootApplication
    @EnableBinding(Processor.class)
    public static class MyProcessor {
        
        @Autowired
        private Processor channels;
        
        @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
        public String transform(String in) {
            return in + " world";
        }
    }
}