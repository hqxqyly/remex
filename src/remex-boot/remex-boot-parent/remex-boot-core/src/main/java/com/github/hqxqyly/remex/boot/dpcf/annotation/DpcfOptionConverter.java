package com.github.hqxqyly.remex.boot.dpcf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.hqxqyly.remex.boot.dpcf.interfaces.IDpcfConvert;

/**
 * dpcf配置项 - 自定义转换器
 * 
 * @author Qiaoxin.Hong
 *
 */
@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DpcfOptionConverter {

	/**
	 * 转换器
	 * @return
	 */
	Class<? extends IDpcfConvert> value();
}
