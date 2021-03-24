package com.github.hqxqyly.remex.crude.sequence.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.github.hqxqyly.remex.fast.common.utils.FastAopUtils;

/**
 * 序列controller aop
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
@Aspect
@Order(999)
public class CrudeSequenceControllerAop {

	@Around("execution(* com.github.hqxqyly.remex.crude.sequence.controller.*.*(..))")
	public Object doAround(ProceedingJoinPoint point) {
		return FastAopUtils.doAroundHandle(CrudeSequenceControllerAop.class, point, true, true);
	}
}
