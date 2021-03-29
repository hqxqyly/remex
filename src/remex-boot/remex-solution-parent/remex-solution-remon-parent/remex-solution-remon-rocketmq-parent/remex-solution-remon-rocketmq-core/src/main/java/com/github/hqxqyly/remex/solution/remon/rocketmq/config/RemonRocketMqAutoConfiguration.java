package com.github.hqxqyly.remex.solution.remon.rocketmq.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.solution.remon.base.config.RemonAutoConfiguration;

/**
 * 消息中间件自动配置类 - rocketmq
 * 
 * @author Qiaoxin.Hong
 *
 */
@AutoConfigureAfter({RemonAutoConfiguration.class})
@Configuration
public class RemonRocketMqAutoConfiguration {

}
