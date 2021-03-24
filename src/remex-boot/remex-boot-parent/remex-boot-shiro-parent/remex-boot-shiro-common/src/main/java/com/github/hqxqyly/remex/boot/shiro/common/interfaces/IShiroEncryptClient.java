package com.github.hqxqyly.remex.boot.shiro.common.interfaces;

import org.apache.shiro.util.ByteSource;

/**
 * shiro加密处理器
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IShiroEncryptClient {
	
	/**
	 * 加密盐值
	 * @param salt 密码盐值
	 * @return
	 */
	default ByteSource encryptSalt(String salt) {
		return ByteSource.Util.bytes(salt);
	}

	/**
	 * 加密
	 * @param password 密码
	 * @param salt 密码盐值
	 * @return
	 */
	String encrypt(String password, String salt);
}
