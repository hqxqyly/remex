package com.github.hqxqyly.remex.crude.activiti.controller;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.task.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.hqxqyly.remex.crude.activiti.req.record.ActivitiRecordAttachmentByInstanceQueryReq;
import com.github.hqxqyly.remex.crude.activiti.req.record.ActivitiRecordAttachmentByTaskQueryReq;
import com.github.hqxqyly.remex.crude.activiti.req.record.ActivitiRecordProcessQueryReq;
import com.github.hqxqyly.remex.crude.activiti.req.record.ActivitiRecordTaskQueryReq;
import com.github.hqxqyly.remex.crude.activiti.rsp.record.ActivitiRecordAttachmentDto;
import com.github.hqxqyly.remex.crude.activiti.rsp.record.ActivitiRecordProcessDto;
import com.github.hqxqyly.remex.crude.activiti.rsp.record.ActivitiRecordTaskDto;
import com.github.hqxqyly.remex.crude.activiti.structure.controller.IActivitiBaseController;
import com.github.hqxqyly.remex.fast.common.structure.req.PageReq;
import com.github.hqxqyly.remex.fast.common.structure.rsp.PageData;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "工作流流水接口")
@RestController
@RequestMapping("/activiti/record/")
public class ActivitiRecordController implements IActivitiBaseController {
	
	@Autowired
	HistoryService historyService;
	
	@Autowired
	TaskService taskService;

	@ApiOperation("查询工作流任务流水列表")
	@PostMapping("queryRecordTaskList")
	public Result<PageData<ActivitiRecordTaskDto>> queryRecordTaskList(@RequestBody PageReq<ActivitiRecordTaskQueryReq> pageReq) {
		HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery();
		return queryPage(pageReq, query, ActivitiRecordTaskDto.class);
	}
	
	@ApiOperation("查询工作流流程流水列表")
	@PostMapping("queryRecordProcessList")
	public Result<PageData<ActivitiRecordProcessDto>> queryRecordProcessList(@RequestBody PageReq<ActivitiRecordProcessQueryReq> pageReq) {
		HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
		return queryPage(pageReq, query, ActivitiRecordProcessDto.class);
	}
	
	@ApiOperation("根据任务ID查询工作流附件流水列表")
	@PostMapping("queryRecordAttachmentListByTask")
	public Result<List<ActivitiRecordAttachmentDto>> queryRecordAttachmentListByTask(@RequestBody ActivitiRecordAttachmentByTaskQueryReq req) {
		List<Attachment> list = taskService.getTaskAttachments(req.getTaskId());
		List<ActivitiRecordAttachmentDto> dtoList = toBeanList(list, ActivitiRecordAttachmentDto.class);
		return newResult(dtoList);
	}
	
	@ApiOperation("根据流程实例ID查询工作流附件流水列表")
	@PostMapping("queryRecordAttachmentListByInstance")
	public Result<List<ActivitiRecordAttachmentDto>> queryRecordAttachmentListByInstance(@RequestBody ActivitiRecordAttachmentByInstanceQueryReq req) {
		List<Attachment> list = taskService.getProcessInstanceAttachments(req.getInstanceId());
		List<ActivitiRecordAttachmentDto> dtoList = toBeanList(list, ActivitiRecordAttachmentDto.class);
		return newResult(dtoList);
	}
}
