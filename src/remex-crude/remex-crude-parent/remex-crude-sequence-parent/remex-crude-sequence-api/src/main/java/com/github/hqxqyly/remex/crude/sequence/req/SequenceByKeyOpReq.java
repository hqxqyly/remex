package com.github.hqxqyly.remex.crude.sequence.req;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("根据序列key操作序列req")
public class SequenceByKeyOpReq implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank
	@ApiModelProperty("序列类型")
    protected String seqType;
	
	public SequenceByKeyOpReq() {
		super();
	}

	public SequenceByKeyOpReq(String seqType) {
		super();
		this.seqType = seqType;
	}

	public void setSeqType(String seqType) {
		this.seqType = seqType;
	}
	
	public String getSeqType() {
		return seqType;
	}
}
