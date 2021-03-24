package com.github.hqxqyly.remex.boot.mybatis.plus.config;

import java.util.List;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.github.hqxqyly.remex.boot.mybatis.config.RemexMybatisAutoConfiguration;
import com.github.hqxqyly.remex.boot.mybatis.constant.MybatisOptions;
import com.github.hqxqyly.remex.boot.mybatis.logging.MybatisLogbackLog;

/**
 * <pre>MybatisAutoConfiguration MybatisPlusAutoConfiguration冲突
 * mybatis plus自动配置类，扩展com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration
 * 扩展：初始化自定义的默认的配置信息{@link #initRemexDefaultProperties(MybatisPlusProperties)}
 * </pre>
 * 
 * @author Qiaoxin.Hong
 *
 */
@Primary
@AutoConfigureAfter({RemexCustomMybatisPlusAutoConfiguration.class})
@AutoConfigureBefore({RemexMybatisAutoConfiguration.class, MybatisPlusAutoConfiguration.class})
@Configuration
public class RemexMybatisPlusAutoConfiguration extends MybatisPlusAutoConfiguration {

	@SuppressWarnings("rawtypes")
	public RemexMybatisPlusAutoConfiguration(MybatisPlusProperties properties,
			ObjectProvider<Interceptor[]> interceptorsProvider, ObjectProvider<TypeHandler[]> typeHandlersProvider,
			ObjectProvider<LanguageDriver[]> languageDriversProvider, ResourceLoader resourceLoader,
			ObjectProvider<DatabaseIdProvider> databaseIdProvider,
			ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider,
			ObjectProvider<List<MybatisPlusPropertiesCustomizer>> mybatisPlusPropertiesCustomizerProvider,
			ApplicationContext applicationContext) {
		super(initRemexDefaultProperties(properties), interceptorsProvider, typeHandlersProvider, languageDriversProvider, resourceLoader,
				databaseIdProvider, configurationCustomizersProvider, mybatisPlusPropertiesCustomizerProvider,
				applicationContext);
	}

	/**
	 * 初始化自定义的默认的配置信息
	 * @param properties
	 * @return
	 */
	protected static MybatisPlusProperties initRemexDefaultProperties(MybatisPlusProperties properties) {
		MybatisConfiguration configuration = properties.getConfiguration();
        if (configuration == null && !StringUtils.hasText(properties.getConfigLocation())) {
            configuration = new MybatisConfiguration();
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
		
		//查询时，null或""不加入条件
		properties.getGlobalConfig().getDbConfig().setSelectStrategy(FieldStrategy.NOT_EMPTY);
		
		return properties;
	}
}
