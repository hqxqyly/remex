package com.github.hqxqyly.remex.boot.interfaces.assist;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.hqxqyly.remex.boot.msg.IMsgEnum;
import com.github.hqxqyly.remex.boot.utils.ApplicationContextUtils;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.boot.utils.DateUtils;

/**
 * 辅助接口
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IAssist {
	
	/**
	 * 日志记录器
	 * @return
	 */
	default Logger getLogger() {
		return LoggerFactory.getLogger(getClass());
	}
	
	/**
	 * 取得bean
	 * @param <T>
	 * @param beanClass
	 * @return
	 */
	default <T> T getBean(Class<T> beanClass) {
		return ApplicationContextUtils.getBean(beanClass);
	}
	
	

	/**
	 * 不能为blank，blank则抛出异常
	 * @param <T>
	 * @param val
	 * @return
	 */
	default String notBlank(String val) {
		return Assist.notBlank(val);
	}
	
	/**
	 * 不能为blank，blank则抛出异常
	 * @param val
	 * @param msg
	 * @return
	 */
	default String notBlank(String val, String msg) {
		return Assist.notBlank(val, msg);
	}
	
	/**
	 * 不能为blank，blank则抛出异常
	 * @param val
	 * @param msgEnum
	 * @param msgArgs
	 * @return
	 */
	default String notBlank(String val, IMsgEnum msgEnum, Object...msgArgs) {
		return Assist.notBlank(val, msgEnum, msgArgs);
	}
	
	/**
	 * 不能为null，null则抛出异常
	 * @param <T>
	 * @param val
	 * @return
	 */
	default <T> T notNull(T val) {
		return Assist.notNull(val);
	}
	
	/**
	 * 不能为null，null则抛出异常
	 * @param <T>
	 * @param val
	 * @param msg
	 * @return
	 */
	default <T> T notNull(T val, String msg) {
		return Assist.notNull(val, msg);
	}
	
	/**
	 * 不能为empty，empty则抛出异常
	 * @param <T>
	 * @param val
	 * @return
	 */
	default <T extends Collection<?>> T notEmpty(T val) {
		return Assist.notEmpty(val);
	}
	
	/**
	 * 不能为empty，empty则抛出异常
	 * @param <T>
	 * @param val
	 * @param msg
	 * @return
	 */
	default <T extends Collection<?>> T notEmpty(T val, String msg, Object...args) {
		return Assist.notEmpty(val, msg, args);
	}
	
	/**
	 * 不能为empty，empty则抛出异常
	 * @param <T>
	 * @param val
	 * @param msgEnum
	 * @param msgArgs
	 * @return
	 */
	default <T extends Collection<?>> T notEmpty(T val, IMsgEnum msgEnum, Object...msgArgs) {
		return Assist.notEmpty(val, msgEnum, msgArgs);
	}
	
	/**
	 * 不能为null，null则抛出异常
	 * @param <T>
	 * @param val
	 * @param msgEnum
	 * @param msgArgs
	 * @return
	 */
	default <T> T notNull(T val, IMsgEnum msgEnum, Object...msgArgs) {
		return Assist.notNull(val, msgEnum, msgArgs);
	}
	
	/**
	 * 是否为空
	 * @param val
	 * @return
	 */
	default boolean isEmpty(Collection<?> val) {
		return Assist.isEmpty(val);
	}
	
	/**
	 * 是否不为空
	 * @param val
	 * @return
	 */
	default boolean isNotEmpty(Collection<?> val) {
		return Assist.isNotEmpty(val);
	}
	
	/**
	 * 是否为空
	 * @param val
	 * @return
	 */
	default boolean isBlank(Object val) {
		return Assist.isBlank(val);
	}
	
	/**
	 * 是否不为空
	 * @param val
	 * @return
	 */
	default boolean isNotBlank(Object val) {
		return Assist.isNotBlank(val);
	}
	
	/**
	 * 如果不为null，则执行action
	 * @param <T>
	 * @param val
	 * @param action
	 */
	default <T> void ifNotNull(T val, Consumer<T> action) {
		Assist.ifNotNull(val, action);
	}
	
	/**
	 * 如果不为null，则执行action
	 * @param val
	 * @param action
	 */
	default void ifNotNull(Object val, Runnable action) {
		Assist.ifNotNull(val, action);
	}
	
	/**
	 * 如果不为null，则执行action
	 * @param <T>
	 * @param <R>
	 * @param val
	 * @param action
	 * @return
	 */
	default <T, R> R ifNotNullFn(T val, Function<T, R> action) {
		return Assist.ifNotNullFn(val, action);
	}
	
	/**
	 * 如果不为blank，则执行action
	 * @param val
	 * @param action
	 */
	default void ifNotBlank(String val, Consumer<String> action) {
		Assist.ifNotBlank(val, action);
	}
	
	/**
	 * 如果不为blank，则执行action
	 * @param action
	 */
	default void ifNotBlank(String val, Runnable action) {
		Assist.ifNotBlank(val, action);
	}
	
	/**
	 * 如果不为blank，则执行action
	 * @param <R>
	 * @param val
	 * @param action
	 */
	default <R> R ifNotBlankFn(String val, Function<String, R> action) {
		return Assist.ifNotBlankFn(val, action);
	}
	
	
	
	/**
	 * 对象转换
	 * @param <T>
	 * @param src
	 * @param destClass
	 * @return
	 */
	default <T> T toBean(Object src, Class<T> destClass) {
		return Assist.toBean(src, destClass);
	}
	
	/**
	 * 对象列表转换
	 * @param <T>
	 * @param srcList
	 * @param destClass
	 * @return
	 */
	default <T> List<T> toBeanList(Collection<?> srcList, Class<T> destClass) {
		return Assist.toBeanList(srcList, destClass);
	}
	
	/**
	 * 对象列表转换
	 * @param <T>
	 * @param srcList
	 * @param destClass
	 * @return
	 */
	default <T> Set<T> toBeanSet(Collection<?> srcList, Class<T> destClass) {
		return Assist.toBeanSet(srcList, destClass);
	}
	
	
	
	/**
	 * 默认值
	 * @param <T>
	 * @param val
	 * @param defaultVal
	 * @return
	 */
	default <T> T defaultVal(T val, T defaultVal) {
		return Assist.defaultVal(val, defaultVal);
	}
	
	/**
	 * 默认值，并执行action
	 * @param <T>
	 * @param val
	 * @param defaultVal
	 * @param action
	 * @return
	 */
	default <T> T defaultVal(T val, T defaultVal, Consumer<T> action) {
		return Assist.defaultVal(val, defaultVal, action);
	}
	
	/**
	 * 默认值，并执行action
	 * @param <T>
	 * @param val
	 * @param defaultVal
	 * @param action
	 * @return
	 */
	default <T> void defaultValGet(Supplier<T> getAction, T defaultVal, Consumer<T> setAction) {
		Assist.defaultValGet(getAction, defaultVal, setAction);
	}
	
	/**
	 * 默认字符串
	 * @param val
	 * @param defaultVal
	 * @return
	 */
	default String defaultString(Object val) {
		return Assist.defaultString(val);
	}
	
	/**
	 * 默认字符串
	 * @param val
	 * @param defaultVal
	 * @return
	 */
	default String defaultString(Object val, String defaultVal) {
		return Assist.defaultString(val, defaultVal);
	}
	
	/**
	 * 默认字符串，并执行action
	 * @param val
	 * @param defaultVal
	 * @param action
	 * @return
	 */
	default String defaultString(Object val, String defaultVal, Consumer<String> action) {
		return Assist.defaultString(val, defaultVal, action);
	}
	
	
	
	/**
	 * 创建主键编号，32位UUID
	 * @return
	 */
	default String newId() {
		return Assist.newId();
	}
	
	/**
	 * 取得当前时间
	 * @return
	 */
	default Timestamp getCurTimestamp() {
		return DateUtils.getCurTimestamp();
	}
	
	/**
	 * 取得当前时间
	 * @return
	 */
	default Date getCurDate() {
		return DateUtils.getCurDate();
	}
	
	/**
	 * 创建实例
	 * @param clazz
	 */
	default <T> T newInstance(Class<T> clazz) {
		return Assist.newInstance(clazz);
	}
	
}
