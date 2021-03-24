package com.github.hqxqyly.remex.boot.dpcf.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.hqxqyly.remex.boot.constant.BConst;
import com.github.hqxqyly.remex.boot.dpcf.annotation.DpcfOptionEnumTran;
import com.github.hqxqyly.remex.boot.dpcf.interfaces.IDpcfConvert;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * dpcf数据转换 - 枚举转换
 * 
 * @author Qiaoxin.Hong
 *
 */
public class DpcfConverterEnumTran implements IDpcfConvert {
	
	/** dpcf配置项 - 枚举转换 */
	protected DpcfOptionEnumTran dpcfEnumTran;

	/** 规则map */
	protected Map<String, String> ruleMap;
	
	/** 缺省值 */
	protected String defaultVal = BConst.EMPTY;
	
	/**
	 * 数据转换
	 * @param data 数据
	 * @param propertyData 当前属性值
	 * @return
	 */
	@Override
	public Object convert(Object data, Object propertyData) {
		if (dpcfEnumTran == null || propertyData == null) return null;
		
		//初始化规则
		initRuleMap();
		
		//规则
		if (Assist.isEmpty(ruleMap)) return propertyData;
		
		String propertyDataKey = Assist.defaultString(propertyData).trim();
		
		//有key则直接取值
		if (ruleMap.containsKey(propertyDataKey)) {
			return ruleMap.get(propertyDataKey);
		} else {  //没有key则取默认值
			return Assist.defaultString(defaultVal);
		}
	}
	
	/**
	 * 初始化规则
	 * @return
	 */
	protected Map<String, String> initRuleMap() {
		if (ruleMap == null) {
			synchronized (this) {
				if (ruleMap == null) {
					ruleMap = new HashMap<>();
					//规则
					String rule = dpcfEnumTran.value();
					if (Assist.isNotBlank(rule)) {
						//切割规则，key、value为一组，如：1, 男, 2, 女, 缺省值
						List<String> ruleList = Assist.splitToList(rule, ",");
						int size = ruleList.size();
						
						//有缺省值
						if (ruleList.size() % 2 != 0) {
							defaultVal = ruleList.get(ruleList.size() -1);
							size -= 1;
						}
						
						for (int i = 0; i < size; i+=2) {
							String key = ruleList.get(i).trim();
							String value = ruleList.get(i + 1).trim();
							ruleMap.put(key, value);
						}
					}
					
				}
			}
		}
		return ruleMap;
	}

	public void setDpcfEnumTran(DpcfOptionEnumTran dpcfEnumTran) {
		this.dpcfEnumTran = dpcfEnumTran;
	}
	
	public DpcfOptionEnumTran getDpcfEnumTran() {
		return dpcfEnumTran;
	}
}
