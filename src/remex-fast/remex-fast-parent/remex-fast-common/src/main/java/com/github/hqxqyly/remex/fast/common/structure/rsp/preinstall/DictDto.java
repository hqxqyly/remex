package com.github.hqxqyly.remex.fast.common.structure.rsp.preinstall;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("数据字典dto")
public class DictDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("字典key")
	protected String key;
	
	@ApiModelProperty("字典value")
	protected String value;
	
	public DictDto() {
		super();
	}
	
	public DictDto(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
