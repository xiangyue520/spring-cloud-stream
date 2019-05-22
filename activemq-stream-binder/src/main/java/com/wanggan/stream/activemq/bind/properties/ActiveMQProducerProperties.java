/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.activemq.bind.properties;

import javax.jms.DeliveryMode;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/10 11:29
 * @since JDK1.8
 */
public class ActiveMQProducerProperties {
    /**
     * 消息是否持久化,默认持久化
     */
    private int deliveryMode = DeliveryMode.PERSISTENT;
    /**
     * 是否开启
     */
    private Boolean enabled = true;
    
    /**
     * Name of producer.
     */
    private String group;
    
    /**
     * max message size
     */
    private Integer maxMessageSize = 1024 * 1024 * 4;
    /**
     * 是否开启事务
     */
    private Boolean transactional = true;
    
    private Boolean sync = false;
    
    /**
     * Millis of send message timeout.
     */
    private int sendMessageTimeout = 3000;
    
    /**
     * Compress message body threshold, namely, message body larger than 4k will be
     * compressed on default.
     */
    private int compressMessageBodyThreshold = 1024 * 4;
    
    /**
     * Maximum number of retry to perform internally before claiming sending failure in
     * synchronous mode. This may potentially cause message duplication which is up to
     * application developers to resolve.
     */
    private int retryTimesWhenSendFailed = 2;
    
    /**
     * <p>
     * Maximum number of retry to perform internally before claiming sending failure in
     * asynchronous mode.
     * </p>
     * This may potentially cause message duplication which is up to application
     * developers to resolve.
     */
    private int retryTimesWhenSendAsyncFailed = 2;
    
    public Boolean getEnabled() {
        return enabled;
    }
    
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    
    public String getGroup() {
        return group;
    }
    
    public void setGroup(String group) {
        this.group = group;
    }
    
    public Integer getMaxMessageSize() {
        return maxMessageSize;
    }
    
    public void setMaxMessageSize(Integer maxMessageSize) {
        this.maxMessageSize = maxMessageSize;
    }
    
    public Boolean getTransactional() {
        return transactional;
    }
    
    public void setTransactional(Boolean transactional) {
        this.transactional = transactional;
    }
    
    public Boolean getSync() {
        return sync;
    }
    
    public void setSync(Boolean sync) {
        this.sync = sync;
    }
    
    public int getSendMessageTimeout() {
        return sendMessageTimeout;
    }
    
    public void setSendMessageTimeout(int sendMessageTimeout) {
        this.sendMessageTimeout = sendMessageTimeout;
    }
    
    public int getCompressMessageBodyThreshold() {
        return compressMessageBodyThreshold;
    }
    
    public void setCompressMessageBodyThreshold(int compressMessageBodyThreshold) {
        this.compressMessageBodyThreshold = compressMessageBodyThreshold;
    }
    
    public int getRetryTimesWhenSendFailed() {
        return retryTimesWhenSendFailed;
    }
    
    public void setRetryTimesWhenSendFailed(int retryTimesWhenSendFailed) {
        this.retryTimesWhenSendFailed = retryTimesWhenSendFailed;
    }
    
    public int getRetryTimesWhenSendAsyncFailed() {
        return retryTimesWhenSendAsyncFailed;
    }
    
    public void setRetryTimesWhenSendAsyncFailed(int retryTimesWhenSendAsyncFailed) {
        this.retryTimesWhenSendAsyncFailed = retryTimesWhenSendAsyncFailed;
    }
    
    public int getDeliveryMode() {
        return deliveryMode;
    }
    
    public void setDeliveryMode(int deliveryMode) {
        this.deliveryMode = deliveryMode;
    }
}
