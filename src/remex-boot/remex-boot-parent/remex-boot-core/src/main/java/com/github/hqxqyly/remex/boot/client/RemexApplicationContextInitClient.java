package com.github.hqxqyly.remex.boot.client;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.github.hqxqyly.remex.boot.utils.ApplicationContextUtils;

/**
 * ApplicationContext初始化处理器
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RemexApplicationContextInitClient implements ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextUtils.setContext(applicationContext);
	}
}
