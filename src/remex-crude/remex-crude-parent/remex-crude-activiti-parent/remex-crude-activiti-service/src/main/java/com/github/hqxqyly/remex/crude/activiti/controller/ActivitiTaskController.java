package com.github.hqxqyly.remex.crude.activiti.controller;

import java.util.List;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.hqxqyly.remex.boot.exception.RemexServerException;
import com.github.hqxqyly.remex.boot.msg.MsgBasicEnum;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.crude.activiti.msg.MsgCrudeActivitiEnum;
import com.github.hqxqyly.remex.crude.activiti.req.task.ActivitiTaskClaimReq;
import com.github.hqxqyly.remex.crude.activiti.req.task.ActivitiTaskCompleteReq;
import com.github.hqxqyly.remex.crude.activiti.req.task.ActivitiTaskDelegateReq;
import com.github.hqxqyly.remex.crude.activiti.req.task.ActivitiTaskHandleQueryReq;
import com.github.hqxqyly.remex.crude.activiti.req.task.ActivitiTaskQueryReq;
import com.github.hqxqyly.remex.crude.activiti.req.task.ActivitiTaskRevocationReq;
import com.github.hqxqyly.remex.crude.activiti.req.task.ActivitiTaskStartReq;
import com.github.hqxqyly.remex.crude.activiti.req.task.ActivitiTaskUnclaimReq;
import com.github.hqxqyly.remex.crude.activiti.rsp.task.ActivitiTaskDto;
import com.github.hqxqyly.remex.crude.activiti.structure.controller.IActivitiBaseController;
import com.github.hqxqyly.remex.crude.activiti.utils.ActivitiUtils;
import com.github.hqxqyly.remex.fast.common.structure.req.PageReq;
import com.github.hqxqyly.remex.fast.common.structure.rsp.PageData;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "?????????????????????")
@RestController
@RequestMapping("/activiti/task/")
public class ActivitiTaskController implements IActivitiBaseController {

	@Autowired
	RuntimeService runtimeService;

	@Autowired
	TaskService taskService;

	@Autowired
	IdentityService identityService;

	@Autowired
	RepositoryService repositoryService;

	@Autowired
	HistoryService historyService;

	@ApiOperation("?????????????????????")
	@PostMapping("startTask")
	public Result<Void> startTask(@RequestBody ActivitiTaskStartReq req) {
		//??????????????????
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(req.getProcessDefinitionId())
				.startableByUser(req.getUserId()).singleResult();
		Assist.notNull(processDefinition, MsgCrudeActivitiEnum.ACTIVITI_PROCESS_DEFINITION_NOT_EXISTS_UNAUTH);
		//?????????????????????
		Assist.mustFalse(processDefinition.isSuspended(), MsgCrudeActivitiEnum.ACTIVITI_PROCESS_DEFINITION_SUSPENDED);

		identityService.setAuthenticatedUserId(req.getUserId());

		//????????????
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(req.getProcessDefinitionId());
		//????????????ID
		String processInstanceId = processInstance.getId();
		//??????????????????
		Task task = ActivitiUtils.findTaskByProcessInstanceId(processInstanceId);

		//????????????????????????????????????
		ActivitiTaskCompleteReq callReq = createEntity(req, task);
		complete(callReq).ok();

		return newResult();
	}

	@ApiOperation("???????????????????????????")
	@PostMapping("queryTaskList")
	public Result<PageData<ActivitiTaskDto>> queryTaskList(@RequestBody PageReq<ActivitiTaskQueryReq> pageReq) {
		TaskQuery query = taskService.createTaskQuery();
		ActivitiTaskQueryReq req = pageReq.getReq();
		Assist.ifNotNull(req.getUserId(), query::taskCandidateOrAssigned);
		Assist.ifNotBlank(req.getCandidateUserId(), query::taskCandidateUser);
		Assist.ifNotBlank(req.getAssigneeUserId(), query::taskAssignee);

		return queryPage(pageReq, query, ActivitiTaskDto.class);
	}

	@ApiOperation("???????????????????????????????????????")
	@PostMapping("queryTaskHandleList")
	public Result<PageData<ActivitiTaskDto>> queryTaskHandleList(@RequestBody PageReq<ActivitiTaskHandleQueryReq> pageReq) {
		TaskQuery query = taskService.createTaskQuery().taskCandidateOrAssigned(pageReq.getReq().getUserId());
		query.taskNameLike("");
		return queryPage(pageReq, query, ActivitiTaskDto.class);
	}

	@ApiOperation("?????????????????????")
	@PostMapping("claim")
	public Result<Void> claim(@RequestBody ActivitiTaskClaimReq req) {
		Task task = ActivitiUtils.findTaskById(req.getTaskId());

		//???????????????
		String assignee = task.getAssignee();
		//???????????????
		Assist.mustBlank(assignee, MsgCrudeActivitiEnum.ACTIVITI_TASK_ALREADY_CLAIMED);

		//??????????????????????????????????????????????????????
		//???????????????????????????
		List<IdentityLink> identityLinkList = taskService.getIdentityLinksForTask(req.getTaskId());
		Assist.notEmpty(identityLinkList, MsgCrudeActivitiEnum.ACTIVITI_TASK_CANDIDATE_EMPTY);

		//?????????????????????
		boolean isAuth = false;

		for (IdentityLink identityLink : identityLinkList) {
			if (isAuth) {
				break;
			}

			//??????????????????
			if (!IdentityLinkType.CANDIDATE.equals(identityLink.getType())) {
				continue;
			}

			//??????
			if (Assist.isNotBlank(identityLink.getUserId())) {
				isAuth = req.getUserId().equals(identityLink.getUserId());
			} else if (Assist.isNotBlank(identityLink.getGroupId())) {  //?????????
				List<User> userList = identityService.createUserQuery().memberOfGroup(identityLink.getGroupId()).list();
				isAuth = Assist.anyMatch(userList, user -> req.getUserId().equals(user.getId()));
			}
		}

		//???????????????
		Assist.mustTrue(isAuth, MsgCrudeActivitiEnum.ACTIVITI_TASK_CANDIDATE_NOT_MATCH);

		try {
			//????????????
			taskService.claim(task.getId(), req.getUserId());
		} catch (ActivitiTaskAlreadyClaimedException e) {
			throw new RemexServerException(MsgCrudeActivitiEnum.ACTIVITI_TASK_ALREADY_CLAIMED);
		}

		return newResult();
	}

	@ApiOperation("?????????????????????")
	@PostMapping("complete")
	public Result<Void> complete(@RequestBody ActivitiTaskCompleteReq req) {
		Task task = ActivitiUtils.findTaskById(req.getTaskId());

		//???????????????
		String assignee = task.getAssignee();
		if (Assist.isBlank(assignee)) {
			//????????????????????????????????????
			ActivitiTaskClaimReq callReq = new ActivitiTaskClaimReq(req.getUserId(), req.getTaskId());
			claim(callReq).ok();
		} else {
			//??????????????????????????????
			Assist.mustEquals(assignee, req.getUserId(), MsgCrudeActivitiEnum.ACTIVITI_TASK_ASSIGNEE_NOT_MATCH);
		}

		Assist.ifNotBlank(req.getFlowId(), flowId -> req.getVariableMap().put("flowId", flowId));
		Assist.ifNotBlank(req.getFlowName(), flowName -> req.getVariableMap().put("flowName", flowName));

		//??????
		Assist.forEach(req.getAttachmentList(), attachmentReq -> {
			taskService.createAttachment(attachmentReq.getType(), task.getId(), task.getProcessInstanceId()
					, attachmentReq.getName(), attachmentReq.getDescription(), attachmentReq.getUrl());
		});

		//????????????
		taskService.complete(task.getId(), req.getVariableMap());

		return newResult();
	}

	@ApiOperation("???????????????????????????")
	@PostMapping("unclaim")
	public Result<Void> unclaim(@RequestBody ActivitiTaskUnclaimReq req) {
		Task task = ActivitiUtils.findTaskById(req.getTaskId());

		//???????????????
		String assignee = task.getAssignee();
		//????????????
		Assist.notBlank(assignee, MsgCrudeActivitiEnum.ACTIVITI_TASK_UN_CLAIMED);
		//??????????????????????????????
		Assist.mustEquals(assignee, req.getUserId(), MsgCrudeActivitiEnum.ACTIVITI_TASK_ASSIGNEE_NOT_MATCH);

		//??????????????????
		taskService.unclaim(req.getTaskId());

		return newResult();
	}

	@ApiOperation("?????????????????????")
	@PostMapping("delegate")
	public Result<Void> delegate(@RequestBody ActivitiTaskDelegateReq req) {
		//??????????????????
		Assist.notEquals(req.getOldUserId(), req.getNewUserId(), MsgCrudeActivitiEnum.ACTIVITI_TASK_DELEGATE_USER_NOT_SAME);

		Task task = ActivitiUtils.findTaskById(req.getTaskId());

		//???????????????
		String assignee = task.getAssignee();
		//????????????
		Assist.notBlank(assignee, MsgCrudeActivitiEnum.ACTIVITI_TASK_UN_CLAIMED);
		//??????????????????????????????
		Assist.mustEquals(assignee, req.getOldUserId(), MsgCrudeActivitiEnum.ACTIVITI_TASK_ASSIGNEE_NOT_MATCH);

		//???????????????????????????
		ActivitiUtils.findUserById(req.getNewUserId());

		//????????????
		taskService.delegateTask(req.getTaskId(), req.getNewUserId());

		return newResult();
	}

	@ApiOperation("?????????????????????")
	@PostMapping("revocation")
	public Result<Void> revocation(@RequestBody ActivitiTaskRevocationReq req) {
		throw new RemexServerException(MsgBasicEnum.FUNCTION_CANNOT_OPEN);
	}
	
	
	
	protected ActivitiTaskCompleteReq createEntity(ActivitiTaskStartReq mainReq, Task task) {
		ActivitiTaskCompleteReq req = new ActivitiTaskCompleteReq();
		req.setUserId(mainReq.getUserId());
		req.setTaskId(task.getId());
		req.setAttachmentList(mainReq.getAttachmentList());
		return req;
	}
}
