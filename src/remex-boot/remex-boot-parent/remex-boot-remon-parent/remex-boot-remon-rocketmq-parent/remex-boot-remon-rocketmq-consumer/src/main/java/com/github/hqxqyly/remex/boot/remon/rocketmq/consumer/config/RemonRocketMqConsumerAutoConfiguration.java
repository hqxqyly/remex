package com.github.hqxqyly.remex.boot.remon.rocketmq.consumer.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.remon.base.consumer.client.IRemonConsumerClient;
import com.github.hqxqyly.remex.boot.remon.rocketmq.config.RemonRocketMqAutoConfiguration;
import com.github.hqxqyly.remex.boot.remon.rocketmq.consumer.client.RemonRocketMqConsumerClient;
import com.github.hqxqyly.remex.boot.rocketmq.consumer.config.RocketMqConsumerAutoConfiguration;

/**
 * 消息中间件自动配置类 - rocketmq - 消费者
 * 
 * @author Qiaoxin.Hong
 *
 */
@AutoConfigureAfter({RemonRocketMqAutoConfiguration.class, RocketMqConsumerAutoConfiguration.class})
@Configuration
public class RemonRocketMqConsumerAutoConfiguration {

	/**
	 * 消息中间件生产者 - rocketmq
	 * 
	 * @author Qiaoxin.Hong
	 *
	 */
	@Bean
	@ConditionalOnMissingBean
	public IRemonConsumerClient createRemonConsumer() {
		RemonRocketMqConsumerClient bean = new RemonRocketMqConsumerClient();
		return bean;
	}
}
