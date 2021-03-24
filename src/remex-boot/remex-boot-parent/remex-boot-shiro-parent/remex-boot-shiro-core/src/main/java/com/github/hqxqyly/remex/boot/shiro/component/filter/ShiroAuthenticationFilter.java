package com.github.hqxqyly.remex.boot.shiro.component.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import com.github.hqxqyly.remex.boot.auth.msg.MsgAuthEnum;
import com.github.hqxqyly.remex.boot.interfaces.result.ICreateCustomResultClient;
import com.github.hqxqyly.remex.boot.msg.IMsgEnum;
import com.github.hqxqyly.remex.boot.utils.ApplicationContextUtils;
import com.github.hqxqyly.remex.boot.utils.ServletUtils;

/**
 * 自定义AuthenticationFilter
 * 
 * @author Qiaoxin.Hong
 *
 */
public class ShiroAuthenticationFilter extends AccessControlFilter {
	
	/** 是否启用开发模式 */
	protected boolean dev = true;
	
	/** 是否启用权限验证 */
	protected boolean enablePermit = false;

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		Subject subject = getSubject(request, response);

		//是否登录过
		boolean isAuthenticated = subject.isAuthenticated();
		
		//登录过，进行权限验证
        if (isAuthenticated) {
        	//开发模式
        	if (dev) return true;
        	//不开启权限验证
        	if (enablePermit) return true;
        	
        	HttpServletRequest httpServletRequest = (javax.servlet.http.HttpServletRequest) request;
        	//调用地址
        	String callUrl = httpServletRequest.getRequestURI();
        	//权限验证
        	boolean isPermitted = subject.isPermitted(callUrl);
        	//有权限访问
        	if (isPermitted) {
        		return true;
        	} else {  //无权限访问
        		writeResult(MsgAuthEnum.UNPERMIT);
        		return false;
			}
        	
        } else {  //未登录
        	writeResult(MsgAuthEnum.UNAUTH);
        	return false;
		}
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		return false;
	}

	
	
	
	
	/**
	 * 写入结果集
	 * @param msgEnum
	 */
	protected void writeResult(IMsgEnum msgEnum) {
		Object result = ApplicationContextUtils.getBean(ICreateCustomResultClient.class).newResult(msgEnum);
		ServletUtils.writeJson(result);
	}
	
	
	
	
	

	public boolean isDev() {
		return dev;
	}

	public void setDev(boolean dev) {
		this.dev = dev;
	}

	public boolean isEnablePermit() {
		return enablePermit;
	}

	public void setEnablePermit(boolean enablePermit) {
		this.enablePermit = enablePermit;
	}
}
