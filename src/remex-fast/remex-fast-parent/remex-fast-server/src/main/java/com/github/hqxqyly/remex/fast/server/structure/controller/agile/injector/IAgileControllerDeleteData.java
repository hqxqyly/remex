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
 * agile controller插件 - 删除记录
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IAgileControllerDeleteData<BEAN extends IBaseEntity> extends IAgileControllerBase<BEAN> {

	@RemexValidate
	@Transactional
	@ApiOperation("删除记录")
	@PostMapping("deleteData")
	default Result<Void> deleteData(@RequestBody OperationByIdReq req) {
		//填充公共属性
		BEAN entity = newDeleteEntity(getBeanClass(), req.getId());
		//修改
		getDao().assertUpdateById(entity);
		
		return newResult();
	}
}
