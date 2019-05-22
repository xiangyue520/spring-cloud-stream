/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package com.wanggan.stream.rabbitmq.client.domain;

import java.io.Serializable;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/13 16:59
 * @since JDK1.8
 */
public  class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    
    public Person() {
    }
    
    public Person(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return this.name;
    }
}
