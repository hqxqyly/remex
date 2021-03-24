package com.github.hqxqyly.remex.boot.encrypt;

/**
 * 动态加密工具类
 * @see com.github.hqxqyly.remex.encrypt.RemexBCryptPasswordEncoder
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RemexBCryptUtils {

	/**
	 * 加密
	 * @param rawPassword
	 * @return
	 */
	public static String encode(CharSequence rawPassword) {
		return new RemexBCryptPasswordEncoder().encode(rawPassword);
	}
	
	/**
	 * 密码比对 
	 * @param rawPassword
	 * @param encodedPassword
	 * @return
	 */
	public static boolean matches(CharSequence rawPassword, String encodedPassword) {
		return new RemexBCryptPasswordEncoder().matches(rawPassword, encodedPassword);
	}
}
