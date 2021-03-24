package com.github.hqxqyly.remex.crude.org.entity;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.hqxqyly.remex.fast.api.structure.entity.AgileEntity;
import com.github.hqxqyly.remex.fast.common.structure.bean.dict.IDict;
import com.github.hqxqyly.remex.fast.common.structure.rsp.preinstall.DictDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("权限entity")
@TableName("t_auth")
public class AuthEntity extends AgileEntity implements IDict {
	private static final long serialVersionUID = 1L;

	@NotBlank
	@ApiModelProperty("名称")
    protected String name;

	@NotBlank
    @ApiModelProperty("权限编号")
    protected String code;

    @ApiModelProperty("URL")
    protected String url;

    @ApiModelProperty("父ID")
    protected String parentId;
    
    @ApiModelProperty("排序，只影响平级")
    protected Integer sort;
    
    @ApiModelProperty("是否启用，0：否；1：是；")
    protected Integer isEnabled;
    
    @ApiModelProperty("是否需要确认码，0：否；1：是；")
    protected Integer isOpw;
    
    @ApiModelProperty("是否需要二次确认码，0：否；1：是；")
    protected Integer isSecondOpw;
    
    /**
	 * 转数据字典
	 */
	@Override
	public DictDto toDict() {
		return new DictDto(code, name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Integer getIsOpw() {
		return isOpw;
	}

	public void setIsOpw(Integer isOpw) {
		this.isOpw = isOpw;
	}

	public Integer getIsSecondOpw() {
		return isSecondOpw;
	}

	public void setIsSecondOpw(Integer isSecondOpw) {
		this.isSecondOpw = isSecondOpw;
	}
}
