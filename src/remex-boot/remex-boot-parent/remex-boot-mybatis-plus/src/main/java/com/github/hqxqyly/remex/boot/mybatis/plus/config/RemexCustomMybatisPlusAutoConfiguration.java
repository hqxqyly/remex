package com.github.hqxqyly.remex.boot.mybatis.plus.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.github.hqxqyly.remex.boot.mybatis.plus.injector.RemexSqlInjector;

/**
 * mybatis自定义组件自动配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
public class RemexCustomMybatisPlusAutoConfiguration {

	/**
	 * 分页插件
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public PaginationInterceptor createPaginationInterceptor () {
		PaginationInterceptor bean = new PaginationInterceptor();
		return bean;
	}
	
	/**
	 * 创建sql解析注册器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public ISqlInjector createSqlInjector() {
		return new RemexSqlInjector();
	}
}
