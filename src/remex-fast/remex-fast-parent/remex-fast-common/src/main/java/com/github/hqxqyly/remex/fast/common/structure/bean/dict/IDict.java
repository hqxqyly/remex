package com.github.hqxqyly.remex.fast.common.structure.bean.dict;

import com.github.hqxqyly.remex.fast.common.structure.rsp.preinstall.DictDto;

/**
 * 数据字典规范
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IDict {

	/**
	 * 转数据字典
	 * @return
	 */
	DictDto toDict();
}
