package com.github.hqxqyly.remex.fast.server.structure.controller.agile.injector;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.hqxqyly.remex.boot.validator.annotation.RemexValidate;
import com.github.hqxqyly.remex.fast.common.structure.entity.IBaseEntity;
import com.github.hqxqyly.remex.fast.common.structure.req.preinstall.OperationByIdReq;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.interfaces.IAgileControllerBase;

import io.swagger.annotations.ApiOperation;

/**
 * agile controller插件 - 根据ID查询记录
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IAgileControllerFindDataById<BEAN extends IBaseEntity> extends IAgileControllerBase<BEAN> {

	@RemexValidate
	@Transactional
	@ApiOperation("根据ID查询记录")
	@PostMapping("findDataById")
	default Result<BEAN> findDataById(@RequestBody OperationByIdReq req) {
		//根据ID查询记录
		BEAN bean = getDao().selectById(req.getId());
		
		return newResult(bean);
	}
}
