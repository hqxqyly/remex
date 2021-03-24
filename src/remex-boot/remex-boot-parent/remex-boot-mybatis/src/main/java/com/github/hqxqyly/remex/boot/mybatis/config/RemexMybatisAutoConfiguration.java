package com.github.hqxqyly.remex.boot.mybatis.config;

import java.util.List;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import com.github.hqxqyly.remex.boot.interfaces.assist.IAssistConfig;
import com.github.hqxqyly.remex.boot.mybatis.constant.MybatisOptions;
import com.github.hqxqyly.remex.boot.mybatis.logging.MybatisLogbackLog;

/**
 * <pre>
 * mybatis自动配置类，扩展org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration
 * 扩展：初始化自定义的默认的配置信息{@link #initRemexDefaultProperties(MybatisProperties)}
 * </pre>
 * 
 * @author Qiaoxin.Hong
 *
 */
@MapperScans(value = {@MapperScan("*.*.*.dao"), @MapperScan("*.*.*.dao.*"), @MapperScan("*.*.*.*.dao"), @MapperScan("*.*.*.*.dao.*")
	, @MapperScan("*.*.*.*.*.dao"), @MapperScan("*.*.*.*.*.dao.*")})
@AutoConfigureBefore(MybatisAutoConfiguration.class)
@Configuration
public class RemexMybatisAutoConfiguration extends MybatisAutoConfiguration implements IAssistConfig {
	
	@SuppressWarnings("rawtypes")
	public RemexMybatisAutoConfiguration(MybatisProperties properties, ObjectProvider<Interceptor[]> interceptorsProvider,
			ObjectProvider<TypeHandler[]> typeHandlersProvider,
			ObjectProvider<LanguageDriver[]> languageDriversProvider, ResourceLoader resourceLoader,
			ObjectProvider<DatabaseIdProvider> databaseIdProvider,
			ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
		super(initRemexDefaultProperties(properties), interceptorsProvider, typeHandlersProvider, languageDriversProvider, resourceLoader,
				databaseIdProvider, configurationCustomizersProvider);
	}
	
	/**
	 * 初始化自定义的默认的配置信息
	 * @param properties
	 * @return
	 */
	protected static MybatisProperties initRemexDefaultProperties(MybatisProperties properties) {
		org.apache.ibatis.session.Configuration configuration = properties.getConfiguration();
		if (configuration == null && !StringUtils.hasText(properties.getConfigLocation())) {
			configuration = new org.apache.ibatis.session.Configuration();
		}
		properties.setConfiguration(configuration);
		
		//默认驼峰命名转换字段
		configuration.setMapUnderscoreToCamelCase(true);
		if (properties.getMapperLocations() == null) 
			//默认加载mapper文件
			properties.setMapperLocations(MybatisOptions.DEFAULT_MAPPER_LOCATIONS);
		if (properties.getTypeAliasesPackage() == null)
			//默认使用实体简单类名
			properties.setTypeAliasesPackage(MybatisOptions.DEFAULT_TYPE_ALIASES_PACKAGE);
		//默认使用我自定义的日志打印器
	    if (configuration.getLogImpl() == null)
	    	configuration.setLogImpl(MybatisLogbackLog.class);

		return properties;
	}
	
	
	
	
	
}
