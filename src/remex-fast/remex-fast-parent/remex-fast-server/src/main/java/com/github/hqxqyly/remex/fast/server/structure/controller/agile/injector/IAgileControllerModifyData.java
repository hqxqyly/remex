package com.github.hqxqyly.remex.fast.server.structure.controller.agile.injector;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.hqxqyly.remex.boot.constant.groups.GroupId;
import com.github.hqxqyly.remex.boot.validator.annotation.RemexValidate;
import com.github.hqxqyly.remex.fast.common.structure.entity.IBaseEntity;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.interfaces.IAgileControllerBase;

import io.swagger.annotations.ApiOperation;

/**
 * <pre>
 * agile controller插件 - 修改记录
 * ID必填，其余字段null则不修改
 * </pre>
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IAgileControllerModifyData<BEAN extends IBaseEntity, REQ> extends IAgileControllerBase<BEAN> {

	@RemexValidate(groups = GroupId.class)
	@Transactional
	@ApiOperation("修改记录")
	@PostMapping("modifyData")
	default Result<Void> modifyData(@RequestBody REQ req) {
		//填充公共属性
		BEAN entity = newUpdateEntity(getBeanClass(), req);
		//修改
		getDao().assertUpdateById(entity);
		
		return newResult();
	}
}
