package com.github.hqxqyly.remex.boot.validator.utils;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;

import com.github.hqxqyly.remex.boot.utils.ApplicationContextUtils;
import com.github.hqxqyly.remex.boot.validator.bean.ValidatorResult;
import com.github.hqxqyly.remex.boot.validator.client.ValidatorClient;

/**
 * 验证工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class ValidatorUtils {

	/**
	 * 验证处理器
	 * @return
	 */
	public static ValidatorClient getClient() {
		return ApplicationContextUtils.getBean(ValidatorClient.class);
	}
	
	/**
	 * 验证处理
	 * @param obj
	 * @param groups 验证组
	 */
	public static ValidatorResult validateHandle(Object obj, Class<?>... groups) {
		return getClient().validateHandle(obj, groups);
	}
	
	/**
	 * 参数验证
	 */
	public static void validate(ProceedingJoinPoint point) {
		getClient().validate(point);
	}
	
	/**
	 * 参数验证
	 */
	public static void validate(MethodInvocation invocation) {
		getClient().validate(invocation);
	}

	/**
	 * 参数验证
	 * @return
	 */
	public static void validate(Method method, Object[] args) {
		getClient().validate(method, args);
	}

	/**
	 * 验证，失败抛出异常
	 * @param obj
	 */
	public static void validate(Object obj, Class<?>...groups) {
		getClient().validate(obj, groups);
	}
}
