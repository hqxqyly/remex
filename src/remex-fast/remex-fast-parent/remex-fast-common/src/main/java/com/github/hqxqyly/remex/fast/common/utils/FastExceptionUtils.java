package com.github.hqxqyly.remex.fast.common.utils;

import com.github.hqxqyly.remex.boot.exception.RemexServerException;
import com.github.hqxqyly.remex.boot.msg.MsgBasicEnum;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;

/**
 * 异常工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class FastExceptionUtils {

	/**
	 * 异常转结果集
	 * @param <T>
	 * @param e
	 * @return
	 */
	public static <T> Result<T> toResult(Throwable e) {
		RemexServerException remexServerException = transferException(e);
		return Result.newResult(remexServerException.getMsgEnum(), remexServerException.getMsgArgs());
	}
	
	/**
	 * 异常传递，如果是RemexServerException则直接抛出，否则新建RemexServerException后再抛出
	 * @param e
	 */
	public static RemexServerException transferException(Throwable e) {
		//RemexServerException
		if (e != null && e instanceof RemexServerException) {
			return (RemexServerException) e;
		} else {  //其它异常
			return Assist.newException(MsgBasicEnum.FAILED, e);
		}
	}
}
