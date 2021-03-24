package com.github.hqxqyly.remex.fast.common.structure.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;

import com.github.hqxqyly.remex.boot.constant.groups.GroupId;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("基础entity")
public class BaseEntity implements Serializable, IBaseEntity {
	private static final long serialVersionUID = 1L;

	/** ID */
	@NotBlank(groups = GroupId.class)
    protected String id;
	
	/** 创建时间 */
	protected Timestamp createTime;

	/** 修改时间 */
    protected Timestamp updateTime;

	/** 是否删除，0：否；1：是 */
    protected Integer isDelete;

    /** 操作人ID */
    protected String opUserId;

    /** 操作人 */
    protected String opUserName;
    
    @ApiModelProperty("ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ApiModelProperty("创建时间")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@ApiModelProperty("修改时间")
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

    @ApiModelProperty(value = "是否删除，0：否；1：是", hidden = true)
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

    @ApiModelProperty("操作人ID")
	public String getOpUserId() {
		return opUserId;
	}

	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}

    @ApiModelProperty("操作人")
	public String getOpUserName() {
		return opUserName;
	}

	public void setOpUserName(String opUserName) {
		this.opUserName = opUserName;
	}
}
