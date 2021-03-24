package com.github.hqxqyly.remex.crude.activiti.req.task;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("工作流任务附件创建req")
public class ActivitiTaskAttachmentCreateReq {

	@NotBlank
	@ApiModelProperty("名称")
	protected String name;
	
	@ApiModelProperty("描述")
	protected String description;
	
	@ApiModelProperty("类型")
	protected String type;
	
	@NotBlank
	@ApiModelProperty("url")
	protected String url;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
