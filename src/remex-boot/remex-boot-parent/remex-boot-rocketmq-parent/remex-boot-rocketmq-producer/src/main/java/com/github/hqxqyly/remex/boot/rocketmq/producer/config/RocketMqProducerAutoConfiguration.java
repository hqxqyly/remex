package com.github.hqxqyly.remex.boot.rocketmq.producer.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.interfaces.assist.IAssistConfig;
import com.github.hqxqyly.remex.boot.rocketmq.config.RocketMqAutoConfiguration;
import com.github.hqxqyly.remex.boot.rocketmq.producer.properties.RocketMqProducerProperties;

/**
 * rocketmq自动配置类 - 生产者
 * 
 * @author Qiaoxin.Hong
 *
 */
@AutoConfigureAfter({RocketMqAutoConfiguration.class})
@Configuration
public class RocketMqProducerAutoConfiguration implements IAssistConfig {
	
	/**
	 * rocketmq properties - 生产者
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	@ConfigurationProperties(prefix = RocketMqProducerProperties.PREFIX)
	public RocketMqProducerProperties createRocketMqProducerProperties() {
		return new RocketMqProducerProperties();
	}
}
