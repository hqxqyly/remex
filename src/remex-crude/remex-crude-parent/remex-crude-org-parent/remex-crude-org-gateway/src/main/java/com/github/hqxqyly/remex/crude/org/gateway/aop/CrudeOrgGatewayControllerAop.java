package com.github.hqxqyly.remex.crude.org.gateway.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.github.hqxqyly.remex.fast.common.utils.FastAopUtils;

/**
 * crude-org-gateway controller aop
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
@Aspect
@Order(999)
public class CrudeOrgGatewayControllerAop {

	@Around("execution(* com.github.hqxqyly.remex.crude.org.gateway.controller.*.*(..))")
	public Object doAround(ProceedingJoinPoint point) {
		return FastAopUtils.doAroundHandle(CrudeOrgGatewayControllerAop.class, point, true, true);
	}
}
