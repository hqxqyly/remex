package com.github.hqxqyly.remex.fast.server.structure.controller.agile.injector;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.hqxqyly.remex.boot.validator.annotation.RemexValidate;
import com.github.hqxqyly.remex.fast.common.structure.entity.IBaseEntity;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.interfaces.IAgileControllerBase;

import io.swagger.annotations.ApiOperation;

/**
 * agile controller插件 - 创建记录
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IAgileControllerCreateData<BEAN extends IBaseEntity, REQ> extends IAgileControllerBase<BEAN> {

	@RemexValidate
	@Transactional
	@ApiOperation("创建记录")
	@PostMapping("createData")
	default Result<String> createData(@RequestBody REQ req) {
		//填充公共属性
		BEAN entity = newCreateEntity(getBeanClass(), req);
		//插入
		getDao().insert(entity);
		
		return newResult(entity.getId());
	}
}
