package com.github.hqxqyly.remex.boot.web.config;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.boot.web.properties.RemexMultipartProperties;

/**
 * remex MultipartAutoConfiguration，扩展org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration
 * 
 * @author Qiaoxin.Hong
 *
 */
@EnableConfigurationProperties(RemexMultipartProperties.class)
@AutoConfigureBefore({MultipartAutoConfiguration.class})
@Configuration
public class RemexMultipartAutoConfiguration extends MultipartAutoConfiguration {

	public RemexMultipartAutoConfiguration(MultipartProperties multipartProperties, RemexMultipartProperties remexMultipartProperties) {
		super(initRemexDefaultProperties(multipartProperties, remexMultipartProperties));
	}
	
	/**
	 * 初始化自定义的默认的配置信息
	 * @param properties
	 * @return
	 */
	protected static MultipartProperties initRemexDefaultProperties(MultipartProperties properties
			, RemexMultipartProperties remexMultipartProperties) {
		//默认20M
		properties.setMaxFileSize(Assist.defaultVal(remexMultipartProperties.getMaxFileSize(), DataSize.ofMegabytes(20)));
		properties.setMaxRequestSize(Assist.defaultVal(remexMultipartProperties.getMaxRequestSize(), DataSize.ofMegabytes(20)));
		return properties;
	}
}
