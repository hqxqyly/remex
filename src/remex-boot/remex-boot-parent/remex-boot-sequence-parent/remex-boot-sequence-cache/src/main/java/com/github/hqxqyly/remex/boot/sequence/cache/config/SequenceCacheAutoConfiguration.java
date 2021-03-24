package com.github.hqxqyly.remex.boot.sequence.cache.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.interfaces.assist.IAssistConfig;
import com.github.hqxqyly.remex.boot.sequence.cache.client.SequenceCacheClient;
import com.github.hqxqyly.remex.boot.sequence.cache.properties.SequenceCacheProperties;
import com.github.hqxqyly.remex.boot.sequence.client.ISequenceClient;

/**
 * 序列自动配置类 - 缓存
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
public class SequenceCacheAutoConfiguration implements IAssistConfig {
	
	/**
	 * 序列properties - 缓存
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	@ConfigurationProperties(prefix = SequenceCacheProperties.PREFIX)
	public SequenceCacheProperties createSequenceCacheProperties() {
		return new SequenceCacheProperties();
	}

	/**
	 * 序列处理器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public ISequenceClient createSequenceClient(SequenceCacheProperties properties) {
		SequenceCacheClient bean =  new SequenceCacheClient();
		ifNotNull(properties.getCacheLockKeySaveSeq(), bean::setCacheLockKeySaveSeq);
		ifNotNull(properties.getDefaultIncrease(), bean::setDefaultIncrease);
		ifNotNull(properties.getDefaultSeqNumLength(), bean::setDefaultSeqNumLength);
		ifNotNull(properties.getCacheKeyPrefixData(), bean::setCacheKeyPrefixData);
		return bean;
	}
}
