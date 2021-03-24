package com.github.hqxqyly.remex.boot.sequence.id.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.sequence.client.ISequenceClient;
import com.github.hqxqyly.remex.boot.sequence.id.client.SequenceIdClient;

/**
 * 序列自动配置类 - id
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
public class SequenceIdAutoConfiguration {

	/**
	 * 序列处理器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public ISequenceClient createSequenceClient() {
		return new SequenceIdClient();
	}
}
