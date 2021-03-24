package com.github.hqxqyly.remex.boot.remon.base.producer.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.remon.base.config.RemonAutoConfiguration;

/**
 * 消息中间件生产者自动配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@AutoConfigureAfter({RemonAutoConfiguration.class})
@Configuration
public class RemonProducerAutoConfiguration {
}
