package com.github.hqxqyly.remex.fast.common.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.io.InputStreamSource;
import org.springframework.web.multipart.MultipartFile;

import com.github.hqxqyly.remex.boot.logging.holder.LogIsPrintHolder;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.fast.common.annotation.rlog.InArgsLogExclude;

/**
 * aop工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class FastAopUtils {
	protected static Logger logger = LoggerFactory.getLogger(AopUtils.class);
	
	/** 入参日志排除标识 */
	public final static String IN_ARGS_JSON_EXCLUDE_FLAG = "exclude";
	
	/** 入参日志文件参数隐藏描述 */
	public final static String FILE_HIDE_NAME = "FILE_HIDE";
	
	/**
	 * 环绕通知
	 * @param aopClass
	 * @param point
	 * @param isLog 是否打印日志
	 * @param isArgsLog 是否打印参数日志
	 * @return
	 * @throws Throwable 
	 */
	public static Object doAroundHandle(Class<?> aopClass, ProceedingJoinPoint point, boolean isLog, boolean isArgsLog) {
		long currTime = System.currentTimeMillis();
		Signature signature = point.getSignature();
		String sid = Assist.newId();
		Object target = point.getTarget();
		String methodName = Assist.defaultString(target.getClass().getName()) + "."
				+ Assist.defaultString(signature.getName());
		//日志是否打印
		boolean logIsPrint = Assist.defaultTrue(LogIsPrintHolder.get());
		
		try {
			//打印开始日志
			if (isLog && logIsPrint) {
				if (isArgsLog) {
					// 提取入参json
					String argsJson = fetchInArgsJson(point);
//					logger.info("{} begin [sid : {}] [method : {}] [args : {}]", aopClass.getSimpleName(), sid, methodName, argsJson);
				} else {
//					logger.info("{} begin [sid : {}] [method : {}]", aopClass.getSimpleName(), sid, methodName);
				}
			}
			
			// 执行方法
			Object result = point.proceed();
			
			//打印结束日志
			if (isLog && logIsPrint) {
//				logger.info("{} end [sid : {}] [method : {}] [callTime : {}]"
//						, aopClass.getSimpleName(), sid, methodName, System.currentTimeMillis() - currTime);
			}
			
			return result;
		} catch (Throwable e) {
			throw FastExceptionUtils.transferException(e);
		}
	}
	
	/**
	 * 提取入参json
	 * @param point
	 * @return String
	 */
	public static String fetchInArgsJson(ProceedingJoinPoint point) {
		Method method = ((MethodSignature) point.getSignature()).getMethod();
		
		String argsJson = IN_ARGS_JSON_EXCLUDE_FLAG;
		
		//如果类上有入参日志排除标识，则不打印入参日志
		Class<?> entityClass = point.getTarget().getClass();
		if (entityClass.isAnnotationPresent(InArgsLogExclude.class)) {
			return argsJson;
		}
		
		//如果方法上有入参日志排除标识，则不打印入参日志
		if (method.isAnnotationPresent(InArgsLogExclude.class)) {
			return argsJson;
		}
		
		Object[] args = point.getArgs();
		
		List<Object> argList = new ArrayList<>();
		Assist.forEach(args, arg -> {
			//文件参数
			if (arg instanceof InputStreamSource) {
				if (arg instanceof MultipartFile) {
					MultipartFile multipartFile = (MultipartFile) arg;
					argList.add(multipartFile.getOriginalFilename());
				} else {
					argList.add(FILE_HIDE_NAME);
				}
			} else {  //其它正常参数
				argList.add(arg);
			}
		});
		
		//入参转json
		argsJson = Assist.toJsonLog(argList);
		
		return argsJson;
	}
}
