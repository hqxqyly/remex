package com.github.hqxqyly.remex.boot.rocketmq.consumer.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.interfaces.assist.IAssistConfig;
import com.github.hqxqyly.remex.boot.rocketmq.config.RocketMqAutoConfiguration;
import com.github.hqxqyly.remex.boot.rocketmq.consumer.properties.RocketMqConsumerProperties;

/**
 * rocketmq自动配置类 - 消费者
 * 
 * @author Qiaoxin.Hong
 *
 */
@AutoConfigureAfter({RocketMqAutoConfiguration.class})
@Configuration
public class RocketMqConsumerAutoConfiguration implements IAssistConfig {
	
	/**
	 * rocketmq properties - 消费者
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	@ConfigurationProperties(prefix = RocketMqConsumerProperties.PREFIX)
	public RocketMqConsumerProperties createRocketMqConsumerProperties() {
		return new RocketMqConsumerProperties();
	}
}
