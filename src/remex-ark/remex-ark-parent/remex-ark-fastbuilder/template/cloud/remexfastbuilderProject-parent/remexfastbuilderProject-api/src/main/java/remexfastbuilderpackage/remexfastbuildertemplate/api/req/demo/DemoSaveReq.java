package remexfastbuilderpackage.remexfastbuildertemplate.api.req.demo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.github.hqxqyly.remex.boot.constant.groups.GroupCreate;
import com.github.hqxqyly.remex.boot.constant.groups.GroupModify;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("demo保存req")
public class DemoSaveReq implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * ，验证组标识：@RemexValidate.groups与相应字段的@NotNull.groups相呼应时，才进行验证
	 */
	@NotBlank(groups = GroupModify.class)
	@ApiModelProperty("ID")
	protected String id;

	/**
	 * ，验证组标识：@RemexValidate.groups与相应字段的@NotNull.groups相呼应时，才进行验证
	 */
	@NotBlank(groups = GroupCreate.class)
	@ApiModelProperty("名称")
	protected String name;

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
}
