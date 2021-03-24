package com.github.hqxqyly.remex.boot.interfaces.result;

import com.github.hqxqyly.remex.boot.msg.IMsgEnum;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * 创建自定义结果集的规范，remex-boot的一些组件需要使用，如response写入一个错误的结果集
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface ICreateCustomResultClient {

	/**
	 * 创建结果集
	 * @return
	 */
	Object newResult(Object data, IMsgEnum msgEnum, Object...msgArgs);
	
	/**
	 * 创建结果集
	 * @return
	 */
	default Object newResult(IMsgEnum msgEnum, Object...msgArgs) {
		return newResult(null, msgEnum, msgArgs);
	}
	
	/**
	 * 创建json结果集
	 * @return
	 */
	default String newResultJson(Object data, IMsgEnum msgEnum, Object...msgArgs) {
		return Assist.toJson(newResult(data, msgEnum, msgArgs));
	}
	
	/**
	 * 创建json结果集
	 * @return
	 */
	default String newResultJson(IMsgEnum msgEnum, Object...msgArgs) {
		return Assist.toJson(newResult(msgEnum, msgArgs));
	}
}
