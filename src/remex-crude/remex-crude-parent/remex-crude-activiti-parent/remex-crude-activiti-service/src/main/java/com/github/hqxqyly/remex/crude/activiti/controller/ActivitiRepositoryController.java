package com.github.hqxqyly.remex.crude.activiti.controller;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.hqxqyly.remex.boot.constant.BConst;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.crude.activiti.bean.definition.ActivitiDefinitionBean;
import com.github.hqxqyly.remex.crude.activiti.bean.group.ActivitiGroupBean;
import com.github.hqxqyly.remex.crude.activiti.bean.user.ActivitiUserBean;
import com.github.hqxqyly.remex.crude.activiti.msg.MsgCrudeActivitiEnum;
import com.github.hqxqyly.remex.crude.activiti.req.definition.ActivitiCandidateByDefIdModifyReq;
import com.github.hqxqyly.remex.crude.activiti.req.definition.ActivitiDefinitionActivateReq;
import com.github.hqxqyly.remex.crude.activiti.req.definition.ActivitiDefinitionAuthQueryReq;
import com.github.hqxqyly.remex.crude.activiti.req.definition.ActivitiDefinitionQueryReq;
import com.github.hqxqyly.remex.crude.activiti.req.definition.ActivitiDefinitionSuspendReq;
import com.github.hqxqyly.remex.crude.activiti.req.definition.FindCandidateListByDefIdReq;
import com.github.hqxqyly.remex.crude.activiti.rsp.definition.ActivitiDefCandidateDto;
import com.github.hqxqyly.remex.crude.activiti.rsp.definition.ActivitiDefinitionDto;
import com.github.hqxqyly.remex.crude.activiti.structure.controller.IActivitiBaseController;
import com.github.hqxqyly.remex.fast.common.structure.req.PageReq;
import com.github.hqxqyly.remex.fast.common.structure.req.preinstall.OperationByIdReq;
import com.github.hqxqyly.remex.fast.common.structure.rsp.PageData;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "工作流资源接口")
@RestController
@RequestMapping("/activiti/repository/")
public class ActivitiRepositoryController implements IActivitiBaseController {

	@Autowired
	RepositoryService repositoryService;

	@Autowired
	IdentityService identityService;

	@ApiOperation("查询工作流定义列表")
	@PostMapping("queryDefinitionList")
	public Result<PageData<ActivitiDefinitionDto>> queryDefinitionList(@RequestBody PageReq<ActivitiDefinitionQueryReq> pageReq) {
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
		ActivitiDefinitionQueryReq req = pageReq.getReq();
		if (req.getIsSuspended() != null) {
			if (BConst.NO.equals(req.getIsSuspended()))
				query.active();
			else
				query.suspended();
		}
		ifNotNull(req.getInstanceKey(), query::processDefinitionKey);
		ifNotNull(req.getCandidateUserId(), query::startableByUser);

		return queryPage(pageReq, query, this::createActivitiDefinitionDto);
	}

	@ApiOperation("根据ID查询工作流定义")
	@PostMapping("queryDefinitionList")
	public Result<ActivitiDefinitionDto> findDefinitionById(@RequestBody OperationByIdReq req) {
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery().processDefinitionId(req.getId());
		ProcessDefinition entity = query.singleResult();
		ActivitiDefinitionDto dto = createActivitiDefinitionDto(entity);
		return newResult(dto);
	}

	@ApiOperation("查询可发起的工作流定义列表")
	@PostMapping("queryAuthDefinitionList")
	public Result<PageData<ActivitiDefinitionBean>> queryAuthDefinitionList(@RequestBody PageReq<ActivitiDefinitionAuthQueryReq> pageReq) {
		ActivitiDefinitionAuthQueryReq req = pageReq.getReq();
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery().active()
				.startableByUser(req.getUserId());
		ifNotNull(req.getInstanceKey(), query::processDefinitionKey);
		return queryPage(pageReq, query, this::createActivitiDefinitionBean);
	}

	@ApiOperation("中止工作流定义")
	@PostMapping("suspend")
	public Result<Void> suspend(@RequestBody ActivitiDefinitionSuspendReq req) {
		repositoryService.suspendProcessDefinitionById(req.getId());
		return newResult();
	}

	@ApiOperation("激活工作流定义")
	@PostMapping("activate")
	public Result<Void> activate(@RequestBody ActivitiDefinitionActivateReq req) {
		repositoryService.activateProcessDefinitionById(req.getId());
		return newResult();
	}

	@ApiOperation("查询某工作流定义的可申请人信息列表")
	@PostMapping("findCandidateListByDefId")
	public Result<ActivitiDefCandidateDto> findCandidateListByDefId(@RequestBody FindCandidateListByDefIdReq req) {
		ActivitiDefCandidateDto dto = new ActivitiDefCandidateDto();

		//可申请的用户组列表
		List<Group> groupList = identityService.createGroupQuery().potentialStarter(req.getId()).list();
		List<ActivitiGroupBean> groupDtoList = toBeanList(groupList, ActivitiGroupBean.class);
		dto.setGroupList(groupDtoList);

		//可申请的用户列表
		List<User> userList = identityService.createUserQuery().potentialStarter(req.getId()).list();
		List<ActivitiUserBean> userDtoList = toBeanList(userList, ActivitiUserBean.class);
		dto.setUserList(userDtoList);

		return newResult(dto);
	}

	@ApiOperation("修改某工作流定义的可申请人信息")
	@PostMapping("modifyCandidateByDefId")
	public Result<Void> modifyCandidateByDefId(@RequestBody ActivitiCandidateByDefIdModifyReq req) {
		//原有可申请的用户列表
		List<User> dbUserList = Assist.defaultList(identityService.createUserQuery().potentialStarter(req.getId()).list());
		//原有可申请的用户ID列表
		List<String> dbUserIdList = Assist.forEachToList(dbUserList, User::getId);
		//原有可申请的用户组列表
		List<Group> dbGroupList = Assist.defaultList(identityService.createGroupQuery().potentialStarter(req.getId()).list());
		//原有可申请的用户组ID列表
		List<String> dbGroupIdList = Assist.forEachToList(dbGroupList, Group::getId);

		//现有可申请的用户ID列表
		List<String> userIdList = Assist.defaultList(req.getUserIdList());
		//现有可申请的用户组ID列表
		List<String> groupIdList = Assist.defaultList(req.getGroupIdList());

		//用户删除
		Assist.forEach(dbUserIdList, dbUserId -> {
			if (!userIdList.contains(dbUserId))
				repositoryService.deleteCandidateStarterUser(req.getId(), dbUserId);
		});

		//用户组删除
		Assist.forEach(dbGroupIdList, dbGroupId -> {
			if (!groupIdList.contains(dbGroupId))
				repositoryService.deleteCandidateStarterGroup(req.getId(), dbGroupId);
		});


		//用户新增
		Assist.forEach(userIdList, userId -> {
			if (!dbUserIdList.contains(userId)) {
				User user = identityService.createUserQuery().userId(userId).singleResult();				
				Assist.notNull(user, MsgCrudeActivitiEnum.ACTIVITI_USER_NOT_EXISTS);
				repositoryService.addCandidateStarterUser(req.getId(), userId);
			}
		});

		//用户组新增
		Assist.forEach(groupIdList, groupId -> {
			if (!dbGroupIdList.contains(groupId)) {
				Group group = identityService.createGroupQuery().groupId(groupId).singleResult();				
				Assist.notNull(group, MsgCrudeActivitiEnum.ACTIVITI_GROUP_NOT_EXISTS);
				repositoryService.addCandidateStarterGroup(req.getId(), groupId);
			}
		});

		return newResult();
	}
	
	
	
	protected ActivitiDefinitionBean createActivitiDefinitionBean(ProcessDefinition entity) {
		ActivitiDefinitionBean bean = toBean(entity, ActivitiDefinitionBean.class);
		fillActivitiDefinitionBean(bean, entity);
		return bean;
	}
	
	protected void fillActivitiDefinitionBean(ActivitiDefinitionBean bean, ProcessDefinition entity) {
		bean.setIsSuspended(entity.isSuspended() ? BConst.YES : BConst.NO);
	}
	
	protected ActivitiDefinitionDto createActivitiDefinitionDto(ProcessDefinition entity) {
		ActivitiDefinitionDto dto = toBean(entity, ActivitiDefinitionDto.class);
		fillActivitiDefinitionBean(dto, entity);
		fillActivitiDefinitionDto(dto);
		return dto;
	}
	
	protected void fillActivitiDefinitionDto(ActivitiDefinitionDto dto) {
		//可申请的用户组列表
		List<Group> groupList = identityService.createGroupQuery().potentialStarter(dto.getId()).list();
		Assist.forEach(groupList, group -> {
			dto.getGroupIdList().add(group.getId());
			dto.getGroupNameList().add(group.getName());
		});
		//可申请的用户列表
		List<User> userList = identityService.createUserQuery().potentialStarter(dto.getId()).list();
		Assist.forEach(userList, user -> {
			dto.getUserIdList().add(user.getId());
			String userName = Assist.join(user.getFirstName(), user.getLastName());
			dto.getUserNameList().add(userName);
		});
	}
}
