package com.github.hqxqyly.remex.boot.poi.data;

import java.util.List;

import com.github.hqxqyly.remex.boot.poi.interfaces.IWriteDataFetch;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * excel导出数据提取器，全量数据
 * 
 * @author Qiaoxin.Hong
 *
 * @param <T>
 */
public class WriteDataFetchAll<T> implements IWriteDataFetch<T> {
	
	/** 数据列表 */
	protected List<T> dataList;
	
	public WriteDataFetchAll(List<T> dataList) {
		this.dataList = Assist.defaultList(dataList);
	}

	/**
	 * 提取数据列表
	 * @param fetchIndex 提取下标
	 * @param excelRowIndex excel行下标
	 * @return
	 */
	@Override
	public List<T> fetch(int fetchIndex, int excelRowIndex) {
		return dataList;
	}
	
	/**
	 * 是否结束提取
	 * @param fetchIndex 提取下标
	 * @param dataList 此次提取的数据列表
	 * @return
	 */
	@Override
	public boolean over(int fetchIndex, List<T> dataList) {
		return true;
	}
}
