package com.github.hqxqyly.remex.fast.server.structure.controller.agile;

import com.github.hqxqyly.remex.fast.common.structure.entity.IBaseEntity;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.injector.IAgileControllerCreateData;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.injector.IAgileControllerDeleteData;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.injector.IAgileControllerFindDataById;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.injector.IAgileControllerModifyData;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.injector.IAgileControllerQueryAllDataList;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.injector.IAgileControllerQueryDataList;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.injector.IAgileControllerSaveData;

/**
 * agile基础controller
 * 
 * @author Qiaoxin.Hong
 *
 * @param <BEAN> 实体
 * @param <QREQ> 查询条件
 */
public class AgileBasicController<BEAN extends IBaseEntity, QREQ> 
	extends AgileBaseController<BEAN>
	implements IAgileControllerCreateData<BEAN, BEAN>,
		IAgileControllerModifyData<BEAN, BEAN>,
		IAgileControllerSaveData<BEAN>,
		IAgileControllerDeleteData<BEAN>,
		IAgileControllerFindDataById<BEAN>,
		IAgileControllerQueryAllDataList<BEAN>,
		IAgileControllerQueryDataList<BEAN, QREQ> {

}
