package com.github.hqxqyly.remex.boot.utils;

import com.github.hqxqyly.remex.boot.msg.IMsgEnum;

/**
 * 消息枚举工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class MsgEnumUtils {

	/**
	 * 消息占位符替换
	 * @param msgEnum
	 * @param msgArgs
	 * @return
	 */
	public static String resolve(IMsgEnum msgEnum, Object...msgArgs) {
		if (msgEnum == null) return "msgEnum is null";
		String msg = Assist.replaceBrace(msgEnum.getMsg(), msgArgs);
		return msg;
	}
	
	/**
	 * 消息占位符替换，偏日志化格式，例：code: SUCCESS; msg: 操作成功
	 * @param msgEnum
	 * @param msgArgs
	 * @return
	 */
	public static String resolveToLog(IMsgEnum msgEnum, Object...msgArgs) {
		if (msgEnum == null) return "msgEnum is null";
		return Assist.replaceBrace("code: {}; msg: {}", msgEnum.getCode(), resolve(msgEnum, msgArgs));
	}
}
