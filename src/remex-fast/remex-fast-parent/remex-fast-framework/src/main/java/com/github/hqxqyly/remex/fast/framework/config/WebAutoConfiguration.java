package com.github.hqxqyly.remex.fast.framework.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.github.hqxqyly.remex.fast.common.structure.entity.BaseEntity;
import com.github.hqxqyly.remex.fast.common.structure.entity.fill.EntityFillDefaultClient;
import com.github.hqxqyly.remex.fast.common.structure.entity.fill.IEntityFillClient;

/**
 * web自动配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@Import({ExceptionHandlerConfig.class})
@Configuration
public class WebAutoConfiguration {

	/**
	 * 设置业务实体的公共属性处理器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public IEntityFillClient<BaseEntity> createEntityFillClient() {
		return new EntityFillDefaultClient();
	}
}
