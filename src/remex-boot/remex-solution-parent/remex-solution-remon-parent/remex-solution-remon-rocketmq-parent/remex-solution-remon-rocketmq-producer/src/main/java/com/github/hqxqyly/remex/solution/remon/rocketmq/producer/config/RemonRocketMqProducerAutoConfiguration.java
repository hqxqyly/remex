package com.github.hqxqyly.remex.solution.remon.rocketmq.producer.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.rocketmq.producer.config.RocketMqProducerAutoConfiguration;
import com.github.hqxqyly.remex.solution.remon.base.producer.client.IRemonProducerClient;
import com.github.hqxqyly.remex.solution.remon.rocketmq.config.RemonRocketMqAutoConfiguration;
import com.github.hqxqyly.remex.solution.remon.rocketmq.producer.client.RemonRocketMqProducerClient;

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
