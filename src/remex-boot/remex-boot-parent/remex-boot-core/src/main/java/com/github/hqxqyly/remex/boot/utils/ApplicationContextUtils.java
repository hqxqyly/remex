package com.github.hqxqyly.remex.boot.utils;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

/**
 * ApplicationContext工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class ApplicationContextUtils {

	/** ApplicationContext */
	protected static ApplicationContext context;
	
	/**
	 * 设置ApplicationContext
	 * @param context
	 */
	public static void setContext(ApplicationContext context) {
		ApplicationContextUtils.context = context;
	}
	
	/**
	 * 取得ApplicationContext
	 * @return
	 */
	public static ApplicationContext getContext() {
		return context;
	}

	/**
	 * 取得bean，未取到会抛出异常
	 * @param <T>
	 * @param beanClass
	 * @return
	 */
	public static <T> T getBean(Class<T> beanClass) {
		return getContext().getBean(beanClass);
	}
	
	/**
	 * 取得bean，未取到返回null
	 * @param <T>
	 * @param beanClass
	 * @return
	 */
	public static <T> T tryBean(Class<T> beanClass) {
		try {
			return getContext().getBean(beanClass);
		} catch (NoSuchBeanDefinitionException e) {
			//未取到bean，返回null
			return null;
		}
	}
	
	/**
	 * 取得bean，取不到抛出相关异常
	 * @param <T>
	 * @param beanClass
	 * @return
	 */
	public static <T> T tryBean(Class<T> beanClass, String msg) {
		return Assist.notNull(tryBean(beanClass), msg);
	}
}
