package com.github.hqxqyly.remex.crude.activiti.controller;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.boot.validator.annotation.RemexValidate;
import com.github.hqxqyly.remex.crude.activiti.bean.group.ActivitiGroupBean;
import com.github.hqxqyly.remex.crude.activiti.msg.MsgCrudeActivitiEnum;
import com.github.hqxqyly.remex.crude.activiti.req.group.ActivitiGroupBindUserReq;
import com.github.hqxqyly.remex.crude.activiti.req.group.ActivitiGroupDeleteReq;
import com.github.hqxqyly.remex.crude.activiti.req.group.ActivitiGroupQueryReq;
import com.github.hqxqyly.remex.crude.activiti.req.group.ActivitiGroupSaveReq;
import com.github.hqxqyly.remex.crude.activiti.structure.controller.IActivitiBaseController;
import com.github.hqxqyly.remex.fast.common.structure.req.PageReq;
import com.github.hqxqyly.remex.fast.common.structure.req.preinstall.OperationByIdReq;
import com.github.hqxqyly.remex.fast.common.structure.rsp.PageData;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "工作流用户组接口")
@RestController
@RequestMapping("/activiti/group/")
public class ActivitiGroupController implements IActivitiBaseController {

	@Autowired
	IdentityService identityService;

	@Transactional
	@RemexValidate
	@ApiOperation("创建工作流用户组")
	@PostMapping("createGroup")
	public Result<Void> createGroup(@RequestBody ActivitiGroupSaveReq req) {
		if (isBlank(req.getId()))
			req.setId(newId());
		Group group = identityService.newGroup(req.getId());
		fillGroup(group, req);
		identityService.saveGroup(group);
		return newResult();
	}

	@Transactional
	@RemexValidate
	@ApiOperation("修改工作流用户组")
	@PostMapping("modifyGroup")
	public Result<Void> modifyGroup(@RequestBody ActivitiGroupSaveReq req) {
		Group group = findGroupById(req.getId());
		notNull(group, MsgCrudeActivitiEnum.ACTIVITI_GROUP_NOT_EXISTS);

		fillGroup(group, req);
		identityService.saveGroup(group);

		return newResult();
	}

	@Transactional
	@RemexValidate
	@ApiOperation("删除工作流用户组")
	@PostMapping("deleteGroup")
	public Result<Void> deleteGroup(@RequestBody ActivitiGroupDeleteReq req) {
		identityService.deleteGroup(req.getId());
		return newResult();
	}

	@RemexValidate
	@ApiOperation("查询工作流用户组列表")
	@PostMapping("queryGroupList")
	public Result<PageData<ActivitiGroupBean>> queryGroupList(@RequestBody PageReq<ActivitiGroupQueryReq> pageReq) {
		ActivitiGroupQueryReq req = pageReq.getReq();
		GroupQuery query = identityService.createGroupQuery();
		ifNotBlank(req.getId(), query::groupId);
		ifNotBlank(req.getName(), query::groupNameLike);
		return queryPage(pageReq, query, ActivitiGroupBean.class);
	}

	@RemexValidate
	@ApiOperation("根据ID查询工作流用户组")
	@PostMapping("findGroupById")
	public Result<ActivitiGroupBean> findGroupById(@RequestBody OperationByIdReq req) {
		Group group = findGroupById(req.getId());
		ActivitiGroupBean dto = toBean(group, ActivitiGroupBean.class);
		return newResult(dto);
	}

	@Transactional
	@RemexValidate
	@ApiOperation("绑定工作流用户组的用户")
	@PostMapping("bindUser")
	public Result<Void> bindUser(@RequestBody ActivitiGroupBindUserReq req) {
		//新增的用户
		Assist.forEach(req.getUserIdList(), userId -> {
			identityService.createMembership(userId, req.getGroupId());
		});

		//移除的用户
		Assist.forEach(req.getRemoveUserIdList(), userId -> {
			identityService.deleteMembership(userId, req.getGroupId());
		});

		return newResult();
	}





	/**
	 * 根据ID查询工作流用户组
	 * @param groupId
	 * @return
	 */
	protected Group findGroupById(String groupId) {
		return identityService.createGroupQuery().groupId(groupId).singleResult();
	}






	protected void fillGroup(Group group, ActivitiGroupSaveReq req) {
		Assist.ifNotNull(req.getName(), group::setName);
		Assist.ifNotNull(req.getType(), group::setType);
	}
}
