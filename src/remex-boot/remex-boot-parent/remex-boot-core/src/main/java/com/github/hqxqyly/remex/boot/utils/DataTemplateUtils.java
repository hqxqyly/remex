package com.github.hqxqyly.remex.boot.utils;

import java.util.List;

/**
 * 数据模板生成器
 * 
 * @author Qiaoxin.Hong
 *
 */
public class DataTemplateUtils {

	/**
	 * 构建set方法
	 */
	public static void buildSet(Class<?> toClass, String toObjName) {
		Assist.notNull(toClass);
		toObjName = Assist.defaultString(toObjName, "req");
		
		List<String> toFieldNameList = Assist.getFieldNameList(toClass);
		
		for (String toFieldName : toFieldNameList) {
			String pdFieldName = Assist.strFirstToUpperCase(toFieldName);
			System.out.println(Assist.replaceBrace("{}.set{}(null);", toObjName, pdFieldName));
		}
	}
	
	/**
	 * 构建set方法
	 * @param clazz
	 */
	public static void buildSet(Class<?> toClass, String toObjName, Class<?> fromClass, String fromObjName) {
		Assist.notNull(toClass);
		Assist.notNull(fromClass);
		toObjName = Assist.defaultString(toObjName, "req");
		fromObjName = Assist.defaultString(fromObjName, "mainReq");
		
		List<String> toFieldNameList = Assist.getFieldNameList(toClass);
		List<String> fromFieldNameList = Assist.getFieldNameList(fromClass);
		
		for (String toFieldName : toFieldNameList) {
			String pdFieldName = Assist.strFirstToUpperCase(toFieldName);
			String valueStr = fromFieldNameList.contains(toFieldName) ? Assist.replaceBrace("{}.get{}()", fromObjName, pdFieldName) : "null";
			System.out.println(Assist.replaceBrace("{}.set{}({});", toObjName, pdFieldName, valueStr));
		}
	}
	
	
	
	
	
}
