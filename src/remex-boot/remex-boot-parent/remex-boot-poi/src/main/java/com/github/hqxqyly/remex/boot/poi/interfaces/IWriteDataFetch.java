package com.github.hqxqyly.remex.boot.poi.interfaces;

import java.util.List;

import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * excel导出数据提取器
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IWriteDataFetch<T> {
	
	/** 默认每页数量 */
	public final static int DEFAULT_PAGE_SIZE = 100;

	/**
	 * 提取数据列表
	 * @param fetchIndex 提取下标
	 * @param excelRowIndex excel行下标
	 * @return
	 */
	List<T> fetch(int fetchIndex, int excelRowIndex);
	
	/**
	 * 是否结束提取
	 * @param fetchIndex 提取下标
	 * @param dataList 此次提取的数据列表
	 * @return
	 */
	default boolean over(int fetchIndex, List<T> dataList) {
		return Assist.isEmpty(dataList);
	}
}
