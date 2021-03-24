package com.github.hqxqyly.remex.crude.activiti.controller;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.boot.validator.annotation.RemexValidate;
import com.github.hqxqyly.remex.crude.activiti.bean.user.ActivitiUserBean;
import com.github.hqxqyly.remex.crude.activiti.msg.MsgCrudeActivitiEnum;
import com.github.hqxqyly.remex.crude.activiti.req.group.ActivitiGroupBindUserReq;
import com.github.hqxqyly.remex.crude.activiti.req.user.ActivitiUserCreateReq;
import com.github.hqxqyly.remex.crude.activiti.req.user.ActivitiUserDeleteReq;
import com.github.hqxqyly.remex.crude.activiti.req.user.ActivitiUserQueryReq;
import com.github.hqxqyly.remex.crude.activiti.req.user.ActivitiUserSaveReq;
import com.github.hqxqyly.remex.crude.activiti.rsp.user.ActivitiUserDto;
import com.github.hqxqyly.remex.crude.activiti.structure.controller.IActivitiBaseController;
import com.github.hqxqyly.remex.fast.common.structure.req.PageReq;
import com.github.hqxqyly.remex.fast.common.structure.req.preinstall.OperationByIdReq;
import com.github.hqxqyly.remex.fast.common.structure.rsp.PageData;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "工作流用户接口")
@RestController
@RequestMapping("/activiti/user/")
public class ActivitiUserController implements IActivitiBaseController {

	@Autowired
	IdentityService identityService;
	
	@Autowired
	ActivitiGroupController activitiGroupController;

	@Transactional
	@RemexValidate
	@ApiOperation("创建工作流用户")
	@PostMapping("createUser")
	public Result<Void> createUser(@RequestBody ActivitiUserCreateReq req) {
		User user = identityService.newUser(req.getId());
		fillUser(user, req);
		identityService.saveUser(user);

		//如果配置了用户组，则直接绑定用户组
		if (Assist.isNotBlank(req.getGroupId())) {
			ActivitiGroupBindUserReq activitiGroupBindUserReq = new ActivitiGroupBindUserReq();
			activitiGroupBindUserReq.setGroupId(req.getGroupId());
			activitiGroupBindUserReq.getUserIdList().add(req.getId());
			activitiGroupController.bindUser(activitiGroupBindUserReq).ok();
		}

		return newResult();
	}

	@Transactional
	@RemexValidate
	@ApiOperation("修改工作流用户")
	@PostMapping("modifyUser")
	public Result<Void> modifyUser(@RequestBody ActivitiUserSaveReq req) {
		User user = findUserById(req.getId());
		notNull(user, MsgCrudeActivitiEnum.ACTIVITI_USER_NOT_EXISTS);

		identityService.saveUser(user);

		return newResult();
	}

	@Transactional
	@RemexValidate
	@ApiOperation("删除工作流用户")
	@PostMapping("deleteUser")
	public Result<Void> deleteUser(@RequestBody ActivitiUserDeleteReq req) {
		identityService.deleteUser(req.getId());
		return newResult();
	}
	
	@RemexValidate
	@ApiOperation("查询工作流用户列表")
	@PostMapping("queryUserList")
	public Result<PageData<ActivitiUserBean>> queryUserList(@RequestBody PageReq<ActivitiUserQueryReq> pageReq) {
		ActivitiUserQueryReq req = pageReq.getReq();
		UserQuery query = identityService.createUserQuery();
		ifNotBlank(req.getId(), query::userId);
		ifNotBlank(req.getFirstName(), query::userFirstNameLike);
		ifNotBlank(req.getLastName(), query::userLastNameLike);
		ifNotBlank(req.getGroupId(), query::memberOfGroup);
		return queryPage(pageReq, query, this::createActivitiUserDto);
	}
	
	@RemexValidate
	@ApiOperation("根据ID查询工作流用户")
	@PostMapping("findUserById")
	public Result<ActivitiUserBean> findUserById(@RequestBody OperationByIdReq req) {
		User user = findUserById(req.getId());
		ActivitiUserBean dto = createActivitiUserDto(user);
		return newResult(dto);
	}
	
	
	
	
	/**
	 * 根据ID查询工作流用户组
	 * @param userId
	 * @return
	 */
	protected User findUserById(String userId) {
		return identityService.createUserQuery().userId(userId).singleResult();
	}
	
	
	
	
	protected void fillUser(User user, ActivitiUserSaveReq req) {
		ifNotNull(req.getFirstName(), user::setFirstName);
		ifNotNull(req.getLastName(), user::setLastName);
		ifNotNull(req.getEmail(), user::setEmail);
		ifNotBlank(req.getPassword(), user::setPassword);
	}
	
	protected ActivitiUserDto createActivitiUserDto(User entity) {
		ActivitiUserDto dto = toBean(entity, ActivitiUserDto.class);
		return dto;
	}
}
