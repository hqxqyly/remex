package com.github.hqxqyly.remex.boot.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.hqxqyly.remex.boot.exception.RemexException;

/**
 * bean工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class BeanUtils {

	/**
	 * 创建实例
	 * @param clazz
	 */
	public static <T> T newInstance(Class<T> clazz) {
		Assist.notNull(clazz, "clazz cannot be null");
		
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw Assist.newException("new instance error", e);
		}
	}
	
	/**
	 * 拷贝对象
	 * @param dest
	 * @param src
	 */
	public static void copyProperties(Object dest, Object src) {
		org.springframework.beans.BeanUtils.copyProperties(src, dest);
	}
	
	/**
	 * 对象转换
	 * @param <T>
	 * @param src
	 * @param destClass
	 * @return
	 */
	public static <T> T toBean(Object src, Class<T> destClass) {
		Assist.notNull(destClass, "destClass cannot be null");
		
		if (src == null) return null;
		
		T dest = newInstance(destClass);
		copyProperties(dest, src);
		return dest;
	}
	
	/**
	 * 对象列表转换
	 * @param <T>
	 * @param srcList
	 * @param destClass
	 * @return
	 */
	public static <T> List<T> toBeanList(Collection<?> srcList, Class<T> destClass) {
		Assist.notNull(destClass, "destClass cannot be null");
		
		if (srcList == null) return null;
		
		List<T> destList = new ArrayList<>();
		for (Object src : srcList) {
			T dest = toBean(src, destClass);
			destList.add(dest);
		}
		
		return destList;
	}
	
	/**
	 * 对象列表转换
	 * @param <T>
	 * @param srcList
	 * @param destClass
	 * @return
	 */
	public static <T> Set<T> toBeanSet(Collection<?> srcList, Class<T> destClass) {
		Assist.notNull(destClass, "destClass cannot be null");
		
		if (srcList == null) return null;
		
		Set<T> destList = new HashSet<>();
		for (Object src : srcList) {
			T dest = toBean(src, destClass);
			destList.add(dest);
		}
		
		return destList;
	}
	
	/**
	 * 从class取field，会考虑父类
	 * @param clazz
	 * @param name
	 * @return
	 */
	public static Field getDeclaredField(Class<?> clazz, String name) {
		Assist.notNull(clazz, "clazz cannot be null");
		Assist.notBlank(name, "name cannot be null");
		
		try {
			if (Object.class.equals(clazz)) return null;
			
			Field field = null;
			try {
				field = clazz.getDeclaredField(name);
			} catch (NoSuchFieldException e) {
				//未找到field不抛异常，继续找父类
			}
			if (field == null)
				field = getDeclaredField(clazz.getSuperclass(), name);
			
			return field;
		} catch (Exception e) {
			throw new RemexException("get declared field error", e);
		}
	}
}
