package com.github.hqxqyly.remex.fast.server.structure.controller.agile.assist;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.hqxqyly.remex.fast.common.structure.entity.IBaseEntity;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.interfaces.IAgileControllerBase;

/**
 * agile controller插件辅助方法 - 拼接默认的查询条件
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IAgileControllerPackDefaultQueryWrapper<BEAN extends IBaseEntity, REQ> extends IAgileControllerBase<BEAN> {

	/**
	 * 拼接默查询条件
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	default QueryWrapper<BEAN> packQueryWrapper(REQ req) {
		if (req == null) return newQueryWrapper();
		
		BEAN bean = null;
		//请求参数为自定req
		if (getBeanClass().equals(req.getClass())) {
			bean = (BEAN) req;
		} else {
			bean = toBean(req, getBeanClass());
		}
		
		//避免空字符串时，加入条件
		if (isBlank(bean.getId()))
			bean.setId(null);
		
		return newQueryWrapper(bean);
	}
}
