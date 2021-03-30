package remexfastbuilderpackage.remexfastbuildertemplate.org.service;

import com.github.hqxqyly.remex.fast.common.structure.req.PageReq;
import com.github.hqxqyly.remex.fast.common.structure.req.preinstall.OperationByIdReq;
import com.github.hqxqyly.remex.fast.common.structure.rsp.PageData;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;

import remexfastbuilderpackage.remexfastbuildertemplate.api.entity.demo.DemoEntity;
import remexfastbuilderpackage.remexfastbuildertemplate.api.req.demo.DemoSaveReq;

/**
 * demo org service
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface OrgDemoService {

	/**
	 * 创建数据
	 * @param req
	 * @return
	 */
	Result<String> createData(DemoSaveReq req);
	
	/**
	 * 修改数据
	 * @param req
	 * @return
	 */
	Result<Void> modifyData(DemoSaveReq req);
	
	/**
	 * 根据ID查询记录
	 * @param req
	 * @return
	 */
	Result<DemoEntity> findDataById(OperationByIdReq req);
	
	/**
	 * 删除记录
	 * @param req
	 * @return
	 */
	Result<Void> deleteData(OperationByIdReq req);
	
	/**
	 * 根据条件查询记录列表
	 * @param pageReq
	 * @return
	 */
	Result<PageData<DemoEntity>> queryDataList(PageReq<OperationByIdReq> pageReq);
}
