package com.github.hqxqyly.remex.boot.validator.client;

import java.lang.reflect.Method;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.github.hqxqyly.remex.boot.exception.RemexServerException;
import com.github.hqxqyly.remex.boot.msg.IMsgEnum;
import com.github.hqxqyly.remex.boot.msg.MsgEnumContext;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.boot.validator.annotation.RemexValidate;
import com.github.hqxqyly.remex.boot.validator.bean.ValidatorResult;
import com.github.hqxqyly.remex.boot.validator.msg.MsgValidatorEnum;

/**
 * 验证处理器
 * 
 * @author Qiaoxin.Hong
 *
 */
public class ValidatorClient {

	/** 验证器 */
	protected Validator validator;
	
	public ValidatorClient() {
		
	}
	
	public ValidatorClient(Validator validator) {
		this.validator = validator;
	}
	
	/**
	 * 验证处理
	 * @param obj
	 * @param groups 验证组
	 */
	public ValidatorResult validateHandle(Object obj, Class<?>... groups) {
		ValidatorResult result = new ValidatorResult();
		if (obj != null) {
			Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj, groups);
			if (Assist.isNotEmpty(constraintViolations)) {
				ConstraintViolation<Object> violation = constraintViolations.iterator().next();
				result.setSuccess(false);
				result.setMessage(violation.getMessage());
				result.setProperty(violation.getPropertyPath().toString());
				result.setValue(violation.getInvalidValue());
				result.setConstraintViolation(violation);
			}
		}
		
		return result;
	}
	
	/**
	 * 参数验证
	 */
	public void validate(ProceedingJoinPoint point) {
		Method method = ((MethodSignature) point.getSignature()).getMethod();
		Object[] args = point.getArgs();
		
		validate(method, args);
	}
	
	/**
	 * 参数验证
	 */
	public void validate(MethodInvocation invocation) {
		Method method = invocation.getMethod();
		Object[] args = invocation.getArguments();
		
		validate(method, args);
	}

	/**
	 * 参数验证
	 * @return
	 */
	public void validate(Method method, Object[] args) {
		if (method.isAnnotationPresent(RemexValidate.class)) {
			RemexValidate remexValidate = method.getAnnotation(RemexValidate.class);
			//验证组标识列表
			Class<?>[] groups = remexValidate.groups();
			if (args != null && args.length != 0) {
				for (int i = 0; i < args.length; i++) {
					validate(args[i], groups);
				}
			}
		}
	}

	/**
	 * 验证，失败抛出异常
	 * @param obj
	 */
	public void validate(Object obj, Class<?>...groups) {
		if (obj == null) return;
		// 验证
		ValidatorResult validatorResult = validateHandle(obj, groups);

		//验证失败
		if (!validatorResult.isSuccess()) {
			//获取消息枚举
			IMsgEnum msgEnum = MsgEnumContext.findByCode(validatorResult.getMessage());
			//未自定义过消息枚举，则使用默认的
			if (msgEnum == null)
				throw new RemexServerException(MsgValidatorEnum.VALIDATOR_FAILED, validatorResult.getProperty(), validatorResult.getMessage());
			else  //自定义消息枚举
				throw new RemexServerException(msgEnum);
		}
	}
}
