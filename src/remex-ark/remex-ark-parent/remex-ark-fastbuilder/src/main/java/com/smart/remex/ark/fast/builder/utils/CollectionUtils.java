package com.smart.remex.ark.fast.builder.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 集合工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class CollectionUtils extends org.apache.commons.collections.CollectionUtils {

	/**
	 * 默认list，null => new ArrayList<>()
	 * 
	 * @param list
	 * @return
	 */
	public static <T> List<T> defaultList(List<T> list) {
		return list == null ? new ArrayList<T>() : list;
	}
}
