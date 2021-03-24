package com.github.hqxqyly.remex.boot.shiro.component.access;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

/**
 * shiro session manager
 * 
 * @author Qiaoxin.Hong
 *
 */
public class ShiroSessionManager extends DefaultWebSessionManager {
	
	/** session id在servletRequest header中的字段名 */
	protected String sessionIdName = "token";

	@Override
	protected Serializable getSessionId(ServletRequest servletRequest, ServletResponse servletResponse) {
		//重写sessionId的获取方式
		HttpServletRequest request =  (HttpServletRequest) servletRequest;
		String token = request.getHeader(sessionIdName);
		return token;
	}

	public String getSessionIdName() {
		return sessionIdName;
	}

	public void setSessionIdName(String sessionIdName) {
		this.sessionIdName = sessionIdName;
	}
}
