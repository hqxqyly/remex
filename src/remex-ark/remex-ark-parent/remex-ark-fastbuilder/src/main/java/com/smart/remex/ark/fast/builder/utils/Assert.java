package com.smart.remex.ark.fast.builder.utils;

import java.util.Collection;

import com.smart.remex.ark.fast.builder.exception.RemexFastBuilderException;

/**
 * 断言工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class Assert {
	
	/**
	 * 必须为true
	 * @param val
	 */
	public static void isTrue(boolean val) {
		isTrue(val, "The argument must is true");
	}
	
	/**
	 * 必须为true
	 * @param val
	 * @param msg
	 */
	public static void isTrue(boolean val, String msg) {
		if (!val ) {
			throw new RemexFastBuilderException(msg);
		}
	}

	/**
	 * 不能为空
	 * @param obj
	 * @param msgEnum
	 */
	public static void notNull(Object obj) {
		notNull(obj, "The argument must not be null");
	}
	
	/**
	 * 不能为空
	 * @param obj
	 * @param msgEnum
	 */
	public static void notNull(Object obj, String msg) {
		if (obj == null) {
			throw new RemexFastBuilderException(msg);
		}
	}
	
	/**
	 * 字符串不能为空
	 * @param obj
	 * @param msgEnum
	 */
	public static void notBlank(String str) {
		notBlank(str, "The argument must not be blank");
	}
	
	/**
	 * 字符串不能为空
	 * @param obj
	 * @param msgEnum
	 */
	public static void notBlank(String str, String msg) {
		if (StringUtils.isBlank(str)) {
			throw new RemexFastBuilderException(msg);
		}
	}
	
	/**
	 * 集合不能为空
	 * @param coll
	 */
	public static void notEmpty(Collection<?> coll) {
		notEmpty(coll, "Collection must contain elements");
	}
	
	/**
	 * 集合不能为空
	 * @param coll
	 */
	public static void notEmpty(Collection<?> coll, String msg) {
		if (CollectionUtils.isEmpty(coll)) {
			throw new RemexFastBuilderException(msg);
		}
	}
}
