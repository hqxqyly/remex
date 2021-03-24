package com.github.hqxqyly.remex.boot.utils;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Servlet工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class ServletUtils {
	protected static Logger logger = LoggerFactory.getLogger(ServletUtils.class);

	/**
	 * 取得ServletRequestAttributes
	 * @return
	 */
	public static ServletRequestAttributes getServletRequestAttributes() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes == null) return null;
		if (requestAttributes instanceof ServletRequestAttributes) {
			return (ServletRequestAttributes) requestAttributes;
		}
		return null;
	}
	
	/**
	 * 取得HttpServletRequest
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return Assist.ifNotNullFn(getServletRequestAttributes(), ServletRequestAttributes::getRequest);
	}
	
	/**
	 * 取得HttpServletResponse
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		return Assist.ifNotNullFn(getServletRequestAttributes(), ServletRequestAttributes::getResponse);
	}
	
	/**
	 * 取得header
	 * @param header
	 * @return
	 */
	public static String getHeader(String header) {
		Assist.notBlank(header, "header cannot be blank");
		return Assist.ifNotNullFn(getRequest(), request -> request.getHeader(header));
	}
	
	/**
	 * HttpServletResponse写入数据
	 * @param response
	 * @param val
	 */
	public static void write(ServletResponse response, String val) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(val);
		} catch (Exception e) {
			logger.error("response write error", e);
		}
	}
	
	/**
	 * HttpServletResponse写入数据
	 * @param response
	 * @param val
	 */
	public static void write(String val) {
		write(getResponse(), val);
	}
	
	/**
	 * HttpServletResponse写入数据 - json格式
	 * @param response
	 * @param val
	 */
	public static void writeJson(ServletResponse response, Object obj) {
		write(response, Assist.toJsonDefault(obj));
	}
	
	/**
	 * HttpServletResponse写入数据 - json格式
	 * @param response
	 * @param val
	 */
	public static void writeJson(Object obj) {
		writeJson(getResponse(), obj);
	}
}
