package com.github.hqxqyly.remex.solution.remon.cache.consumer.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.interfaces.assist.IAssistConfig;
import com.github.hqxqyly.remex.solution.remon.base.consumer.client.IRemonConsumerClient;
import com.github.hqxqyly.remex.solution.remon.cache.config.RemonCacheAutoConfiguration;
import com.github.hqxqyly.remex.solution.remon.cache.consumer.client.RemonCacheConsumerClient;
import com.github.hqxqyly.remex.solution.remon.cache.consumer.client.RemonCacheRetryConsumerClient;
import com.github.hqxqyly.remex.solution.remon.cache.consumer.properties.RemonCacheConsumerProperties;
import com.github.hqxqyly.remex.solution.remon.cache.properties.RemonCacheProperties;

/**
 * 消息中间件自动配置类 - 缓存 - 消费者
 * 
 * @author Qiaoxin.Hong
 *
 */
@AutoConfigureAfter({RemonCacheAutoConfiguration.class})
@Configuration
public class RemonCacheConsumerAutoConfiguration implements IAssistConfig {

	/**
	 * 消息中间件properties - 缓存 - 消费者
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	@ConfigurationProperties(prefix = RemonCacheConsumerProperties.PREFIX)
	public RemonCacheConsumerProperties createRemonCacheConsumerProperties() {
		return new RemonCacheConsumerProperties();
	}
	
	/**
	 * 消息中间件消费者 - 缓存
	 * 
	 * @author Qiaoxin.Hong
	 *
	 */
	@Bean
	@ConditionalOnMissingBean
	public IRemonConsumerClient createRemonConsumer(RemonCacheConsumerProperties properties
			, RemonCacheProperties remonCacheProperties) {
		RemonCacheConsumerClient bean = new RemonCacheConsumerClient();
		ifNotBlank(remonCacheProperties.getCachePrefix(), bean::setCachePrefix);
		ifNotBlank(properties.getCacheLockPrefix(), bean::setCacheLockPrefix);
		ifNotBlank(properties.getCacheRetryPrefix(), bean::setCacheRetryPrefix);
		ifNotBlank(properties.getCacheDiePrefix(), bean::setCacheDiePrefix);
		ifNotNull(properties.getSleepSeconds(), bean::setSleepSeconds);
		ifNotNull(properties.getIsRetry(), bean::setRetry);
		return bean;
	}
	
	/**
	 * 消息中间件消费者 - 缓存
	 * 
	 * @author Qiaoxin.Hong
	 *
	 */
	@Bean
	@ConditionalOnMissingBean
	public RemonCacheRetryConsumerClient createRemonCacheRetryConsumer(RemonCacheConsumerProperties properties
			, RemonCacheProperties remonCacheProperties) {
		RemonCacheRetryConsumerClient bean = new RemonCacheRetryConsumerClient();
		ifNotBlank(properties.getCacheRetryPrefix(), bean::setCacheRetryPrefix);
		ifNotBlank(properties.getCacheRetryLockPrefix(), bean::setCacheRetryLockPrefix);
		ifNotBlank(properties.getCacheDiePrefix(), bean::setCacheDiePrefix);
		ifNotNull(properties.getRetrySleepSeconds(), bean::setRetrySleepSeconds);
		ifNotNull(properties.getRetryMaxCount(), bean::setRetryMaxCount);
		return bean;
	}
}
