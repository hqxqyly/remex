package com.github.hqxqyly.remex.crude.org.gateway.controller;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.hqxqyly.remex.boot.shiro.common.bean.LoginResult;
import com.github.hqxqyly.remex.boot.shiro.utils.ShiroUtils;
import com.github.hqxqyly.remex.crude.org.req.LoginReq;
import com.github.hqxqyly.remex.crude.org.rsp.LoginDto;
import com.github.hqxqyly.remex.crude.org.rsp.PrincipalDto;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;
import com.github.hqxqyly.remex.fast.framework.structure.controller.IBaseBasicController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "系统接口")
@RequestMapping("/")
public class SystemController<DTO extends LoginDto, P extends PrincipalDto> implements IBaseBasicController {
	
	/** dto class */
	protected Class<DTO> dtoClass;
	
	@SuppressWarnings("unchecked")
	public SystemController() {
		//生成各泛型的class
		Type genType = getClass().getGenericSuperclass();   
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments(); 
		dtoClass = (Class<DTO>) params[0];
	}

	@ApiOperation("登录")
	@PostMapping("login")
	public Result<DTO> login(@RequestBody LoginReq req) {
		//参数验证
		validate(req);
		
		//登录
		LoginResult<P> loginResult = ShiroUtils.login(req.getUsername(), req.getPassword());
		
		//封装登录结果信息
		DTO dto = toBean(loginResult.getUser(), dtoClass);
		dto.setToken(loginResult.getSessionId());
		
		
		
		return newResult(dto);
	}
	
	@ApiOperation("登出")
	@PostMapping("logout")
	public Result<Void> logout() {
		return newResult();
	}
}
