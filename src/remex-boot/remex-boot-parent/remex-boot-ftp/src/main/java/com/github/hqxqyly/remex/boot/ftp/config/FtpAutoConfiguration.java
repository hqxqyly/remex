package com.github.hqxqyly.remex.boot.ftp.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.ftp.client.FtpClient;
import com.github.hqxqyly.remex.boot.ftp.properties.FtpProperties;
import com.github.hqxqyly.remex.boot.interfaces.assist.IAssistConfig;

/**
 * ftp自动配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
public class FtpAutoConfiguration implements IAssistConfig {

	/**
	 * ftp配置属性文件
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	@ConfigurationProperties(prefix = FtpProperties.PREFIX)
	public FtpProperties createFtpProperties() {
		return new FtpProperties();
	}
	
	/**
	 * ftp处理器
	 * @param properties
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public FtpClient createFtpClient(FtpProperties properties) {
		FtpClient bean = new FtpClient();
		ifNotNull(properties.getHostName(), bean::setHostName);
		ifNotNull(properties.getPort(), bean::setPort);
		ifNotNull(properties.getUserName(), bean::setUserName);
		ifNotNull(properties.getPassword(), bean::setPassword);
		ifNotNull(properties.getBufferSize(), bean::setBufferSize);
		return bean;
	}
}
