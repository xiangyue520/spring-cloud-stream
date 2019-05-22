/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.activemq.bind.properties;

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
}
