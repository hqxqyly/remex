package com.github.hqxqyly.remex.boot.poi.interfaces;

/**
 * excel导出列数据处理
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IWriteDataCellHandle<T> {

	/**
	 * 列数据处理
	 * @param obj
	 * @param rowIndex
	 * @param cellIndex
	 */
	Object handleCell(T obj, int rowIndex, int cellIndex);
	
	/**
	 * 行处理结束后置处理
	 * @param obj
	 * @param rowIndex 行下标
	 * @param lastCellIndex 最后的列下标
	 */
	default void rowOverAfter(T obj, int rowIndex, int lastCellIndex) {
		
	}
}
