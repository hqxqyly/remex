package com.github.hqxqyly.remex.crude.org.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.github.hqxqyly.remex.crude.org.entity.UserEntity;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.AgileBaseController;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.injector.IAgileControllerDeleteData;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.injector.IAgileControllerFindDataById;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.injector.IAgileControllerQueryAllDataList;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.injector.IAgileControllerQueryDataList;

import io.swagger.annotations.Api;

@Api(tags = "用户接口")
@RequestMapping("/org/user/")
public class UserController<BEAN extends UserEntity> extends AgileBaseController<BEAN>
	implements IAgileControllerDeleteData<BEAN>,
		IAgileControllerFindDataById<BEAN>,
		IAgileControllerQueryAllDataList<BEAN>,
		IAgileControllerQueryDataList<BEAN, BEAN> {

}
