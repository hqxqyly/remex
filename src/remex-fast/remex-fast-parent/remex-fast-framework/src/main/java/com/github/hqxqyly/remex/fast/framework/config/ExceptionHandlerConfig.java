package com.github.hqxqyly.remex.fast.framework.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.github.hqxqyly.remex.boot.interfaces.assist.IAssist;
import com.github.hqxqyly.remex.boot.utils.ServletUtils;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;
import com.github.hqxqyly.remex.fast.common.utils.FastExceptionUtils;

/**
 * mvc异常统一处理
 * 
 * @author Qiaoxin.Hong
 *
 */
@ControllerAdvice
public class ExceptionHandlerConfig implements IAssist {
	
//	@ExceptionHandler(constrain)
//	public void name() {
//		
//	}

	/**
	 * 异常处理
	 * @param e
	 * @param response
	 */
	@ExceptionHandler(value = Throwable.class)
	public void handle(Throwable e, HttpServletResponse response) {
		getLogger().error("mvn exception", e);
		
		Result<?> result = FastExceptionUtils.toResult(e);
		ServletUtils.writeJson(result);
	}
}
