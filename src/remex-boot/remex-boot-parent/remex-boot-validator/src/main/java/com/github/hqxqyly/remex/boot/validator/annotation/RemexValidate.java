package com.github.hqxqyly.remex.boot.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启动数据验证的标识
 * 
 * @author Qiaoxin.Hong
 *
 */
@Target(value = {ElementType.TYPE ,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RemexValidate {

	/**
	 * 验证组
	 * @return
	 */
	Class<?>[] groups() default {};
}
