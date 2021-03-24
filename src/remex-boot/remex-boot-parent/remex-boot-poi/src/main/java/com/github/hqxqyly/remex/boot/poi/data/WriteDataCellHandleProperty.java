package com.github.hqxqyly.remex.boot.poi.data;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.hqxqyly.remex.boot.constant.BConst;
import com.github.hqxqyly.remex.boot.poi.interfaces.IWriteDataCellHandle;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * excel导出列数据处理 - 属性提取
 * 
 * @author Qiaoxin.Hong
 *
 */
public class WriteDataCellHandleProperty<T> implements IWriteDataCellHandle<T> {
	
	/** 属性集 */
	protected List<String> fieldList;
	
	/** 缓存属性解析后的method */
	protected Map<String, Method> propertyMethodMap = new HashMap<String, Method>();
	
	public WriteDataCellHandleProperty(List<String> fieldList) {
		this.fieldList = Assist.trimToList(fieldList);
	}

	/**
	 * 列数据处理
	 * @param obj
	 * @param rowIndex
	 * @param cellIndex
	 */
	@Override
	public Object handleCell(T obj, int rowIndex, int cellIndex) {
		if (obj == null) return null;
		
		try {
			String property = fieldList.get(cellIndex);
			
			//属性是否排除
			if (isPropertyExclude(property)) return null;
			
			Method method = propertyMethodMap.get(property);
			//method不存在，生成method
			if (method == null) {
				synchronized (this) {
					method = propertyMethodMap.get(property);
					if (method == null) {
						PropertyDescriptor pdes = new PropertyDescriptor(property, obj.getClass());
						method = pdes.getReadMethod();
						propertyMethodMap.put(property, method);
					}
				}
			}
			return method.invoke(obj);
		} catch (Exception e) {
			throw Assist.transferException("poi excel write handle cell error", e);
		}
	}
	
	/**
	 * 属性是否排除，属性为空或为-1，则排除
	 * @param property
	 * @return
	 */
	protected boolean isPropertyExclude(String property) {
		return Assist.isBlank(property) || BConst.STR_MINUS_ONE.equals(property);
	}
	
	
	
	
	
	public WriteDataCellHandleProperty<T> setFieldList(List<String> fieldList) {
		this.fieldList = fieldList;
		return this;
	}
	
	public List<String> getFieldList() {
		return fieldList;
	}
	
	public Map<String, Method> getPropertyMethodMap() {
		return propertyMethodMap;
	}
}
