package com.github.hqxqyly.remex.fast.common.structure.rsp;

import java.io.Serializable;

import com.github.hqxqyly.remex.boot.exception.RemexException;
import com.github.hqxqyly.remex.boot.msg.IMsgEnum;
import com.github.hqxqyly.remex.boot.msg.MsgBasicEnum;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.boot.utils.MsgEnumUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("结果集")
public class Result<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("结果code")
	protected String code;
	
	@ApiModelProperty("结果消息")
	protected String msg;
	
	@ApiModelProperty("结果数据")
	protected T data;
	
	/**
	 * 创建默认成功的结果集
	 */
	public Result() {
		fillMsgEnum(MsgBasicEnum.DEFAULT_MSG_ENUM_SUCCESS);
	}
	
	/**
	 * 创建默认成功的结果集
	 */
	public Result(T data) {
		this();
		this.data = data;
	}
	
	/**
	 * 创建结果集
	 */
	public Result(IMsgEnum msgEnum) {
		fillMsgEnum(msgEnum);
	}
	
	/**
	 * 创建结果集
	 */
	public Result(IMsgEnum msgEnum, Object...msgArgs) {
		fillMsgEnum(msgEnum, msgArgs);
	}
	
	/**
	 * 创建结果集
	 */
	public Result(T data, IMsgEnum msgEnum, Object...msgArgs) {
		this(msgEnum, msgArgs);
	}
	
	
	
	
	
	/**
	 * 将此结果转json
	 * @return
	 */
	public String toJson() {
		return Assist.toJson(this);
	}
	
	/**
	 * 以消息枚举进行消息填充
	 * @param msgEnum
	 * @param msgArgs
	 */
	public void fillMsgEnum(IMsgEnum msgEnum, Object...msgArgs) {
		if (msgEnum == null)
			msgEnum = MsgBasicEnum.DEFAULT_MSG_ENUM_FAILED;

		this.code = msgEnum.getCode();
		this.msg = MsgEnumUtils.resolve(msgEnum, msgArgs);
	}
	
	/**
	 * 是否操作成功，即code = SUCCESS
	 * 
	 * @return
	 */
	public boolean succes() {
		return MsgBasicEnum.DEFAULT_MSG_ENUM_SUCCESS.getCode().equals(code);
	}
	
	/**
	 * 是否操作失败，即code != SUCCESS
	 * 
	 * @return
	 */
	public boolean failed() {
		return !succes();
	}
	
	/**
	 * 带业务性质的获取结果数据，如果操作结果失败，则会抛出异常
	 * @return
	 */
	public T ok() {
		if (failed())
			throw new RemexException(code, Assist.defaultString(msg));
		return data;
	}

	
	
	
	
	/**
	 * 创建默认成功的结果集
	 * @param <T>
	 * @param <R>
	 * @return
	 */
	public static <T> Result<T> newResult() {
		return new Result<>();
	}
	
	/**
	 * 创建默认成功的结果集
	 * @param <T>
	 * @param <R>
	 * @return
	 */
	public static <T> Result<T> newResult(T data) {
		return new Result<>(data);
	}
	
	/**
	 * 创建结果集
	 * @param <T>
	 * @param msgEnum
	 * @param msgArgs
	 * @return
	 */
	public static <T> Result<T> newResult(IMsgEnum msgEnum, Object...msgArgs) {
		return new Result<>(msgEnum, msgArgs);
	}
	
	/**
	 * 创建失败结果集
	 * @param <T>
	 * @return
	 */
	public static <T> Result<T> newFailedResult() {
		return new Result<>(MsgBasicEnum.DEFAULT_MSG_ENUM_FAILED);
	}
	
	
	
	
	
	
	public String getCode() {
		return code;
	}

	public Result<T> setCode(String code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public Result<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public T getData() {
		return data;
	}

	public Result<T> setData(T data) {
		this.data = data;
		return this;
	}
}
