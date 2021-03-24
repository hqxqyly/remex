package com.github.hqxqyly.remex.boot.dpcf.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.hqxqyly.remex.boot.constant.BConst;
import com.github.hqxqyly.remex.boot.dpcf.annotation.DpcfOptionConverter;
import com.github.hqxqyly.remex.boot.dpcf.annotation.DpcfOptionDateFormat;
import com.github.hqxqyly.remex.boot.dpcf.annotation.DpcfOptionEnumTran;
import com.github.hqxqyly.remex.boot.dpcf.converter.DpcfConverterDateFormat;
import com.github.hqxqyly.remex.boot.dpcf.converter.DpcfConverterEnumTran;
import com.github.hqxqyly.remex.boot.dpcf.interfaces.IDpcfConvert;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.boot.utils.BeanUtils;

/**
 * 数据属性转换提取器
 * 
 * @author Qiaoxin.Hong
 *
 */
public class DataPropertyConvertFetcher {

	/** 属性转换器列表 */
	protected Map<String, List<IDpcfConvert>> dpcfConvertMap = new HashMap<>();
	
	/**
	 * 属性提取
	 * @param data 数据
	 * @param propertyData 属性值
	 * @param property 属性名
	 * @return
	 */
	public Object fetch(Object data, Object propertyData, String property) {
		if (data == null || propertyData == null) return null;
		//属性是否排除
		if (isPropertyExclude(property)) return null;
		
		List<IDpcfConvert> dpcfConvertList = null;
		if (dpcfConvertMap.containsKey(property)) {
			dpcfConvertList = dpcfConvertMap.get(property);
		} else {
			dpcfConvertList = new ArrayList<>();
			
			Field field = BeanUtils.getDeclaredField(data.getClass(), property);
			if (field != null) {
				//dpcf配置项 - 日期格式化
				DpcfOptionDateFormat dpcfOptionDateFormat = field.getAnnotation(DpcfOptionDateFormat.class);
				if (dpcfOptionDateFormat != null) {
					DpcfConverterDateFormat dpcfConverterDateFormat = BeanUtils.newInstance(dpcfOptionDateFormat.converter());
					dpcfConverterDateFormat.setDpcfDateFormat(dpcfOptionDateFormat);
					dpcfConvertList.add(dpcfConverterDateFormat);
				}
				
				//dpcf配置项 - 枚举转换
				DpcfOptionEnumTran dpcfOptionEnumTran = field.getAnnotation(DpcfOptionEnumTran.class);
				if (dpcfOptionEnumTran != null) {
					DpcfConverterEnumTran dpcfConverterEnumTran = BeanUtils.newInstance(dpcfOptionEnumTran.converter());
					dpcfConverterEnumTran.setDpcfEnumTran(dpcfOptionEnumTran);
					dpcfConvertList.add(dpcfConverterEnumTran);
				}
				
				//dpcf配置项 - 自定义转换器
				DpcfOptionConverter dpcfOptionConverter = field.getAnnotation(DpcfOptionConverter.class);
				if (dpcfOptionConverter != null) {
					IDpcfConvert dpcfConvert = BeanUtils.newInstance(dpcfOptionConverter.value());
					dpcfConvertList.add(dpcfConvert);
				}
			}
			
			//排序
			dpcfConvertList = Assist.sorted(dpcfConvertList, IDpcfConvert::sort);
			dpcfConvertMap.put(property, dpcfConvertList);
		}
		
		if (Assist.isNotEmpty(dpcfConvertList)) {
			//属性转换
			for (IDpcfConvert dpcfConvert : dpcfConvertList) {
				propertyData = dpcfConvert.convert(propertyData, propertyData);
			}
		}
		
		return propertyData;
	}
	
	
	
	
	/**
	 * 属性是否排除，属性为空或为-1，则排除
	 * @param property
	 * @return
	 */
	protected boolean isPropertyExclude(String property) {
		return Assist.isBlank(property) || BConst.STR_MINUS_ONE.equals(property);
	}
}
