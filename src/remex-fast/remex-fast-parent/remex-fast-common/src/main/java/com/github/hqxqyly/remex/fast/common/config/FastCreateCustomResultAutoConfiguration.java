package com.github.hqxqyly.remex.fast.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.interfaces.result.ICreateCustomResultClient;
import com.github.hqxqyly.remex.fast.common.component.result.FastCreateCustomResultClient;

/**
 * 创建自定义结果集配置类，remex-boot的一些组件需要使用，如response写入一个错误的结果集
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
public class FastCreateCustomResultAutoConfiguration {

	/**
	 * 创建自定义结果集的规范
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public ICreateCustomResultClient createCreateCustomResultClient() {
		return new FastCreateCustomResultClient();
	}
}
