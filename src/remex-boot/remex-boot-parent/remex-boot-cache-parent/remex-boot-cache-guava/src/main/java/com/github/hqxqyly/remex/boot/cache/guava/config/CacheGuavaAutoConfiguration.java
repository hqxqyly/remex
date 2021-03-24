package com.github.hqxqyly.remex.boot.cache.guava.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.cache.client.ICacheClient;
import com.github.hqxqyly.remex.boot.cache.guava.client.CacheGuavaClient;

/**
 * 缓存自动配置类 - guava
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
public class CacheGuavaAutoConfiguration {

	/**
	 * 缓存处理器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public ICacheClient createCacheClient() {
		return new CacheGuavaClient();
	}
}
