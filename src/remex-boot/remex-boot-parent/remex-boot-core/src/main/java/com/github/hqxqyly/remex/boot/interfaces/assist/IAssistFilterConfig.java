package com.github.hqxqyly.remex.boot.interfaces.assist;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;

/**
 * 辅助接口 - 过滤器配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IAssistFilterConfig extends IAssist {

	/**
	 * 创建过滤器注册类
	 * @return
	 */
	default <T extends Filter> FilterRegistrationBean<T> createFilterRegistration(T filter, int order) {
		FilterRegistrationBean<T> bean = new FilterRegistrationBean<>();
		bean.setFilter(filter);
		bean.setOrder(order);
		return bean;
	}
}
