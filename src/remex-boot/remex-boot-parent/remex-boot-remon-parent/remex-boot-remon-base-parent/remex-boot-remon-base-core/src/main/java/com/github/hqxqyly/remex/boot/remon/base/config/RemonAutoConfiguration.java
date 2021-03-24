package com.github.hqxqyly.remex.boot.remon.base.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.remon.base.properties.RemonProperties;

/**
 * 消息中间件自动配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
public class RemonAutoConfiguration {

	/**
	 * 消息中间件properties
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	@ConfigurationProperties(prefix = RemonProperties.PREFIX)
	public RemonProperties createRemonProperties() {
		return new RemonProperties();
	}
}
