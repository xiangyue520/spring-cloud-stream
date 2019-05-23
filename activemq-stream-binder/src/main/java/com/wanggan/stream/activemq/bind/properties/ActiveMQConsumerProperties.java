/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.activemq.bind.properties;

import javax.jms.Session;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/10 11:27
 * @since JDK1.8
 */
public class ActiveMQConsumerProperties {
    /**
     * 是否启用消费者
     */
    private Boolean enabled = true;
    /**
     * 私信队列名称
     */
    private String dlqName = "dlq";
    
    private boolean transacted;
    
    private int acknowledgeMode = Session.AUTO_ACKNOWLEDGE;
    /**
     * true为发布订阅模式
     */
    private boolean pubSubDomain = true;
    
    public Boolean getEnabled() {
        return enabled;
    }
    
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    
    public String getDlqName() {
        return dlqName;
    }
    
    public void setDlqName(String dlqName) {
        this.dlqName = dlqName;
    }
    
    public boolean isTransacted() {
        return transacted;
    }
    
    public void setTransacted(boolean transacted) {
        this.transacted = transacted;
    }
    
    public int getAcknowledgeMode() {
        return acknowledgeMode;
    }
    
    public void setAcknowledgeMode(int acknowledgeMode) {
        this.acknowledgeMode = acknowledgeMode;
    }
    
    public boolean isPubSubDomain() {
        return pubSubDomain;
    }
    
    public void setPubSubDomain(boolean pubSubDomain) {
        this.pubSubDomain = pubSubDomain;
    }
}
