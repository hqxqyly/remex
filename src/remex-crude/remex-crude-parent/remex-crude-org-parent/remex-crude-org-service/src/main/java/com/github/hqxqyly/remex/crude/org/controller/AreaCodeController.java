package com.github.hqxqyly.remex.crude.org.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.github.hqxqyly.remex.crude.org.entity.AreaCodeEntity;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.AgileController;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.injector.IAgileControllerQueryDictList;

import io.swagger.annotations.Api;

@Api(tags = "区号接口")
@RequestMapping("/org/areaCode/")
public class AreaCodeController<BEAN extends AreaCodeEntity> extends AgileController<BEAN>
	implements IAgileControllerQueryDictList<BEAN> {

}
