/**
 * Company
 * Copyright (C) 1992-2019 All Rights Reserved.
 */
package boot.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author wanggan@yinhai.com
 * @version 1.0
 * @date 2019/5/7 17:15
 * @since JDK1.8
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class,args);
    }
}
