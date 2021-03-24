package com.github.hqxqyly.remex.boot.shiro.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.github.hqxqyly.remex.boot.interfaces.assist.IAssistConfig;
import com.github.hqxqyly.remex.boot.shiro.common.client.ShiroEncryptClient;
import com.github.hqxqyly.remex.boot.shiro.common.interfaces.IShiroEncryptClient;
import com.github.hqxqyly.remex.boot.shiro.common.properties.ShiroProperties;

/**
 * shiro通用自动配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class ShiroCommonAutoConfiguration implements IAssistConfig {

	/**
	 * swagger配置属性文件
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	@ConfigurationProperties(prefix = ShiroProperties.PREFIX)
	public ShiroProperties createShiroProperties() {
		return new ShiroProperties();
	}
	
	/**
	 * shiro加密处理器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public IShiroEncryptClient createShiroEncryptClient(ShiroProperties properties) {
		ShiroEncryptClient bean = new ShiroEncryptClient();
		ifNotNull(properties.getEncryptAlgorithmName(), bean::setEncryptAlgorithmName);
		ifNotNull(properties.getEncryptHashIterations(), bean::setEncryptHashIterations);
		ifNotNull(properties.getIsEncryptBcrypt(), bean::setEncryptBcrypt);
		return bean;
	}
}
