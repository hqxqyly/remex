package com.github.hqxqyly.remex.crude.org.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.github.hqxqyly.remex.crude.org.entity.DepartmentEntity;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.AgileController;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.injector.IAgileControllerQueryDictList;

import io.swagger.annotations.Api;

@Api(tags = "部门接口")
@RequestMapping("/org/department/")
public class DepartmentController<BEAN extends DepartmentEntity> extends AgileController<BEAN>
	implements IAgileControllerQueryDictList<BEAN> {

}
