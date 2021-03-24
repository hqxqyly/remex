package com.github.hqxqyly.remex.boot.dpcf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.hqxqyly.remex.boot.dpcf.converter.DpcfConverterEnumTran;

/**
 * dpcf配置项 - 枚举转换
 * 
 * @author Qiaoxin.Hong
 *
 */
@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DpcfOptionEnumTran {
	
	/**
	 * 规则，以","号隔开，key、value为一组，key为空时，则表示默认值，如：1, 男, 2, 女, , 默认
	 * @return
	 */
	String value() default "";

	/**
	 * 转换器
	 * @return
	 */
	Class<? extends DpcfConverterEnumTran> converter() default DpcfConverterEnumTran.class;
}
