package com.github.hqxqyly.remex.crude.activiti.bean.definition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("工作流定义bean")
public class ActivitiDefinitionBean {

	@ApiModelProperty("ID")
	protected String id;
	
	@ApiModelProperty("名称")
	protected String name;
	
	@ApiModelProperty("流程key")
	protected String key;
	
	@ApiModelProperty("说明")
	protected String description;
	
	@ApiModelProperty("是否中止，0：否；1：是；")
	protected Integer isSuspended;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setIsSuspended(Integer isSuspended) {
		this.isSuspended = isSuspended;
	}
	
	public Integer getIsSuspended() {
		return isSuspended;
	}
}
