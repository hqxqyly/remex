package com.github.hqxqyly.remex.boot.validator.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.github.hqxqyly.remex.boot.validator.client.ValidatorClient;

/**
 * 验证拦截器
 * 
 * @author Qiaoxin.Hong
 *
 */
@Aspect
public class ValidatorAop {
	
	/** 验证处理器 */
	ValidatorClient validatorClient;
	
	@Around("@annotation(com.github.hqxqyly.remex.boot.validator.annotation.RemexValidate)")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		//参数验证
		validatorClient.validate(point);
		return point.proceed();
	}
	
	public void setValidatorClient(ValidatorClient validatorClient) {
		this.validatorClient = validatorClient;
	}
	
	public ValidatorClient getValidatorClient() {
		return validatorClient;
	}
}
