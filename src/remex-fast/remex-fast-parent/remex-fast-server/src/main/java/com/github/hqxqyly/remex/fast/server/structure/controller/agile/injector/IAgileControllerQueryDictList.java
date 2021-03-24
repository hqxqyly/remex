package com.github.hqxqyly.remex.fast.server.structure.controller.agile.injector;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;

import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.fast.common.structure.bean.dict.IDict;
import com.github.hqxqyly.remex.fast.common.structure.entity.IBaseEntity;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;
import com.github.hqxqyly.remex.fast.common.structure.rsp.preinstall.DictDto;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.interfaces.IAgileControllerBase;

import io.swagger.annotations.ApiOperation;

/**
 * agile controller插件 - 查询所有记录字典列表
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IAgileControllerQueryDictList<BEAN extends IBaseEntity & IDict> extends IAgileControllerBase<BEAN> {

	@ApiOperation("查询所有记录字典列表")
	@PostMapping("queryDictList")
	default Result<List<DictDto>> queryDictList() {
		List<BEAN> list = getDao().selectList(null);
		List<DictDto> dictList = Assist.forEachToList(list, bean -> bean.toDict());
		
		return newResult(dictList);
	}
}
