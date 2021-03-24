package com.github.hqxqyly.remex.fast.api.structure.entity;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.github.hqxqyly.remex.fast.common.structure.entity.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("agile entity")
public class AgileEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 是否删除，0：否；1：是 */
	@TableLogic
    protected Integer isDelete;
	
	@Override
	@ApiModelProperty("ID")
	public String getId() {
		return id;
	}

	@Override
	@ApiModelProperty(value = "创建时间", hidden = true)
	public Timestamp getCreateTime() {
		return createTime;
	}

	@Override
	@ApiModelProperty(value = "修改时间", hidden = true)
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	@Override
    @ApiModelProperty(value = "操作人ID", hidden = true)
	public String getOpUserId() {
		return opUserId;
	}

	@Override
    @ApiModelProperty(value = "操作人", hidden = true)
	public String getOpUserName() {
		return opUserName;
	}
}
