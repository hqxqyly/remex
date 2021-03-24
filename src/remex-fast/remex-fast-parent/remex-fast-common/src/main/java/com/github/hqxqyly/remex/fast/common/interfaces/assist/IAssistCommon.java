package com.github.hqxqyly.remex.fast.common.interfaces.assist;

import java.util.Collection;

import com.github.hqxqyly.remex.boot.interfaces.assist.IAssist;
import com.github.hqxqyly.remex.boot.msg.IMsgEnum;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.boot.validator.msg.MsgValidatorEnum;
import com.github.hqxqyly.remex.boot.validator.utils.ValidatorUtils;

/**
 * 辅助接口 - 通用方法
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IAssistCommon extends IAssist {
	
	
	/**
	 * 参数验证
	 * @param obj
	 */
	default void validate(Object obj, Class<?>...groups) {
		//参数验证
		ValidatorUtils.validate(obj, groups);
	}

	/**
	 * 验证不能为空
	 */
	default void validateNotBlank(String val, String fieldName) {
		if (Assist.isBlank(val))
			throw Assist.newException(MsgValidatorEnum.VALIDATOR_FAILED_REQUIRED, fieldName);
	}
	
	/**
	 * 验证不能为空
	 */
	default void validateNotNull(Object val, String fieldName) {
		if (Assist.isNull(val))
			throw Assist.newException(MsgValidatorEnum.VALIDATOR_FAILED_REQUIRED, fieldName);
	}
	
	/**
	 * 验证不能为空
	 */
	default void validateNotEmpty(Collection<?> val, String fieldName) {
		if (Assist.isEmpty(val))
			throw Assist.newException(MsgValidatorEnum.VALIDATOR_FAILED_REQUIRED, fieldName);
	}
	
	
	
	/**
	 * 断言存在数据，否则抛出异常
	 * @param count 0：不存在；其它：存在
	 * @param msgEnum
	 * @param msgArgs
	 */
	default void assertExist(int count, IMsgEnum msgEnum, Object...msgArgs) {
		if (count == 0)
			throw Assist.newException(msgEnum, msgArgs);
	}
	
	/**
	 * 断言不存在数据，否则抛出异常
	 * @param count 0：不存在；其它：存在
	 * @param msgEnum
	 * @param msgArgs
	 */
	default void assertNotExist(int count, IMsgEnum msgEnum, Object...msgArgs) {
		if (count != 0)
			throw Assist.newException(msgEnum, msgArgs);
	}
}
