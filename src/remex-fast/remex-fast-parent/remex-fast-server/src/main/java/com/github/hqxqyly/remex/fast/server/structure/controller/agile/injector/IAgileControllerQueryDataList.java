package com.github.hqxqyly.remex.fast.server.structure.controller.agile.injector;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.hqxqyly.remex.fast.common.structure.entity.IBaseEntity;
import com.github.hqxqyly.remex.fast.common.structure.req.PageReq;
import com.github.hqxqyly.remex.fast.common.structure.rsp.PageData;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.assist.IAgileControllerPackDefaultQueryWrapper;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.interfaces.IAgileControllerBase;

import io.swagger.annotations.ApiOperation;

/**
 * agile controller插件 - 根据条件查询记录列表
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IAgileControllerQueryDataList<BEAN extends IBaseEntity, REQ> 
	extends IAgileControllerBase<BEAN>, IAgileControllerPackDefaultQueryWrapper<BEAN, REQ> {

	@ApiOperation("根据条件查询记录列表")
	@PostMapping("queryDataList")
	default Result<PageData<BEAN>> queryDataList(@RequestBody PageReq<REQ> pageReq) {
		REQ req = pageReq.getReq();
		
		//拼接查询条件
		QueryWrapper<BEAN> queryWrapper = packQueryWrapper(req);
		IPage<BEAN> page = getDao().selectPage(pageReq, queryWrapper);
		return newPageResult(page);
	}
}
