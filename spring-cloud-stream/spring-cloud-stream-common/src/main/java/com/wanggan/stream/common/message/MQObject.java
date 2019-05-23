/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.common.message;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

/**
 * 消息实体类,可扩展消息内容
 *
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/23 09:17
 * @since JDK1.8
 */
public class MQObject<T> implements Serializable {
    private static final long serialVersionUID = 4494062293735279012L;
    /**
     * 自定义数据头
     */
    private Map<String,Object> headers;
    /**
     * 消息id
     */
    private String msgId = UUID.randomUUID().toString();
    /**
     * 发送消息时间戳
     */
    private long timestamp = System.currentTimeMillis();
    /**
     * 消息内容
     */
    private T content;
    
    public MQObject() {
    }
    
    public MQObject(T content) {
        this.content = content;
    }
    
    public MQObject(Map<String, Object> headers, T content) {
        this.headers = headers;
        this.content = content;
    }
    
    @Override
    public String toString() {
        return "MQObject{" +
                "headers=" + headers +
                ", msgId='" + msgId + '\'' +
                ", timestamp=" + timestamp +
                ", content=" + content +
                '}';
    }
    
    public Map<String, Object> getHeaders() {
        return headers;
    }
    
    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }
    
    public String getMsgId() {
        return msgId;
    }
    
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
    
    public T getContent() {
        return content;
    }
    
    public void setContent(T content) {
        this.content = content;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
