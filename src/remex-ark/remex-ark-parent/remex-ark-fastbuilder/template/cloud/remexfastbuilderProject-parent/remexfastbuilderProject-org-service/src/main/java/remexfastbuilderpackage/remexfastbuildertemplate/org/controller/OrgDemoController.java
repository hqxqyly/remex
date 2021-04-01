package remexfastbuilderpackage.remexfastbuildertemplate.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.hqxqyly.remex.fast.common.structure.req.PageReq;
import com.github.hqxqyly.remex.fast.common.structure.req.preinstall.OperationByIdReq;
import com.github.hqxqyly.remex.fast.common.structure.rsp.PageData;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import remexfastbuilderpackage.remexfastbuildertemplate.api.entity.demo.DemoEntity;
import remexfastbuilderpackage.remexfastbuildertemplate.api.req.demo.DemoSaveReq;
import remexfastbuilderpackage.remexfastbuildertemplate.org.service.OrgDemoService;
import remexfastbuilderpackage.remexfastbuildertemplate.server.structure.controller.BaseController;

@Api(tags = "demo接口")
@RestController
@RequestMapping("/org/demo/")
public class OrgDemoController implements BaseController {
	
	@Autowired
	OrgDemoService orgDemoService;
	
	@ApiOperation("test")
	@PostMapping("test")
	public Result<String> test() {
		return newResult("Hello World");
	}

	@ApiOperation("创建数据")
	@PostMapping("createData")
	public Result<String> createData(@RequestBody DemoSaveReq req) {
		return orgDemoService.createData(req);
	}
	
	@ApiOperation("修改数据")
	@PostMapping("modifyData")
	public Result<Void> modifyData(@RequestBody DemoSaveReq req) {
		return orgDemoService.modifyData(req);
	}
	
	@ApiOperation("根据ID查询记录")
	@PostMapping("findDataById")
	public Result<DemoEntity> findDataById(@RequestBody OperationByIdReq req) {
		return orgDemoService.findDataById(req);
	}
	
	@ApiOperation("删除记录")
	@PostMapping("deleteData")
	public Result<Void> deleteData(@RequestBody OperationByIdReq req) {
		return orgDemoService.deleteData(req);
	}
	
	@ApiOperation("根据条件查询记录列表")
	@PostMapping("queryDataList")
	public Result<PageData<DemoEntity>> queryDataList(@RequestBody PageReq<OperationByIdReq> pageReq) {
		return orgDemoService.queryDataList(pageReq);
	}
}
