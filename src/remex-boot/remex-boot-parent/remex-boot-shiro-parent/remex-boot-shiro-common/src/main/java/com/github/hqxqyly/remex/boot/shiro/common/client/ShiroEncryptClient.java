package com.github.hqxqyly.remex.boot.shiro.common.client;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

import com.github.hqxqyly.remex.boot.encrypt.RemexBCryptUtils;
import com.github.hqxqyly.remex.boot.interfaces.assist.IAssist;
import com.github.hqxqyly.remex.boot.shiro.common.interfaces.IShiroEncryptClient;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * shiro加密处理器
 * 
 * @author Qiaoxin.Hong
 *
 */
public class ShiroEncryptClient implements IShiroEncryptClient, IAssist {
	
	/** 加密算法方式 */
	protected String encryptAlgorithmName = Md5Hash.ALGORITHM_NAME;
	
	/** 加密次数 */
	protected int encryptHashIterations = 1024;
	
	/** 是否开启密码二次动态加密 */
	protected boolean isEncryptBcrypt = true;
	
	/**
	 * 加密
	 * @param password 密码
	 * @param salt 密码盐值
	 * @return
	 */
	@Override
	public String encrypt(String password, String salt) {
		notBlank(password, "password cannot be blank");
		salt = Assist.defaultString(salt);
		
		//shiro方式密码加密
		String encryptPassword = new SimpleHash(encryptAlgorithmName, password, salt, encryptHashIterations).toHex();
		//二次动态密码加密
		if (isEncryptBcrypt)
			encryptPassword = encryptBcryptPassword(encryptPassword);
					
		return encryptPassword;
	}
	
	/**
	 * 二次动态密码加密，目前参考spring-security的加密方式
	 * @see com.github.hqxqyly.remex.boot.encrypt.RemexBCryptUtils
	 * @param password
	 */
	public String encryptBcryptPassword(String password) {
		return RemexBCryptUtils.encode(password);
	}
	
	
	
	

	public String getEncryptAlgorithmName() {
		return encryptAlgorithmName;
	}

	public void setEncryptAlgorithmName(String encryptAlgorithmName) {
		this.encryptAlgorithmName = encryptAlgorithmName;
	}

	public int getEncryptHashIterations() {
		return encryptHashIterations;
	}

	public void setEncryptHashIterations(int encryptHashIterations) {
		this.encryptHashIterations = encryptHashIterations;
	}

	public boolean isEncryptBcrypt() {
		return isEncryptBcrypt;
	}

	public void setEncryptBcrypt(boolean isEncryptBcrypt) {
		this.isEncryptBcrypt = isEncryptBcrypt;
	}
}
