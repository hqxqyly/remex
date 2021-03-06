package com.github.hqxqyly.remex.crude.org.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.github.hqxqyly.remex.crude.org.entity.RoleEntity;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.AgileController;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.injector.IAgileControllerQueryDictList;

import io.swagger.annotations.Api;

@Api(tags = "角色接口")
@RequestMapping("/org/role/")
public class RoleController<BEAN extends RoleEntity> extends AgileController<BEAN>
	implements IAgileControllerQueryDictList<BEAN> {

}
