package com.github.hqxqyly.remex.boot.rocketmq.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.rocketmq.properties.RocketMqProperties;

/**
 * rocketmq自动配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
public class RocketMqAutoConfiguration {

	/**
	 * rocketmq properties
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	@ConfigurationProperties(prefix = RocketMqProperties.PREFIX)
	public RocketMqProperties createRocketMqProperties() {
		return new RocketMqProperties();
	}
}
