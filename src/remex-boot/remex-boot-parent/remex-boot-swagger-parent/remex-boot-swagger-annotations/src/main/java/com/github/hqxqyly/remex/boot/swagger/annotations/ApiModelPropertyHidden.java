package com.github.hqxqyly.remex.boot.swagger.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ApiModelProperty隐藏
 * 
 * @author Qiaoxin.Hong
 *
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiModelPropertyHidden {

	/**
	 * 组列表
	 * @return
	 */
	Class<?>[] groups() default {};
}
