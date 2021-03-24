package com.github.hqxqyly.remex.boot.shiro.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.github.hqxqyly.remex.boot.auth.msg.MsgAuthEnum;
import com.github.hqxqyly.remex.boot.exception.RemexServerException;
import com.github.hqxqyly.remex.boot.shiro.common.bean.LoginResult;
import com.github.hqxqyly.remex.boot.shiro.common.utils.ShiroCommonUtils;

/**
 * shiro工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class ShiroUtils extends ShiroCommonUtils {

	/**
	 * 登录
	 * @param username 用户名
	 * @param password 密码
	 */
	public static <T> LoginResult<T> login(String username, String password) {
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			
			LoginResult<T> loginResult = new LoginResult<>();
			loginResult.setUser(getUser());
			loginResult.setSessionId(getSessionId());
			
			return loginResult;
		} catch (UnknownAccountException e) {
			throw new RemexServerException(MsgAuthEnum.LOGIN_ACQUISITION_ACCOUNT_ERROR, e);
		} catch (IncorrectCredentialsException e) {
			throw new RemexServerException(MsgAuthEnum.LOGIN_PASSWORD_ERROR, e);
		} catch (Exception e) {
			throw new RemexServerException(MsgAuthEnum.LOGIN_ERROR, e);
		}
	}
}
