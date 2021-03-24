package com.github.hqxqyly.remex.boot.validator.config;

import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.validator.aop.ValidatorAop;
import com.github.hqxqyly.remex.boot.validator.client.ValidatorClient;

/**
 * 验证自动配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
public class ValidatorAutoConfiguration {
	
	/**
	 * 验证处理器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public ValidatorClient createValidatorClient() {
		return new ValidatorClient(createValidator());
	}
	
	/**
	 * HibernateValidator
	 * @return
	 */
	@Bean
	public Validator createValidator() {
		return Validation.byProvider(HibernateValidator.class)
			.configure()
			.failFast(true)  //一个验证失败就结束
			.buildValidatorFactory()
			.getValidator(); 		
	}
	
	/**
	 * 验证拦截器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public ValidatorAop createValidatorAop(ValidatorClient validatorClient) {
		ValidatorAop bean = new ValidatorAop();
		bean.setValidatorClient(validatorClient);
		return bean;
	}
}
