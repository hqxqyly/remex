package com.github.hqxqyly.remex.boot.utils;

import java.io.Closeable;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.github.hqxqyly.remex.boot.exception.RemexException;
import com.github.hqxqyly.remex.boot.exception.RemexServerException;
import com.github.hqxqyly.remex.boot.msg.IMsgEnum;
import com.github.hqxqyly.remex.boot.msg.MsgBasicEnum;

/**
 * 辅助工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class Assist {
	protected final static Logger logger = LoggerFactory.getLogger(Assist.class);
	
	/** 空字符串  */
	public final static String STR_EMPTY = "";
	
	/** 占位符 - {} */
	public final static String PLACEHOLDER_BRACE = "\\{\\}";

	/**
	 * 如果不为null，则执行action
	 * @param <T>
	 * @param val
	 * @param action
	 */
	public static <T> void consumer(T val, Consumer<T> action) {
		if (val != null) action.accept(val);
	}
	
	/**
	 * 如果不为null，则执行action
	 * @param <T>
	 * @param val
	 * @param action
	 */
	public static <T> void run(T val, Runnable action) {
		if (val != null) action.run();
	}
	
	/**
	 * 如果不为null，则执行action
	 * @param <T>
	 * @param <R>
	 * @param val
	 * @param action
	 * @return
	 */
	public static <T, R> R function(T val, Function<T, R> action) {
		return val == null ? null : action.apply(val);
	}
	
	/**
	 * 如果不为null，则执行action
	 * @param <R>
	 * @param val
	 * @param action
	 * @return
	 */
	public static <R> R supplier(Object val, Supplier<R> action) {
		return val == null ? null : action.get();
	}
	
	
	
	/**
	 * 是否为null
	 * @param val
	 * @return
	 */
	public static boolean isNull(Object val) {
		return val == null;
	}
	
	/**
	 * 是否不为null
	 * @param val
	 * @return
	 */
	public static boolean isNotNull(Object val) {
		return !isNull(val);
	}
	
	/**
	 * 是否为空
	 * @param val
	 * @return
	 */
	public static boolean isEmpty(Collection<?> val) {
		return val == null || val.size() == 0;
	}
	
	/**
	 * 是否不为空
	 * @param val
	 * @return
	 */
	public static boolean isNotEmpty(Collection<?> val) {
		return !isEmpty(val);
	}
	
	/**
	 * 是否为空
	 * @param val
	 * @return
	 */
	public static boolean isEmpty(byte[] val) {
		return val == null || val.length == 0;
	}
	
	/**
	 * 是否不为空
	 * @param val
	 * @return
	 */
	public static boolean isNotEmpty(byte[] val) {
		return !isEmpty(val);
	}
	
	/**
	 * 是否为空
	 * @param val
	 * @return
	 */
	public static boolean isBlank(Object val) {
		if (val == null) return true;
		if (val instanceof CharSequence) return StringUtils.isBlank((CharSequence) val);
		return false;
	}
	
	/**
	 * 是否不为空
	 * @param val
	 * @return
	 */
	public static boolean isNotBlank(Object val) {
		return !isBlank(val);
	}
	
	/**
	 * 是否为空
	 * @param <T>
	 * @param val
	 * @return
	 */
	public static <T> boolean isEmpty(T[] val) {
		return val == null || val.length == 0;
	}
	
	/**
	 * 是否不为空
	 * @param <T>
	 * @param val
	 * @return
	 */
	public static <T> boolean isNotEmpty(T[] val) {
		return !isEmpty(val);
	}
	
	/**
	 * 是否为空
	 * @param val
	 * @return
	 */
	public static boolean isEmpty(Map<?, ?> val) {
		return val == null || val.size() == 0;
	}
	
	/**
	 * 是否不为空
	 * @param val
	 * @return
	 */
	public static boolean isNotEmpty(Map<?, ?> val) {
		return !isEmpty(val);
	}
	
	/**
	 * 是否为true，null => false
	 * @param val
	 * @return
	 */
	public static boolean isTrue(Boolean val) {
		return val != null && val;
	}
	
	/**
	 * 是否为false，null => true
	 * @param val
	 * @return
	 */
	public static boolean isFalse(Boolean val) {
		return val != null && !val;
	}
	
	
	
	/**
	 * 默认值
	 * @param <T>
	 * @param val
	 * @param defaultVal
	 * @return
	 */
	public static <T> T defaultVal(T val, T defaultVal) {
		return val == null ? defaultVal : val;
	}
	
	/**
	 * 默认值，并执行action
	 * @param <T>
	 * @param val
	 * @param defaultVal
	 * @param action
	 * @return
	 */
	public static <T> T defaultVal(T val, T defaultVal, Consumer<T> action) {
		T finalVal = defaultVal(val, defaultVal);
		action.accept(finalVal);
		return finalVal;
	}
	
	/**
	 * 默认值，并执行action
	 * @param <T>
	 * @param val
	 * @param defaultVal
	 * @param action
	 * @return
	 */
	public static <T> void defaultValGet(Supplier<T> getAction, T defaultVal, Consumer<T> setAction) {
		setAction.accept(defaultVal(getAction.get(), defaultVal));
	}
	
	/**
	 * 默认字符串
	 * @param val
	 * @param defaultVal
	 * @return
	 */
	public static String defaultString(Object val) {
		return defaultString(val, STR_EMPTY);
	}
	
	/**
	 * 默认字符串
	 * @param val
	 * @param defaultVal
	 * @return
	 */
	public static String defaultString(Object val, Consumer<String> action) {
		return defaultString(val, STR_EMPTY, action);
	}
	
	/**
	 * 默认字符串
	 * @param val
	 * @param defaultVal
	 * @return
	 */
	public static String defaultString(Object val, String defaultVal) {
		return isBlank(val) ? defaultVal : val.toString();
	}
	
	/**
	 * 默认字符串，并执行action
	 * @param val
	 * @param defaultVal
	 * @param action
	 * @return
	 */
	public static String defaultString(Object val, String defaultVal, Consumer<String> action) {
		String finalVal = defaultString(val, defaultVal);
		action.accept(finalVal);
		return finalVal;
	}
	
	/**
	 * <pre>
	 * 默认字符串
	 * "a" => "a"
	 * "" => ""
	 * null => null
	 * </pre>
	 * @param val
	 * @return
	 */
	public static String defaultStringNull(Object val) {
		return val == null ? null : val.toString();
	}
	
	/**
	 * 默认List
	 * @param <T>
	 * @param val
	 * @return
	 */
	public static <T> List<T> defaultList(List<T> val) {
		return val == null ? newList() : val;
	}
	
	/**
	 * 默认Set
	 * @param <T>
	 * @param list
	 * @return
	 */
	public static <T> Set<T> defaultSet(Set<T> val) {
		return val == null ? newSet() : val;
	}
	
	/**
	 * 默认Map
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @return
	 */
	public static <K, V> Map<K, V> defaultMap(Map<K, V> val) {
		return val == null ? newMap() : val;
	}
	
	/**
	 * 默认true
	 * @param val
	 * @return
	 */
	public static boolean defaultTrue(Boolean val) {
		return val == null ? true : val;
	}
	
	/**
	 * 默认false
	 * @param val
	 * @return
	 */
	public static boolean defaultFalse(Boolean val) {
		return val == null ? false : val;
	}
	
	/**
	 * 默认值0
	 * @param val
	 * @return
	 */
	public static int defaultInt(Object val) {
		if (val == null) return 0;
		if (val instanceof Integer) return (int) val;
		return Integer.valueOf(val.toString());
	}
	
	/**
	 * 默认值0
	 * @param val
	 * @return
	 */
	public static long defaultLong(Object val) {
		if (val == null) return 0;
		if (val instanceof Long) return (long) val;
		return Long.valueOf(val.toString());
	}
	
	
	
	/**
	 * 不能为null，null则抛出异常
	 * @param <T>
	 * @param val
	 * @return
	 */
	public static <T> T notNull(T val) {
		return notNull(val, MsgBasicEnum.REQUIRED);
	}
	
	/**
	 * 不能为null，null则抛出异常
	 * @param <T>
	 * @param val
	 * @param msg
	 * @return
	 */
	public static <T> T notNull(T val, String msg, Object...args) {
		if (val == null)
			throw newException(msg, args);
		return val;
	}
	
	/**
	 * 不能为null，null则抛出异常
	 * @param <T>
	 * @param val
	 * @param msgEnum
	 * @param msgArgs
	 * @return
	 */
	public static <T> T notNull(T val, IMsgEnum msgEnum, Object...msgArgs) {
		if (val == null)
			throw newException(msgEnum, msgArgs);
		return val;
	}
	
	/**
	 * 不能为null，null则抛出异常，不为null则执行action
	 * @param <T>
	 * @param val
	 * @param action
	 * @return
	 */
	public static <T> T notNull(T val, Consumer<T> action) {
		notNull(val);
		action.accept(val);
		return val;
	}
	
	/**
	 * 不能为null，null则抛出异常，不为null则执行action
	 * @param <T>
	 * @param val
	 * @param action
	 * @param msg
	 * @return
	 */
	public static <T> T notNull(T val, Consumer<T> action, String msg, Object...args) {
		notNull(val, msg, args);
		action.accept(val);
		return val;
	}
	
	/**
	 * 不能为null，null则抛出异常，不为null则执行action
	 * @param <T>
	 * @param val
	 * @param action
	 * @param msgEnum
	 * @param msgArgs
	 * @return
	 */
	public static <T> T notNull(T val, Consumer<T> action, IMsgEnum msgEnum, Object...msgArgs) {
		notNull(val, msgEnum, msgArgs);
		action.accept(val);
		return val;
	}
	
	/**
	 * 不能为null，null则抛出异常，不为null则执行action
	 * @param <T>
	 * @param val
	 * @param action
	 * @return
	 */
	public static <T, R> R notNullFn(T val, Function<T, R> action) {
		notNull(val);
		return action.apply(val);
	}
	
	/**
	 * 不能为null，null则抛出异常，不为null则执行action
	 * @param <T>
	 * @param val
	 * @param action
	 * @param msg
	 * @return
	 */
	public static <T, R> R notNullFn(T val, Function<T, R> action, String msg, Object...args) {
		notNull(val, msg, args);
		return action.apply(val);
	}
	
	/**
	 * 不能为null，null则抛出异常，不为null则执行action
	 * @param <T>
	 * @param val
	 * @param action
	 * @param msgEnum
	 * @param msgArgs
	 * @return
	 */
	public static <T, R> R notNullFn(T val, Function<T, R> action, IMsgEnum msgEnum, Object...msgArgs) {
		notNull(val, msgEnum, msgArgs);
		return action.apply(val);
	}
	
	/**
	 * 不能为blank，blank则抛出异常
	 * @param <T>
	 * @param val
	 * @return
	 */
	public static String notBlank(String val) {
		return notBlank(val, MsgBasicEnum.REQUIRED);
	}
	
	/**
	 * 不能为blank，blank则抛出异常
	 * @param val
	 * @param msg
	 * @return
	 */
	public static String notBlank(String val, String msg, Object...args) {
		if (isBlank(val))
			throw newException(msg, args);
		return val;
	}
	
	/**
	 * 不能为blank，blank则抛出异常
	 * @param val
	 * @param msgEnum
	 * @param msgArgs
	 * @return
	 */
	public static String notBlank(String val, IMsgEnum msgEnum, Object...msgArgs) {
		if (isBlank(val))
			throw newException(msgEnum, msgArgs);
		return val;
	}
	
	/**
	 * 不能为blank，blank则抛出异常，不为blank则执行action
	 * @param val
	 * @param action
	 * @return
	 */
	public static String notBlank(String val, Consumer<String> action) {
		notBlank(val);
		action.accept(val);
		return val;
	}
	
	/**
	 * 不能为blank，blank则抛出异常，不为blank则执行action
	 * @param val
	 * @param action
	 * @param msg
	 * @return
	 */
	public static String notBlank(String val, Consumer<String> action, String msg, Object...args) {
		notBlank(val, msg, args);
		action.accept(val);
		return val;
	}
	
	/**
	 * 不能为blank，blank则抛出异常，不为blank则执行action
	 * @param val
	 * @param action
	 * @param msgEnum
	 * @param msgArgs
	 * @return
	 */
	public static String notBlank(String val, Consumer<String> action, IMsgEnum msgEnum, Object...msgArgs) {
		notBlank(val, msgEnum, msgArgs);
		action.accept(val);
		return val;
	}
	
	/**
	 * 不能为blank，blank则抛出异常，不为blank则执行action
	 * @param <R>
	 * @param val
	 * @param action
	 * @return
	 */
	public static <R> R notBlankFn(String val, Function<String, R> action) {
		notBlank(val);
		return action.apply(val);
	}
	
	/**
	 * 不能为blank，blank则抛出异常，不为blank则执行action
	 * @param <R>
	 * @param val
	 * @param action
	 * @param msg
	 * @return
	 */
	public static <R> R notBlankFn(String val, Function<String, R> action, String msg, Object...args) {
		notBlank(val, msg, args);
		return action.apply(val);
	}
	
	/**
	 * 不能为blank，blank则抛出异常，不为blank则执行action
	 * @param <R>
	 * @param val
	 * @param action
	 * @param msgEnum
	 * @param msgArgs
	 * @return
	 */
	public static <R> R notBlankFn(String val, Function<String, R> action, IMsgEnum msgEnum, Object...msgArgs) {
		notBlank(val, msgEnum, msgArgs);
		return action.apply(val);
	}
	
	/**
	 * 不能为empty，empty则抛出异常
	 * @param <T>
	 * @param val
	 * @return
	 */
	public static <T extends Collection<?>> T notEmpty(T val) {
		notEmpty(val, MsgBasicEnum.REQUIRED);
		return val;
	}
	
	/**
	 * 不能为empty，empty则抛出异常
	 * @param <T>
	 * @param val
	 * @param msg
	 * @return
	 */
	public static <T extends Collection<?>> T notEmpty(T val, String msg, Object...args) {
		if (isEmpty(val))
			throw newException(msg, args);
		return val;
	}
	
	/**
	 * 不能为empty，empty则抛出异常
	 * @param <T>
	 * @param val
	 * @param msgEnum
	 * @param msgArgs
	 * @return
	 */
	public static <T extends Collection<?>> T notEmpty(T val, IMsgEnum msgEnum, Object...msgArgs) {
		if (isEmpty(val))
			throw newException(msgEnum, msgArgs);
		return val;
	}
	
	/**
	 * 不能为empty，empty则抛出异常
	 * @param <T>
	 * @param val
	 * @return
	 */
	public static <T> T[] notEmpty(T[] val) {
		notEmpty(val, MsgBasicEnum.REQUIRED);
		return val;
	}
	
	/**
	 * 不能为empty，empty则抛出异常
	 * @param <T>
	 * @param val
	 * @param msg
	 * @return
	 */
	public static <T> T[] notEmpty(T[] val, String msg, Object...args) {
		if (isEmpty(val))
			throw newException(msg, args);
		return val;
	}
	
	/**
	 * 不能为empty，empty则抛出异常
	 * @param <T>
	 * @param val
	 * @param msgEnum
	 * @param msgArgs
	 * @return
	 */
	public static <T> T[] notEmpty(T[] val, IMsgEnum msgEnum, Object...msgArgs) {
		if (isEmpty(val))
			throw newException(msgEnum, msgArgs);
		return val;
	}
	
	/**
	 * 不能为empty，empty则抛出异常
	 * @param <T>
	 * @param val
	 * @param msg
	 * @return
	 */
	public static byte[] notEmpty(byte[] val, String msg, Object...args) {
		if (isEmpty(val))
			throw newException(msg, args);
		return val;
	}
	
	/**
	 * 不能为empty，empty则抛出异常
	 * @param <T>
	 * @param val
	 * @param msgEnum
	 * @param msgArgs
	 * @return
	 */
	public static byte[] notEmpty(byte[] val, IMsgEnum msgEnum, Object...msgArgs) {
		if (isEmpty(val))
			throw newException(msgEnum, msgArgs);
		return val;
	}
	
	/**
	 * 不能相等
	 * @param val1
	 * @param val2
	 * @param msg
	 */
	public static void notEquals(Object val1, Object val2, String msg, Object...args) {
		if (val1 == null) {
			if (val2 == null)
				throw newException(msg, args);
			else
				return;
		}
		if (val2 == null || val1.equals(val2))
			throw newException(msg, args);
	}
	
	/**
	 * 不能相等
	 * @param val1
	 * @param val2
	 * @param msgEnum
	 * @param msgArgs
	 */
	public static void notEquals(Object val1, Object val2, IMsgEnum msgEnum, Object...msgArgs) {
		if (val1 == null) {
			if (val2 == null)
				throw newException(msgEnum, msgArgs);
			else
				return;
		}
		if (val2 == null || val1.equals(val2))
			throw newException(msgEnum, msgArgs);
	}
	
	
	
	/**
	 * 必须为null，不为null则抛出异常
	 * @param val
	 * @param msg
	 * @return
	 */
	public static <T> void mustNull(Object val, String msg, Object...args) {
		if (val == null)
			throw newException(msg, args);
	}
	
	/**
	 * 必须为null，不为null则抛出异常
	 * @param val
	 * @param msgEnum
	 * @param msgArgs
	 * @return
	 */
	public static void mustNull(Object val, IMsgEnum msgEnum, Object...msgArgs) {
		if (val == null)
			throw newException(msgEnum, msgArgs);
	}
	
	/**
	 * 必须为blank，不为blank则抛出异常
	 * @param val
	 * @param msg
	 * @return
	 */
	public static void mustBlank(String val, String msg, Object...args) {
		if (isNotBlank(val))
			throw newException(msg, args);
	}
	
	/**
	 * 必须为blank，不为blank则抛出异常
	 * @param val
	 * @param msgEnum
	 * @param msgArgs
	 * @return
	 */
	public static void mustBlank(String val, IMsgEnum msgEnum, Object...msgArgs) {
		if (isNotBlank(val))
			throw newException(msgEnum, msgArgs);
	}
	
	/**
	 * 必须为empty，不为empty则抛出异常
	 * @param val
	 * @param msg
	 * @return
	 */
	public static void mustEmpty(Collection<?> val, String msg, Object...args) {
		if (isNotEmpty(val))
			throw newException(msg, args);
	}
	
	/**
	 * 必须为empty，不为empty则抛出异常
	 * @param val
	 * @param msgEnum
	 * @param msgArgs
	 * @return
	 */
	public static void mustEmpty(Collection<?> val, IMsgEnum msgEnum, Object...msgArgs) {
		if (isNotEmpty(val))
			throw newException(msgEnum, msgArgs);
	}
	
	/**
	 * 必须为empty，不为empty则抛出异常
	 * @param val
	 * @param msg
	 * @return
	 */
	public static void mustEmpty(Object[] val, String msg, Object...args) {
		if (isNotEmpty(val))
			throw newException(msg, args);
	}
	
	/**
	 * 必须为empty，不为empty则抛出异常
	 * @param val
	 * @param msgEnum
	 * @param msgArgs
	 * @return
	 */
	public static void mustEmpty(Object[] val, IMsgEnum msgEnum, Object...msgArgs) {
		if (isNotEmpty(val))
			throw newException(msgEnum, msgArgs);
	}
	
	/**
	 * 必须相等
	 * @param val1
	 * @param val2
	 * @param msg
	 */
	public static void mustEquals(Object val1, Object val2, String msg, Object...args) {
		if (val1 == null) {
			if (val2 == null)
				return;
			else
				throw newException(msg, args);
		}
		if (val2 != null && !val1.equals(val2))
			throw newException(msg, args);
	}
	
	/**
	 * 必须相等
	 * @param val1
	 * @param val2
	 * @param msgEnum
	 * @param msgArgs
	 */
	public static void mustEquals(Object val1, Object val2, IMsgEnum msgEnum, Object...msgArgs) {
		if (val1 == null) {
			if (val2 == null)
				return;
			else
				throw newException(msgEnum, msgArgs);
		}
		if (val2 != null && !val1.equals(val2))
			throw newException(msgEnum, msgArgs);
	}
	
	/**
	 * 必须为true，null => false
	 * @param val
	 * @param msgEnum
	 * @param msgArgs
	 */
	public static void mustTrue(Boolean val, String msg, Object...args) {
		if (!isTrue(val))
			throw newException(msg, args);
	}
	
	/**
	 * 必须为true，null => false
	 * @param val
	 * @param msgEnum
	 * @param msgArgs
	 */
	public static void mustTrue(Boolean val, IMsgEnum msgEnum, Object...msgArgs) {
		if (!isTrue(val))
			throw newException(msgEnum, msgArgs);
	}
	
	/**
	 * 必须为false，null => true
	 * @param val
	 * @param msgEnum
	 * @param msgArgs
	 */
	public static void mustFalse(Boolean val, String msg, Object...args) {
		if (!isFalse(val))
			throw newException(msg, args);
	}
	
	/**
	 * 必须为false，null => true
	 * @param val
	 * @param msgEnum
	 * @param msgArgs
	 */
	public static void mustFalse(Boolean val, IMsgEnum msgEnum, Object...msgArgs) {
		if (!isFalse(val))
			throw newException(msgEnum, msgArgs);
	}
	
	
	
	
	/**
	 * 如果不为null，则执行action
	 * @param <T>
	 * @param val
	 * @param action
	 */
	public static <T> void ifNotNull(T val, Consumer<T> action) {
		consumer(val, action);
	}
	
	/**
	 * 如果不为null，则执行action
	 * @param val
	 * @param action
	 */
	public static void ifNotNull(Object val, Runnable action) {
		run(val, action);
	}

	/**
	 * 如果不为null，则执行action
	 * @param <T>
	 * @param <R>
	 * @param val
	 * @param action
	 * @return
	 */
	public static <T, R> R ifNotNullFn(T val, Function<T, R> action) {
		return function(val, action);
	}
	
	/**
	 * 如果不为null，则执行action
	 * @param <R>
	 * @param val
	 * @param action
	 * @return
	 */
	public static <R> R ifNotNullFn(Object val, Supplier<R> action) {
		return supplier(val, action);
	}
	
	/**
	 * 如果不为blank，则执行action
	 * @param val
	 * @param action
	 */
	public static void ifNotBlank(String val, Consumer<String> action) {
		if (isNotBlank(val))
			action.accept(val);
	}
	
	/**
	 * 如果不为blank，则执行action
	 * @param action
	 */
	public static void ifNotBlank(String val, Runnable action) {
		if (isNotBlank(val))
			action.run();
	}
	
	/**
	 * 如果不为blank，则执行action
	 * @param <R>
	 * @param val
	 * @param action
	 */
	public static <R> R ifNotBlankFn(String val, Function<String, R> action) {
		return isNotBlank(val) ? action.apply(val) : null;
	}
	
	
	
	/**
	 * 如果不为null，则forEach执行action
	 * @param <T>
	 * @param val
	 * @param action
	 */
	public static <T> void forEach(Stream<T> val, Consumer<T> action) {
		if (val != null)
			val.forEach(action);
	}
	
	/**
	 * 如果不为null，则forEach执行action
	 * @param <T>
	 * @param val
	 * @param action
	 */
	public static <T> void forEach(Collection<T> val, Consumer<T> action) {
		if (val != null)
			val.forEach(action);
	}
	
	/**
	 * 如果不为null，则forEach执行action
	 * @param <T>
	 * @param val
	 * @param action
	 */
	public static <T> void forEach(T[] val, Consumer<T> action) {
		if (val != null)
			Arrays.stream(val).forEach(action);
	}
	
	/**
	 * 如果不为null，则forEach执行action
	 * @param <K>
	 * @param <V>
	 * @param val
	 * @param action
	 */
	public static <K, V> void forEach(Map<K, V> val, BiConsumer<K, V> action) {
		if (val != null)
			val.forEach(action);
	}
	
	/**
	 * 如果不为null，则forEach执行action，并转List
	 * @param <T>
	 * @param <R>
	 * @param val
	 * @param action
	 * @return
	 */
	public static <T, R> List<R> forEachToList(Stream<T> val, Function<T, R> action) {
		return val == null ? newList() : toList(val.map(action));
	}
	
	/**
	 * 如果不为null，则forEach执行action，并转List
	 * @param <T>
	 * @param <R>
	 * @param val
	 * @param action
	 * @return
	 */
	public static <T, R> List<R> forEachToList(Collection<T> val, Function<T, R> action) {
		return val == null ? newList() : toList(val.stream().map(action));
	}
	
	/**
	 * 如果不为null，则forEach执行action，并转List
	 * @param <T>
	 * @param <R>
	 * @param val
	 * @param action
	 * @return
	 */
	public static <T, R> List<R> forEachToList(T[] val, Function<T, R> action) {
		return val == null ? newList() : toList(Arrays.stream(val).map(action));
	}
	
	/**
	 * 如果不为null，则forEach执行action，并转List
	 * @param <R>
	 * @param <K>
	 * @param <V>
	 * @param val
	 * @param action
	 * @return
	 */
	public static <R, K, V> List<R> forEachToList(Map<K, V> val, BiFunction<K, V, R> action) {
		List<R> list = newList();
		forEach(val, (k, v) -> list.add(action.apply(k, v)));
		return list;
	}
	
	/**
	 * 如果不为null，则forEach执行action，并转Set
	 * @param <T>
	 * @param <R>
	 * @param val
	 * @param action
	 * @return
	 */
	public static <T, R> Set<R> forEachToSet(Stream<T> val, Function<T, R> action) {
		return val == null ? newSet() : toSet(val.map(action));
	}
	
	/**
	 * 如果不为null，则forEach执行action，并转Set
	 * @param <T>
	 * @param <R>
	 * @param val
	 * @param action
	 * @return
	 */
	public static <T, R> Set<R> forEachToSet(Collection<T> val, Function<T, R> action) {
		return val == null ? newSet() : toSet(val.stream().map(action));
	}
	
	/**
	 * 如果不为null，则forEach执行action，并转Set
	 * @param <T>
	 * @param <R>
	 * @param val
	 * @param action
	 * @return
	 */
	public static <T, R> Set<R> forEachToSet(T[] val, Function<T, R> action) {
		return val == null ? newSet() : toSet(Arrays.stream(val).map(action));
	}
	
	/**
	 * 如果不为null，则forEach执行action，并转Set
	 * @param <R>
	 * @param <K>
	 * @param <V>
	 * @param val
	 * @param action
	 * @return
	 */
	public static <R, K, V> Set<R> forEachToSet(Map<K, V> val, BiFunction<K, V, R> action) {
		Set<R> list = newSet();
		forEach(val, (k, v) -> list.add(action.apply(k, v)));
		return list;
	}
	
	/**
	 * 如果不为null，则forEach执行action，并转Map
	 * @param <T>
	 * @param <K>
	 * @param <V>
	 * @param val
	 * @param keyAction key action
	 * @param valueAction value action
	 * @return
	 */
	public static <T, K, V> Map<K, V> forEachToMap(Collection<T> val, Function<T, K> keyAction, Function<T, V> valueAction) {
		return val == null ? newMap() : val.stream().collect(Collectors.toMap(keyAction, valueAction));
	}
	
	/**
	 * 如果不为null，则forEach执行action，并转Map，以当前元素做value
	 * @param <T>
	 * @param <K>
	 * @param <V>
	 * @param val
	 * @param keyAction
	 * @return
	 */
	public static <K, V> Map<K, V> forEachToMap(Collection<V> val, Function<V, K> keyAction) {
		return val == null ? newMap() : val.stream().collect(Collectors.toMap(keyAction, o -> o));
	}
	
	/**
	 * 如果不为null，则for有序执行action
	 * @param <T>
	 * @param val
	 * @param action
	 */
	public static <T> void forEachOrdered(List<T> val, BiConsumer<Integer, T> action) {
		if (isEmpty(val)) return;
		for (int i = 0; i < val.size(); i++)
			action.accept(i, val.get(i));
	}
	
	/**
	 * 如果不为null，则for有序执行action
	 * @param <T>
	 * @param val
	 * @param action
	 */
	public static <T> void forEachOrdered(T[] val, BiConsumer<Integer, T> action) {
		if (isEmpty(val)) return;
		for (int i = 0; i < val.length; i++)
			action.accept(i, val[i]);
	}
	
	
	
	/**
	 * 创建List
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> newList() {
		return new ArrayList<>();
	}
	
	/**
	 * 创建Set
	 * @param <T>
	 * @return
	 */
	public static <T> Set<T> newSet() {
		return new HashSet<>();
	}
	
	/**
	 * 创建Map
	 * @param <K>
	 * @param <V>
	 * @return
	 */
	public static <K, V> Map<K, V> newMap() {
		return new HashMap<>();
	}
	
	/**
	 * 创建实例
	 * @param clazz
	 */
	public static <T> T newInstance(Class<T> clazz) {
		return BeanUtils.newInstance(clazz);
	}
	
	
	
	/**
	 * 转List
	 * @param <T>
	 * @param val
	 * @return
	 */
	public static <T> List<T> toList(Stream<T> stream) {
		return ifNotNullFn(stream, () -> stream.collect(Collectors.toList()));
	}
	
	/**
	 * 转List
	 * @param <T>
	 * @param val
	 * @return
	 */
	public static <T> List<T> toList(T[] val) {
		return ifNotNullFn(val, () -> toList(Arrays.stream(val)));
	}
	
	/**
	 * 转List
	 * @param <T>
	 * @param val
	 * @return
	 */
	public static <T> List<T> toList(Collection<T> val) {
		if (val == null) return null;
		if (val instanceof List) return (List<T>) val;
		return toList(val.stream());
	}
	
	/**
	 * 转Set
	 * @param <T>
	 * @param val
	 * @return
	 */
	public static <T> Set<T> toSet(Stream<T> stream) {
		return ifNotNullFn(stream, () -> stream.collect(Collectors.toSet()));
	}
	
	/**
	 * 转Set
	 * @param <T>
	 * @param val
	 * @return
	 */
	public static <T> Set<T> toSet(T[] val) {
		return ifNotNullFn(val, () -> toSet(Arrays.stream(val)));
	}
	
	/**
	 * 转Set
	 * @param <T>
	 * @param val
	 * @return
	 */
	public static <T> Set<T> toSet(Collection<T> val) {
		if (val == null) return null;
		if (val instanceof Set) return (Set<T>) val;
		return toSet(val.stream());
	}
	
	/**
	 * 转String
	 * @param val
	 * @return
	 */
	public static String toString(Object val) {
		return val == null ? null : val.toString();
	}
	
	/**
	 * 转Integer
	 * @param val
	 * @return
	 */
	public static Integer toInteger(Object val) {
		if (val == null) return null;
		if (val instanceof Integer) return (Integer) val;
		return Integer.valueOf(val.toString());
	}
	
	/**
	 * 转Long
	 * @param val
	 * @return
	 */
	public static Long toLong(Object val) {
		if (val == null) return null;
		if (val instanceof Long) return (Long) val;
		return Long.valueOf(val.toString());
	}
	
	/**
	 * 转Double
	 * @param val
	 * @return
	 */
	public static Double toDouble(Object val) {
		if (val == null) return null;
		if (val instanceof Double) return (Double) val;
		return Double.valueOf(val.toString());
	}
	
	/**
	 * 转BigDecimal
	 * @param val
	 * @return
	 */
	public static BigDecimal toBigDecimal(Object val) {
		if (val == null) return null;
		if (val instanceof BigDecimal) return (BigDecimal) val;
		return new BigDecimal(val.toString());
	}
	
	/**
	 * 转Number
	 * @param val
	 * @return
	 */
	public static Number toNumber(Object val) {
		if (val == null) return null;
		if (val instanceof Number) return (Number) val;
		//有可能是数字值的字符串，此时尝试转换为BigDecimal
		return new BigDecimal(val.toString());
	}
	
	/**
	 * 转数组
	 * @param <T>
	 * @param val
	 * @return
	 */
	public static <T> T[] toArray(Collection<T> val, IntFunction<T[]> action) {
		return ifNotNullFn(val, () -> val.stream().toArray(action));
	}
	
	/**
	 * 转数组 - 字符串
	 * @param <T>
	 * @param val
	 * @return
	 */
	public static String[] toArrayString(Collection<String> val) {
		return toArray(val, String[]::new);
	}
	
	/**
	 * <pre>
	 * 转json
	 * null => null
	 * "abc" => "abc"
	 * 对象 => JSONObject.toJSONString(对象)
	 * </pre>
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		if (obj == null) return null;
		if (obj instanceof String) return (String) obj;
		return JSONObject.toJSONString(obj);
	}
	
	/**
	 * <pre>
	 * 转json
	 * null => "{}"
	 * "abc" => "abc"
	 * 对象 => JSONObject.toJSONString(对象)
	 * </pre>
	 * @param obj
	 * @return
	 */
	public static String toJsonDefault(Object obj) {
		if (obj == null) return "{}";
		if (obj instanceof String) return (String) obj;
		return JSONObject.toJSONString(obj);
	}
	
	/**
	 * 对象转换为json，偏向于打印日志使用
	 * @param obj
	 * @return
	 */
	public static String toJsonLog(Object obj) {
		return JsonUtils.toJsonLog(obj);
	}
	
	/**
	 * <pre>
	 * json转对象
	 * null => null
	 * "" => null
	 * " " => null
	 * 有值 =>  JSONObject.parseObject(json, clazz)
	 * </pre>
	 * @param <T>
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T toJsonBean(String json, Class<T> clazz) {
		notNull(clazz, "clazz cannot be null");
		if (isBlank(json)) return null;
		return JSONObject.parseObject(json, clazz);
	}
	
	/**
	 * <pre>
	 * json列表转List
	 * null => null
	 * "" => null
	 * " " => null
	 * 有值 =>  JSONObject.parseObject(json, clazz)
	 * </pre>
	 * @param <T>
	 * @param jsonList
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> toJsonBeanList(Collection<String> jsonList, Class<T> clazz) {
		notNull(clazz, "clazz cannot be null");
		return forEachToList(jsonList, json -> toJsonBean(json, clazz));
	}
	
	/**
	 * <pre>
	 * json列表转Set
	 * null => null
	 * "" => null
	 * " " => null
	 * 有值 =>  JSONObject.parseObject(json, clazz)
	 * </pre>
	 * @param <T>
	 * @param jsonList
	 * @param clazz
	 * @return
	 */
	public static <T> Set<T> toJsonBeanSet(Collection<String> jsonList, Class<T> clazz) {
		notNull(clazz, "clazz cannot be null");
		return forEachToSet(jsonList, json -> toJsonBean(json, clazz));
	}
	
	/**
	 * 对象转换
	 * @param <T>
	 * @param src
	 * @param destClass
	 * @return
	 */
	public static <T> T toBean(Object src, Class<T> destClass) {
		return BeanUtils.toBean(src, destClass);
	}
	
	/**
	 * 对象列表转换
	 * @param <T>
	 * @param srcList
	 * @param destClass
	 * @return
	 */
	public static <T> List<T> toBeanList(Collection<?> srcList, Class<T> destClass) {
		return BeanUtils.toBeanList(srcList, destClass);
	}
	
	/**
	 * 对象列表转换
	 * @param <T>
	 * @param srcList
	 * @param destClass
	 * @return
	 */
	public static <T> Set<T> toBeanSet(Collection<?> srcList, Class<T> destClass) {
		return BeanUtils.toBeanSet(srcList, destClass);
	}
	
	/**
	 * 转换为树列表
	 * @param <T>
	 * @param <I>
	 * @param coll
	 * @param idAction
	 * @param parentIdAction
	 * @param addChildAction
	 * @return
	 */
	public static <T, I> List<T> toTree(Collection<T> coll, Function<T, I> idAction, Function<T, I> parentIdAction
			, BiConsumer<T, T> addChildAction) {
		if (coll == null) return null;
		List<T> treeList = new ArrayList<>();
		Map<I, T> map = forEachToMap(coll, idAction, obj -> obj);
		coll.forEach(obj -> {
			//父ID
			I parentId = parentIdAction.apply(obj);
			//为空则为根节点
			if (isBlank(parentId)) {
				treeList.add(obj);
				return;
			}
			
			//父节点
			T parentObj = map.get(parentId);
			notNull(parentObj, "parentId {} not found", parentId);
			//添加子节点
			addChildAction.accept(parentObj, obj);
		});
		return treeList;
	}
	
	
	
	/**
	 * 抛出异常
	 * @param msg
	 */
	public static RemexException newException(String msg, Object...args) {
		return new RemexException(msg, args);
	}
	
	/**
	 * 抛出异常
	 * @param msg
	 * @param e
	 */
	public static RemexException newException(String msg, Throwable e, Object...args) {
		return new RemexException(msg, e, args);
	}
	
	/**
	 * 抛出异常
	 * @param msgEnum
	 * @param msgArgs
	 */
	public static RemexServerException newException(IMsgEnum msgEnum, Object...msgArgs) {
		return new RemexServerException(msgEnum, msgArgs);
	}
	
	/**
	 * 抛出异常
	 * @param msgEnum
	 * @param e
	 * @param msgArgs
	 * @return
	 */
	public static RemexServerException newException(IMsgEnum msgEnum, Throwable e, Object...msgArgs) {
		return new RemexServerException(msgEnum, e, msgArgs);
	}
	
	/**
	 * 异常传递，如果是RemexException则直接抛出，否则新建RemexException后再抛出
	 * @param msg
	 * @param e
	 * @param args
	 */
	public static RemexException transferException(String msg, Throwable e, Object...args) {
		if (e != null && e instanceof RemexException)
			return (RemexException) e;
		return newException(msg, e, args);
	}
	
	/**
	 * 异常传递，如果是RemexException则直接抛出，否则新建RemexException后再抛出
	 * @param msgEnum
	 * @param e
	 * @param msgArgs
	 * @return
	 */
	public static RemexException transferException(IMsgEnum msgEnum, Throwable e, Object...msgArgs) {
		if (e != null && e instanceof RemexException)
			return (RemexException) e;
		return newException(msgEnum, e, msgArgs);
	}
	
	
	
	/**
	 * 添加元素
	 * @param <T>
	 * @param val
	 * @param data
	 */
	@SuppressWarnings("unchecked")
	public static <T> void add(Collection<T> val, T...data) {
		if (val != null)
			forEach(data, val::add);
	}
	
	/**
	 * 添加元素
	 * @param <T>
	 * @param val
	 * @param data
	 */
	public static <T> void add(Collection<T> val, Collection<T> data) {
		if (val != null)
			forEach(data, val::add);
	}
	
	
	
	/**
	 * 过滤，并转List
	 * @param <T>
	 * @param val
	 * @param action
	 * @return
	 */
	public static <T> List<T> filterToList(Collection<T> val, Predicate<T> action) {
		if (isEmpty(val)) return newList();
		return toList(val.stream().filter(action));
	}
	
	/**
	 * 过滤，并转List
	 * @param <T>
	 * @param val
	 * @param action
	 * @return
	 */
	public static <T> List<T> filterToList(T[] val, Predicate<T> action) {
		if (isEmpty(val)) return newList();
		return toList(Arrays.stream(val).filter(action));
	}
	
	/**
	 * 过滤，并转Set
	 * @param <T>
	 * @param val
	 * @param action
	 * @return
	 */
	public static <T> Set<T> filterToSet(Collection<T> val, Predicate<T> action) {
		if (isEmpty(val)) return newSet();
		return toSet(val.stream().filter(action));
	}
	
	/**
	 * 过滤，并转Set
	 * @param <T>
	 * @param val
	 * @param action
	 * @return
	 */
	public static <T> Set<T> filterToSet(T[] val, Predicate<T> action) {
		if (isEmpty(val)) return newSet();
		return toSet(Arrays.stream(val).filter(action));
	}
	
	
	
	/**
	 * 初始化List
	 * @param <T>
	 * @param val
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> asList(T...val) {
		List<T> list = newList();
		forEach(val, list::add);
		return list;
	}
	
	/**
	 * 初始化Set
	 * @param <T>
	 * @param val
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Set<T> asSet(T...val) {
		Set<T> list = newSet();
		forEach(val, list::add);
		return list;
	}
	
	/**
	 * 初始化Map
	 * @param <T>
	 * @param arr 数量必须为偶数，键1, 值1, ..., 键n, 值n
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> asMap(Object...arr) {
		Map<K, V> map = new HashMap<>();
		if (isNotEmpty(arr)) {
			mustTrue(arr.length % 2 == 0, "arr length must be even");
			for (int i = 0; i < arr.length; i+=2) {
				K key = (K) arr[i];
				V value = (V) arr[i + 1];
				map.put(key, value);
			}
		}
		return map;
	}
	
	/**
	 * 初始化数组
	 * @param <T>
	 * @param val
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] asArray(T...val) {
		return val;
	}
	
	
	
	/**
	 * 排序
	 * @param <T>
	 * @param val
	 * @return
	 */
	public static <T> List<T> sorted(Collection<T> val) {
		if (isEmpty(val)) newList();
		return toList(val.stream().sorted());
	}
	
	/**
	 * 排序
	 * @param <T>
	 * @param val
	 * @return
	 */
	public static <T> List<T> sorted(Collection<T> val, Comparator<T> action) {
		if (isEmpty(val)) newList();
		return toList(val.stream().sorted(action));
	}
	
	/**
	 * 排序
	 * @param <T>
	 * @param val
	 * @return
	 */
	public static <T, R extends Comparable<R>> List<T> sorted(Collection<T> val, Function<T, R> action) {
		if (isEmpty(val)) newList();
		return toList(val.stream().sorted((o1, o2) -> {
			R v1 = action.apply(o1);
			R v2 = action.apply(o2);
			return Assist.compare(v1, v2);
		}));
	}
	
	
	
//	/**
//	 * 验证是否必填
//	 * @param <T>
//	 * @param val
//	 * @return
//	 */
//	public static <T> T requiredNotNull(T val) {
//		return notNull(val, MsgBasicEnum.REQUIRED);
//	}
//	
//	/**
//	 * 验证是否必填
//	 * @param <T>
//	 * @param val
//	 * @param fieldName 提示的字段名
//	 * @return
//	 */
//	public static <T> T requiredNotNull(T val, String fieldName) {
//		return notNull(val, MsgBasicEnum.REQUIRED_FIELD, fieldName);
//	}
//	
//	/**
//	 * 验证是否必填
//	 * @param <T>
//	 * @param val
//	 * @return
//	 */
//	public static String requiredNotBlank(String val) {
//		return notBlank(val, MsgBasicEnum.REQUIRED);
//	}
//	
//	/**
//	 * 验证是否必填
//	 * @param <T>
//	 * @param val
//	 * @param fieldName 提示的字段名
//	 * @return
//	 */
//	public static String requiredNotBlank(String val, String fieldName) {
//		return notBlank(val, MsgBasicEnum.REQUIRED_FIELD, fieldName);
//	}
	
	
	
	
	
	
	
	
	
	/**
	 * 创建主键编号，32位UUID
	 * @return
	 */
	public static String newId() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * <pre>
	 * 以{}占位符来替换字符串
	 * 例：args = ["11", "22"]; str = "A{}BC{}D";  =>　A11BC{}D
	 * </pre>
	 * @param val
	 * @param args
	 * @return
	 */
	public static String replaceBrace(String val, Object...args) {
		if (isBlank(val) || Assist.isEmpty(args)) return val;
		
		for (Object arg : args)
			val = val.replaceFirst(PLACEHOLDER_BRACE, defaultString(arg));
		
		return val;
	}
	
	/**
	 * 资源释放
	 * @param sources
	 */
	public static void close(Closeable...sources) {
		close(true, sources);
	}
	
	/**
	 * 资源释放
	 * @param isLog 是否打印日志
	 * @param sources
	 */
	public static void close(boolean isLog, Closeable...sources) {
		forEach(sources, source -> {
			try {
				if (source != null)
					source.close();
			} catch (Exception e) {
				if (isLog)
					logger.error("source close error!", e);
			}
		});
	}
	
	/**
	 * 拼接字符串
	 * @param val
	 * @return
	 */
	public static String join(Object...val) {
		StringBuilder sb = new StringBuilder();
		forEach(val, obj -> sb.append(defaultString(obj)));
		return sb.toString();
	}
	
	/**
	 * 线程休眠，Thread.sleep(millis);
	 * @param millis
	 */
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			throw newException("thread sleep error", e);
		}
	}
	
	/**
	 * 比较，考虑null
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static <T extends Comparable<T>> int compare(T o1, T o2) {
		if (o1 == null) return o2 == null ? 0 : -1;
		if (o2 == null) return 1;
		return o1.compareTo(o2);
	}
	
	/**
	 * <pre>
	 * 是否相等
	 * o1 = null; o2 = null  =>  true
	 * o1 = null; o2 = 'aa'  =>  false
	 * o1 = 'aa'; o2 = null  =>  false
	 * o1 = 'aa'; o2 = 'aa'  => true
	 * o1 = 'aa'; o2 = 'bb'  => false
	 * </pre>
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static boolean equals(Object o1, Object o2) {
		if (o1 == null) return o2 == null;
		if (o2 == null) return false;
		return o1.equals(o2);
	}
	
	/**
	 * trim字符串
	 * @param val
	 * @return
	 */
	public static String trim(String val) {
		return val == null ? STR_EMPTY : val.trim();
	}
	
	/**
	 * trim字符串，并最后返回List
	 * @param val
	 * @return
	 */
	public static List<String> trimToList(Collection<String> val) {
		return forEachToList(val, Assist::trim);
	}
	
	/**
	 * trim字符串，并最后返回List
	 * @param val
	 * @return
	 */
	public static List<String> trimToList(String[] val) {
		return forEachToList(val, Assist::trim);
	}
	
	/**
	 * 字符串切割，并转换为List
	 * @param str
	 * @param regex
	 * @return
	 */
	public static List<String> splitToList(Object val, String regex) {
		notBlank(regex, "regex cannot be blank");
		if (val == null) return null;
		return toList(val.toString().split(regex));
	}
	
	/**
	 * 字符串切割，trim字符串，并转换为List
	 * @param str
	 * @param regex
	 * @return
	 */
	public static List<String> splitTrimToList(Object val, String regex) {
		notBlank(regex, "regex cannot be blank");
		if (val == null) return null;
		return trimToList(val.toString().split(regex));
	}
	
	/**
	 * <pre>
	 * 如果值存在则循环执行consumer，否则不做任何事情
	 * value类型：单对象、Stream、Collection、数组、Map（只取value）
	 * </pre>
	 * @param <T>
	 * @param coll
	 * @param action
	 */
	@SuppressWarnings("unchecked")
	public static <T> void tryEach(Object value, Consumer<T> action) {
		if (value == null) return;
		if (value instanceof Stream<?>) {
			forEach((Stream<T>) value, action);
		} else if (value instanceof Collection<?>) {
			forEach((Collection<T>) value, action);
		} else if (value instanceof Array) {
			forEach((T[]) value, action);
		} else if (value instanceof Map<?, ?>) {
			Map<?, T> map = (Map<?, T>) value;
			forEach(map.values(), action);
		} else {
			T obj = (T) value;
			action.accept(obj);
		}
	}
	
	/**
	 * 拷贝对象
	 * @param dest
	 * @param src
	 */
	public static void copyProperties(Object dest, Object src) {
		BeanUtils.copyProperties(dest, src);
	}
	
	/**
	 * 集合中是否有匹配上
	 * @param <T>
	 * @param coll
	 * @param action
	 * @return
	 */
	public static <T> boolean anyMatch(Collection<T> coll, Predicate<T> action) {
		if (isEmpty(coll)) return false;
		return coll.stream().anyMatch(action);
	}
}
