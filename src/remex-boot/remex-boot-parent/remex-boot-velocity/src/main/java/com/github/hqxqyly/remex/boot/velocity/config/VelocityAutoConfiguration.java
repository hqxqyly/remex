package com.github.hqxqyly.remex.boot.velocity.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.velocity.client.VelocityClient;

/**
 * velocity自动配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
public class VelocityAutoConfiguration {

	/**
	 * velocity处理器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public VelocityClient createVelocityClient() {
		return new VelocityClient();
	}
}
