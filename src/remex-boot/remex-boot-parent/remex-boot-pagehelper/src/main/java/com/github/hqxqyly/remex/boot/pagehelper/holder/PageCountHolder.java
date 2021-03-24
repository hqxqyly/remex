package com.github.hqxqyly.remex.boot.pagehelper.holder;

/**
 * 分页数据总数量当前线程值
 * 
 * @author Qiaoxin.Hong
 *
 */
public class PageCountHolder {
	/** 分页数据总数量当前线程值 */
	private static final InheritableThreadLocal<Long> holder = new InheritableThreadLocal<>();
	
	/**
	 * 设置分页数据总数量当前线程值
	 * @param value
	 */
	public static void set(Long value) {
		holder.set(value);
	}
	
	/**
	 * 取得分页数据总数量当前线程值
	 * @return
	 */
	public static Long get() {
		return holder.get();
	}
}
