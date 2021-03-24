package com.github.hqxqyly.remex.crude.fileserver.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.github.hqxqyly.remex.fast.common.utils.FastAopUtils;

/**
 * crude-fileserver-service controller aop
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
@Aspect
@Order(999)
public class FileServerControllerAop {

	@Around("execution(* com.github.hqxqyly.remex.crude.fileserver.controller.*.*(..))")
	public Object doAround(ProceedingJoinPoint point) {
		return FastAopUtils.doAroundHandle(FileServerControllerAop.class, point, true, true);
	}
}
