package com.github.hqxqyly.remex.solution.cache.ehcache.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.solution.cache.client.ICacheClient;
import com.github.hqxqyly.remex.solution.cache.ehcache.client.CacheEhCacheClient;

/**
 * 缓存自动配置类 - EhCache
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
public class CacheEhCacheAutoConfiguration {

	/**
	 * 缓存处理器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public ICacheClient createCacheClient() {
		return new CacheEhCacheClient();
	}
}
