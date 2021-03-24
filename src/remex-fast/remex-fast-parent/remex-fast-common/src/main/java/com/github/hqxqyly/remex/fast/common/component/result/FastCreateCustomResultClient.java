package com.github.hqxqyly.remex.fast.common.component.result;

import com.github.hqxqyly.remex.boot.interfaces.result.ICreateCustomResultClient;
import com.github.hqxqyly.remex.boot.msg.IMsgEnum;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;

/**
 * 创建自定义结果集的规范，remex-boot的一些组件需要使用，如response写入一个错误的结果集
 * 
 * @author Qiaoxin.Hong
 *
 */
public class FastCreateCustomResultClient implements ICreateCustomResultClient {

	/**
	 * 创建结果集
	 * @return
	 */
	@Override
	public Object newResult(Object data, IMsgEnum msgEnum, Object... msgArgs) {
		return new Result<>(data, msgEnum, msgArgs);
	}
}
