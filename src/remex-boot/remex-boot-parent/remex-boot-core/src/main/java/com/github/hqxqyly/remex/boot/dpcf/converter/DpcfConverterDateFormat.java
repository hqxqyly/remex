package com.github.hqxqyly.remex.boot.dpcf.converter;

import java.util.Date;

import com.github.hqxqyly.remex.boot.dpcf.annotation.DpcfOptionDateFormat;
import com.github.hqxqyly.remex.boot.dpcf.interfaces.IDpcfConvert;
import com.github.hqxqyly.remex.boot.exception.RemexException;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.boot.utils.DateUtils;

/**
 * dpcf数据转换 - 日期格式化
 * 
 * @author Qiaoxin.Hong
 *
 */
public class DpcfConverterDateFormat implements IDpcfConvert {

	/** dpcf配置项 - 日期格式化 */
	protected DpcfOptionDateFormat dpcfDateFormat;

	/**
	 * 数据转换
	 * @param data 数据
	 * @param propertyData 当前属性值
	 * @return
	 */
	@Override
	public Object convert(Object data, Object propertyData) {
		if (dpcfDateFormat == null || propertyData == null) return null;
		
		//日期格式
		String pattern = dpcfDateFormat.value();
		if (Assist.isBlank(pattern)) return propertyData;
		
		if (!(propertyData instanceof Date)) {
			throw new RemexException("property data not date");
		}
		Date curPropertyData = (Date) propertyData;
		return DateUtils.format(curPropertyData, pattern);
	}
	
	public void setDpcfDateFormat(DpcfOptionDateFormat dpcfDateFormat) {
		this.dpcfDateFormat = dpcfDateFormat;
	}
	
	public DpcfOptionDateFormat getDpcfDateFormat() {
		return dpcfDateFormat;
	}
}
