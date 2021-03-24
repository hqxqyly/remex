package com.github.hqxqyly.remex.boot.msg;

import java.util.HashMap;
import java.util.Map;

import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * 消息枚举上下文
 * 
 * @author Qiaoxin.Hong
 *
 */
public class MsgEnumContext {

	/** 消息枚举列表 */
	protected static Map<String, IMsgEnum> msgEnumMap = new HashMap<>();
	
	/**
	 * 根据消息编号查询消息枚举
	 * @param code
	 * @return
	 */
	public static IMsgEnum findByCode(String code) {
		return Assist.ifNotBlankFn(code, msgEnumMap::get);
	}
	
	/**
	 * 往消息枚举上下文加入消息枚举类
	 * @param msgEnum
	 */
	public static void addMsgEnum(IMsgEnum...msgEnumsItem) {
		Assist.forEach(msgEnumsItem, msgEnum -> msgEnumMap.put(msgEnum.getCode(), msgEnum));
	}
	
	/**
	 * 往消息枚举上下文加入消息枚举类
	 * @param msgEnum
	 */
	public static void addMsgEnumArr(IMsgEnum[]...msgEnumsItemArr) {
		Assist.forEach(msgEnumsItemArr, MsgEnumContext::addMsgEnum);
	}
}
