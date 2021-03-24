package com.github.hqxqyly.remex.boot.db.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.hqxqyly.remex.boot.interfaces.assist.IAssistConfig;

/**
 * <pre>
 * 数据源自动配置类，扩展org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
 * 扩展：初始化自定义的默认的配置信息{@link #initRemexDefaultProperties(DataSourceProperties)}
 * </pre>
 * 
 * @author Qiaoxin.Hong
 *
 */
@EnableConfigurationProperties(DataSourceProperties.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@Configuration
public class RemexDataSourceAutoConfiguration implements IAssistConfig {

	/**
	 * 数据源
	 * @see org.springframework.boot.autoconfigure.jdbc.DataSourceConfiguration.Generic
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public DataSource createDataSource(DataSourceProperties properties) {
		//初始化自定义的默认的配置信息
		initRemexDefaultProperties(properties);
		return properties.initializeDataSourceBuilder().build();
	}
	
	/**
	 * 初始化自定义的默认的配置信息
	 * @param properties
	 * @return
	 */
	protected DataSourceProperties initRemexDefaultProperties(DataSourceProperties properties) {
		//默认使用Druid数据源
		ifNotNull(properties.getType(), () -> properties.setType(DruidDataSource.class));
		//默认使用mysql驱动
		ifNotNull(properties.getDriverClassName(), () -> properties.setDriverClassName("com.mysql.cj.jdbc.Driver"));
		//默认使用root用户
		ifNotNull(properties.getUsername(), () -> properties.setUsername("root"));
		return properties;
	}
}
