package com.github.hqxqyly.remex.crude.org.entity;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.hqxqyly.remex.fast.api.structure.entity.AgileEntity;
import com.github.hqxqyly.remex.fast.common.structure.bean.dict.IDict;
import com.github.hqxqyly.remex.fast.common.structure.rsp.preinstall.DictDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("角色entity")
@TableName("t_role")
public class RoleEntity extends AgileEntity implements IDict {
	private static final long serialVersionUID = 1L;

	@NotBlank
    @ApiModelProperty("名称")
    protected String name;
    
    @ApiModelProperty("部门ID")
    protected String departmentId;
    
    /**
	 * 转数据字典
	 */
	@Override
	public DictDto toDict() {
		return new DictDto(id, name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
}
