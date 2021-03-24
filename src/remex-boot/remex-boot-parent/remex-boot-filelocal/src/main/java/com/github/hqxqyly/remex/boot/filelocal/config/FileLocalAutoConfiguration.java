package com.github.hqxqyly.remex.boot.filelocal.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.filelocal.client.FileLocalClient;
import com.github.hqxqyly.remex.boot.filelocal.properties.FileLocalProperties;
import com.github.hqxqyly.remex.boot.interfaces.assist.IAssistConfig;

/**
 * 本地文件处理自动配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
public class FileLocalAutoConfiguration implements IAssistConfig {

	/**
	 * 本地文件处理配置属性文件
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	@ConfigurationProperties(prefix = FileLocalProperties.PREFIX)
	public FileLocalProperties createFileLocalProperties() {
		return new FileLocalProperties();
	}
	
	/**
	 * 本地文件处理器
	 * @param properties
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public FileLocalClient createFileLocalClient(FileLocalProperties properties) {
		FileLocalClient bean = new FileLocalClient();
		ifNotNull(properties.getRootPath(), bean::setRootPath);
		ifNotNull(properties.getIsValidateFilePassLevelAccess(), bean::setValidateFilePassLevelAccess);
		return bean;
	}
}
