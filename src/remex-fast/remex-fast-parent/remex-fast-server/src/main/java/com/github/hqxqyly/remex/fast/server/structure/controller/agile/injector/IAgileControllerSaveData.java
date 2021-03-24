package com.github.hqxqyly.remex.fast.server.structure.controller.agile.injector;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.hqxqyly.remex.boot.constant.groups.GroupId;
import com.github.hqxqyly.remex.fast.common.constant.FastPlusSwaggerNotes;
import com.github.hqxqyly.remex.fast.common.structure.entity.IBaseEntity;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.interfaces.IAgileControllerBase;

import io.swagger.annotations.ApiOperation;

/**
 * agile controller插件 - 保存记录，ID有值则修改；无值则新增
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IAgileControllerSaveData<BEAN extends IBaseEntity> extends IAgileControllerBase<BEAN> {

	@Transactional
	@ApiOperation(value = "保存记录，ID有值则修改；ID无值则新增", notes = FastPlusSwaggerNotes.MODIFY_DATA)
	@PostMapping("saveData")
	default Result<String> saveData(@RequestBody BEAN req) {
		//新增
		if (isBlank(req.getId())) {
			//参数验证
			validate(req);
			
			//填充公共属性
			BEAN entity = newCreateEntity(getBeanClass(), req);
			//插入
			getDao().insert(entity);
			
			return newResult(req.getId());
		} else {  //修改
			//参数验证
			validate(req, GroupId.class);
			
			//填充公共属性
			BEAN entity = newUpdateEntity(getBeanClass(), req);
			//修改
			getDao().assertUpdateById(entity);
			
			return newResult(req.getId());
		}
	}
}
