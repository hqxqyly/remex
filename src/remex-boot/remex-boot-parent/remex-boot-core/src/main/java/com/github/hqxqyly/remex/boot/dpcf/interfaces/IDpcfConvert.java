package com.github.hqxqyly.remex.boot.dpcf.interfaces;

/**
 * dpcf数据转换
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IDpcfConvert {

	/**
	 * 数据转换
	 * @param data 数据
	 * @param propertyData 当前属性值
	 * @return
	 */
	Object convert(Object data, Object propertyData);
	
	/**
	 * 排序
	 * @return
	 */
	default int sort() {
		return Integer.MAX_VALUE;
	}
}
