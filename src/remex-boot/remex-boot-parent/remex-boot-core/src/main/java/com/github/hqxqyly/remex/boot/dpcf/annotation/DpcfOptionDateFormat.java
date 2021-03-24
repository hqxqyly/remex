package com.github.hqxqyly.remex.boot.dpcf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.hqxqyly.remex.boot.dpcf.converter.DpcfConverterDateFormat;

/**
 * dpcf配置项 - 日期格式化
 * 
 * @author Qiaoxin.Hong
 *
 */
@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DpcfOptionDateFormat {

	/**
	 * 日期格式化格式
	 * @return
	 */
	String value() default "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 转换器
	 * @return
	 */
	Class<? extends DpcfConverterDateFormat> converter() default DpcfConverterDateFormat.class;
}
