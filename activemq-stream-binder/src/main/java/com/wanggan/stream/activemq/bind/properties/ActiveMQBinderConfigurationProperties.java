/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.activemq.bind.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置项目录,必须以spring.cloud.stream.binders.<configurationName>.environment为前缀
 * binders的environment配置类属性,一组属性的根，可用于自定义 Binders 的环境。设置此属性后，创建 Binders 的上下文不是应用程序上下文的子项。此设置允许 Binders 组件和应用组件之间的完全分离。
 *
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/11 18:31
 * @since JDK1.8
 */
@ConfigurationProperties(prefix = "spring.cloud.stream.activemq.binder")
public class ActiveMQBinderConfigurationProperties {
    private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";
    /**
     * broker-url
     */
    private String brokerUrl = DEFAULT_BROKER_URL;
    
    private String user;
    
    private String password;
    
    public String getBrokerUrl() {
        return brokerUrl;
    }
    
    public void setBrokerUrl(String brokerUrl) {
        this.brokerUrl = brokerUrl;
    }
    
    public String getUser() {
        return user;
    }
    
    public void setUser(String user) {
        this.user = user;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
