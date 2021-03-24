package com.github.hqxqyly.remex.boot.ehcache.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.ehcache.client.EhCacheClient;

/**
 * redis自动配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
public class EhCacheAutoConfiguration {

	/**
	 * redis处理器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public EhCacheClient createEhCacheClient() {
		return new EhCacheClient();
	}
}
