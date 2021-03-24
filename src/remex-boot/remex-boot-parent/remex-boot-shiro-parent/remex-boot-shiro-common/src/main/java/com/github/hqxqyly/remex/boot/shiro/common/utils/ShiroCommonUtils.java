package com.github.hqxqyly.remex.boot.shiro.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.hqxqyly.remex.boot.shiro.common.interfaces.IShiroEncryptClient;
import com.github.hqxqyly.remex.boot.utils.ApplicationContextUtils;

/**
 * shiro通用工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class ShiroCommonUtils {
	protected static Logger logger = LoggerFactory.getLogger(ShiroCommonUtils.class);
	
	/**
	 * 取得IShiroEncryptClient
	 * @return
	 */
	public static IShiroEncryptClient getShiroEncryptClient() {
		return ApplicationContextUtils.getBean(IShiroEncryptClient.class);
	}

	/**
	 * 加密盐值
	 * @param salt 密码盐值
	 * @return
	 */
	public static ByteSource encryptSalt(String salt) {
		return getShiroEncryptClient().encryptSalt(salt);
	}

	/**
	 * 加密
	 * @param password 密码
	 * @param salt 密码盐值
	 * @return
	 */
	public static String encrypt(String password, String salt) {
		return getShiroEncryptClient().encrypt(password, salt);
	}
	
	/**
	 * 取得sessionId
	 * @return
	 */
	public static String getSessionId() {
		String sessionId = getSubject().getSession().getId().toString();
		return sessionId;
	}
	
	/**
	 * 取得Subject
	 * @return
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}
	
	/**
	 * 取得已登录的用户
	 * @param <T>
	 * @return
	 */
	public static <T> T getUser() {
		@SuppressWarnings("unchecked")
		T user = (T) getSubject().getPrincipal();
		return user;
	}
	
	/**
	 * 注销
	 */
	public static void logout() {
		try {
			Subject subject = SecurityUtils.getSubject();
			subject.logout();
		} catch (Exception e) {
			logger.error("logout error", e);
		}
	}
}
