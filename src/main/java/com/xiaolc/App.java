package com.xiaolc;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 启动类
 *
 * @Author: lc
 * @Date: 2019/6/19 16:01
 */

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ServletComponentScan("com.xiaolc.config")
public class App extends SpringBootServletInitializer {
    private static Logger logger = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        logger.info("--------------启动完毕--------------");
    }

}
