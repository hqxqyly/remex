package remexfastbuilderpackage.remexfastbuildertemplate.api.entity.demo;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.hqxqyly.remex.fast.common.structure.entity.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("demo entity")
@TableName("t_demo")
public class DemoEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@NotBlank
	@ApiModelProperty("名称")
	protected String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
