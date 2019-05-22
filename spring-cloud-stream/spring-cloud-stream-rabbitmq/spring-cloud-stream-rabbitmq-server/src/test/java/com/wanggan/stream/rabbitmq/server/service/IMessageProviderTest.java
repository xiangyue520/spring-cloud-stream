package com.wanggan.stream.rabbitmq.server.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanggan.stream.rabbitmq.server.ServerRabbitStreamApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServerRabbitStreamApplication.class)
public class IMessageProviderTest {
    @Resource
    private IMessageProvider messageProvider;
    
    @Test
    public void testSend() {
        this.messageProvider.send("hello"); // 消息发送
    }
}