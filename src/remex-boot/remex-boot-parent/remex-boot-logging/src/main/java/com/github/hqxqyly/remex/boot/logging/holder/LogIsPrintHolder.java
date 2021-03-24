package com.github.hqxqyly.remex.boot.logging.holder;

/**
 * 日志是否打印的当前线程值
 * 
 * @author Qiaoxin.Hong
 *
 */
public class LogIsPrintHolder {
	/** 日志是否打印的当前线程值 */
	private static final InheritableThreadLocal<Boolean> holder = new InheritableThreadLocal<>();
	
	/**
	 * 设置日志是否打印的当前线程值
	 * @param value
	 */
	public static void set(Boolean value) {
		holder.set(value);
	}
	
	/**
	 * 取得日志是否打印的当前线程值
	 * @return
	 */
	public static Boolean get() {
		return holder.get();
	}
}
