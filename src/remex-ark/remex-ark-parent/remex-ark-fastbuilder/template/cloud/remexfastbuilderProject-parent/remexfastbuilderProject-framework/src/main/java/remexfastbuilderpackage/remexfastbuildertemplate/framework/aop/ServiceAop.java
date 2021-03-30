package remexfastbuilderpackage.remexfastbuildertemplate.framework.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.github.hqxqyly.remex.fast.common.utils.FastAopUtils;

/**
 * service aop
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
@Aspect
@Order(999)
public class ServiceAop {
	
	@Around("execution(* remexfastbuilderpackage.remexfastbuildertemplate.*.service.impl.*.*(..))")
	public Object doAround(ProceedingJoinPoint point) {
		return FastAopUtils.doAroundHandle(ServiceAop.class, point, true, true);
	}
}
