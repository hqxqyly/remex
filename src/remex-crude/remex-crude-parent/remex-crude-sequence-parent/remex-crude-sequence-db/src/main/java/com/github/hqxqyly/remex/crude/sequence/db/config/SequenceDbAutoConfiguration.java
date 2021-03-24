package com.github.hqxqyly.remex.crude.sequence.db.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.interfaces.assist.IAssistConfig;
import com.github.hqxqyly.remex.boot.sequence.client.ISequenceClient;
import com.github.hqxqyly.remex.crude.sequence.db.client.SequenceDbClient;
import com.github.hqxqyly.remex.crude.sequence.db.properties.SequenceDbProperties;

/**
 * 序列自动配置类 - db
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
public class SequenceDbAutoConfiguration implements IAssistConfig {
	
	/**
	 * 序列properties - db
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	@ConfigurationProperties(prefix = SequenceDbProperties.PREFIX)
	public SequenceDbProperties createSequenceDbProperties() {
		return new SequenceDbProperties();
	}

	/**
	 * 序列处理器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public ISequenceClient createSequenceClient(SequenceDbProperties properties) {
		SequenceDbClient bean =  new SequenceDbClient();
		ifNotNull(properties.getCacheLockKeySaveSeq(), bean::setCacheLockKeySaveSeq);
		ifNotNull(properties.getDefaultIncrease(), bean::setDefaultIncrease);
		ifNotNull(properties.getDefaultSeqNumLength(), bean::setDefaultSeqNumLength);
		return bean;
	}
}
