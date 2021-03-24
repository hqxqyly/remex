package com.github.hqxqyly.remex.boot.guava.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.guava.client.GuavaClient;

/**
 * guava自动配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
public class GuavaAutoConfiguration {

	/**
	 * guava处理器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public GuavaClient createGuavaClient() {
		return new GuavaClient();
	}
}
