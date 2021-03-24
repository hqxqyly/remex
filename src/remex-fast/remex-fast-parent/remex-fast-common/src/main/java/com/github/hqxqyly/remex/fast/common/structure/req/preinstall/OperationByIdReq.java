package com.github.hqxqyly.remex.fast.common.structure.req.preinstall;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("根据ID进行操作req")
public class OperationByIdReq {

	@NotBlank
	@ApiModelProperty("ID")
	protected String id;
	
	public OperationByIdReq() {
		super();
	}

	public OperationByIdReq(String id) {
		super();
		this.id = id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}
