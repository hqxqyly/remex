package com.github.hqxqyly.remex.fast.framework.utils;

import org.springframework.boot.SpringApplication;

import com.github.hqxqyly.remex.boot.utils.ApplicationContextUtils;

/**
 * application工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class ApplicationUtils extends ApplicationContextUtils {

	/**
	 * 启动
	 * @param primarySource
	 * @param args
	 */
	public static void run(Class<?> primarySource, String... args) {
		SpringApplication.run(primarySource, args);
	}
}
