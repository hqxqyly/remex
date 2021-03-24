package com.github.hqxqyly.remex.boot.poi.data;

import java.util.List;

import com.github.hqxqyly.remex.boot.dpcf.core.DataPropertyConvertFetcher;

/**
 * excel导出列数据处理 - dpcf
 * 
 * @author Qiaoxin.Hong
 *
 */
public class WriteDataCellHandleDpcf<T> extends WriteDataCellHandleProperty<T> {

	/** 数据属性转换提取器 */
	protected DataPropertyConvertFetcher dataPropertyConvertFetcher = new DataPropertyConvertFetcher();
	
	public WriteDataCellHandleDpcf(List<String> fieldList) {
		super(fieldList);
	}
	
	/**
	 * 列数据处理
	 * @param obj
	 * @param rowIndex
	 * @param cellIndex
	 */
	@Override
	public Object handleCell(T obj, int rowIndex, int cellIndex) {
		//获取原转换后的属性值
		Object val = super.handleCell(obj, rowIndex, cellIndex);
		
		String property = fieldList.get(cellIndex);
		
		//属性是否排除
		if (isPropertyExclude(property)) return null;
		
		//属性值dpcf处理
		val = dataPropertyConvertFetcher.fetch(obj, val, property);
		
		return val;
	}
}
