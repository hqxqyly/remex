package com.github.hqxqyly.remex.boot.mybatis.logging;

import org.apache.ibatis.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.hqxqyly.remex.boot.logging.holder.LogIsPrintHolder;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * mybatis logback日志打印
 * 
 * @author Qiaoxin.Hong
 *
 */
public class MybatisLogbackLog implements Log {

	private Logger logger;

	public MybatisLogbackLog(String clazz) {
		logger = LoggerFactory.getLogger(clazz);
	}

	@Override
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	@Override
	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	@Override
	public void error(String s, Throwable e) {
		logger.error("[realMethod : {}] {}", logger.getName(), s, e);
	}

	@Override
	public void error(String s) {
		logger.error("[realMethod : {}] {}", logger.getName(), s);
	}

	@Override
	public void debug(String s) {
		if (isLogPrint()) {
			logger.info("[realMethod : {}] {}", logger.getName(), s);
		}
	}

	@Override
	public void trace(String s) {
		if (isLogPrint()) {
			logger.trace("[realMethod : {}] {}", logger.getName(), s);
		}
	}

	@Override
	public void warn(String s) {
		if (isLogPrint()) {
			logger.info("[realMethod : {}] {}", logger.getName(), s);
		}
	}
	
	
	
	/**
	 * 日志是否打印
	 * @return
	 */
	private boolean isLogPrint() {
		return Assist.defaultTrue(LogIsPrintHolder.get());
	}
}
