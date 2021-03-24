package com.github.hqxqyly.remex.boot.remon.rocketmq.producer.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.remon.base.producer.client.IRemonProducerClient;
import com.github.hqxqyly.remex.boot.remon.rocketmq.config.RemonRocketMqAutoConfiguration;
import com.github.hqxqyly.remex.boot.remon.rocketmq.producer.client.RemonRocketMqProducerClient;
import com.github.hqxqyly.remex.boot.rocketmq.producer.config.RocketMqProducerAutoConfiguration;

/**
 * 消息中间件自动配置类 - rocketmq - 生产者
 * 
 * @author Qiaoxin.Hong
 *
 */
@AutoConfigureAfter({RemonRocketMqAutoConfiguration.class, RocketMqProducerAutoConfiguration.class})
@Configuration
public class RemonRocketMqProducerAutoConfiguration {

	/**
	 * 消息中间件生产者 - rocketmq
	 * 
	 * @author Qiaoxin.Hong
	 *
	 */
	@Bean
	@ConditionalOnMissingBean
	public IRemonProducerClient createRemonProducer() {
		RemonRocketMqProducerClient bean = new RemonRocketMqProducerClient();
		return bean;
	}
}
