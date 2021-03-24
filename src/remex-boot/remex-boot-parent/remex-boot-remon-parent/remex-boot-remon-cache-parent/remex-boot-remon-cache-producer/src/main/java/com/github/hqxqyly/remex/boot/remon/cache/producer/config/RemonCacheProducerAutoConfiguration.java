package com.github.hqxqyly.remex.boot.remon.cache.producer.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.interfaces.assist.IAssistConfig;
import com.github.hqxqyly.remex.boot.remon.base.producer.client.IRemonProducerClient;
import com.github.hqxqyly.remex.boot.remon.cache.config.RemonCacheAutoConfiguration;
import com.github.hqxqyly.remex.boot.remon.cache.producer.client.RemonCacheProducerClient;
import com.github.hqxqyly.remex.boot.remon.cache.producer.properties.RemonCacheProducerProperties;
import com.github.hqxqyly.remex.boot.remon.cache.properties.RemonCacheProperties;

/**
 * 消息中间件自动配置类 - 缓存 - 生产者
 * 
 * @author Qiaoxin.Hong
 *
 */
@AutoConfigureAfter({RemonCacheAutoConfiguration.class})
@Configuration
public class RemonCacheProducerAutoConfiguration implements IAssistConfig {

	/**
	 * 消息中间件properties - 缓存 - 生产者
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	@ConfigurationProperties(prefix = RemonCacheProducerProperties.PREFIX)
	public RemonCacheProducerProperties createRemonCacheProducerProperties() {
		return new RemonCacheProducerProperties();
	}
	
	/**
	 * 消息中间件生产者 - 缓存
	 * 
	 * @author Qiaoxin.Hong
	 *
	 */
	@Bean
	@ConditionalOnMissingBean
	public IRemonProducerClient createRemonProducer(RemonCacheProducerProperties properties
			, RemonCacheProperties remonCacheProperties) {
		RemonCacheProducerClient bean = new RemonCacheProducerClient();
		ifNotNull(remonCacheProperties.getCachePrefix(), bean::setCachePrefix);
		return bean;
	}
}
