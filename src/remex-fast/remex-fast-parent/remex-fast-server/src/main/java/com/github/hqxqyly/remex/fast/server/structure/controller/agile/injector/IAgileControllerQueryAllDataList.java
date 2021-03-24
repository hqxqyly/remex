package com.github.hqxqyly.remex.fast.server.structure.controller.agile.injector;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;

import com.github.hqxqyly.remex.fast.common.structure.entity.IBaseEntity;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.interfaces.IAgileControllerBase;

import io.swagger.annotations.ApiOperation;

/**
 * agile controller插件 - 查询所有记录列表
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IAgileControllerQueryAllDataList<BEAN extends IBaseEntity> extends IAgileControllerBase<BEAN> {

	@ApiOperation("查询所有记录列表")
	@PostMapping("queryAllDataList")
	default Result<List<BEAN>> queryAllDataList() {
		List<BEAN> list = getDao().selectList(null);
		return newResult(list);
	}
}
