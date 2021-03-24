package com.github.hqxqyly.remex.crude.org.entity;

/**
 * 用户安全entity规范，如密码等
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IUserSafeEntity {

	/**
	 * 取得密码
	 * @return
	 */
	String getPassword();

	/**
	 * 设置密码
	 * @param password
	 */
	void setPassword(String password);

	/**
	 * 取得操作码
	 * @return
	 */
	String getOpw();

	/**
	 * 设置操作码
	 * @param opw
	 */
	void setOpw(String opw);
}
