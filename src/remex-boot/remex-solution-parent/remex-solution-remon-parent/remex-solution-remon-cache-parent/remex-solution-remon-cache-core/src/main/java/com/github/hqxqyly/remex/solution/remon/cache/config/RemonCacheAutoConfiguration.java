package com.github.hqxqyly.remex.solution.remon.cache.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.solution.remon.base.config.RemonAutoConfiguration;
import com.github.hqxqyly.remex.solution.remon.cache.properties.RemonCacheProperties;

/**
 * 消息中间件自动配置类 - 缓存
 * 
 * @author Qiaoxin.Hong
 *
 */
@AutoConfigureAfter({RemonAutoConfiguration.class})
@Configuration
public class RemonCacheAutoConfiguration {

	/**
	 * 消息中间件properties - 缓存
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	@ConfigurationProperties(prefix = RemonCacheProperties.PREFIX)
	public RemonCacheProperties createRemonCacheProperties() {
		return new RemonCacheProperties();
	}
}
