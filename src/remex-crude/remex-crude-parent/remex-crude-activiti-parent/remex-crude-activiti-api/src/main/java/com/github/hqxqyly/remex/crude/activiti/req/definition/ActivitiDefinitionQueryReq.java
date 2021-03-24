package com.github.hqxqyly.remex.crude.activiti.req.definition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("查询工作流定义列表req")
public class ActivitiDefinitionQueryReq {

	@ApiModelProperty("是否中止，0：否；1：是；")
	protected Integer isSuspended;
	
	@ApiModelProperty("实例key")
	protected String instanceKey;
	
	@ApiModelProperty("申请人ID")
	protected String candidateUserId;
	
	public void setIsSuspended(Integer isSuspended) {
		this.isSuspended = isSuspended;
	}
	
	public Integer getIsSuspended() {
		return isSuspended;
	}
	
	public void setInstanceKey(String instanceKey) {
		this.instanceKey = instanceKey;
	}
	
	public String getInstanceKey() {
		return instanceKey;
	}
	
	public void setCandidateUserId(String candidateUserId) {
		this.candidateUserId = candidateUserId;
	}
	
	public String getCandidateUserId() {
		return candidateUserId;
	}
}
