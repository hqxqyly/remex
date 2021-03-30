package remexfastbuilderpackage.remexfastbuildertemplate.org.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.hqxqyly.remex.boot.constant.groups.GroupCreate;
import com.github.hqxqyly.remex.boot.constant.groups.GroupModify;
import com.github.hqxqyly.remex.boot.validator.annotation.RemexValidate;
import com.github.hqxqyly.remex.fast.common.structure.req.PageReq;
import com.github.hqxqyly.remex.fast.common.structure.req.preinstall.OperationByIdReq;
import com.github.hqxqyly.remex.fast.common.structure.rsp.PageData;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;

import remexfastbuilderpackage.remexfastbuildertemplate.api.entity.demo.DemoEntity;
import remexfastbuilderpackage.remexfastbuildertemplate.api.req.demo.DemoSaveReq;
import remexfastbuilderpackage.remexfastbuildertemplate.org.dao.DemoDao;
import remexfastbuilderpackage.remexfastbuildertemplate.org.service.OrgDemoService;
import remexfastbuilderpackage.remexfastbuildertemplate.server.structure.service.BaseService;

/**
 * demo org service
 * 
 * @author Qiaoxin.Hong
 *
 */
@Service
public class OrgDemoServiceImpl implements BaseService, OrgDemoService {
	
	@Autowired
	DemoDao demoDao;

	/**
	 * 创建数据，验证组标识：@RemexValidate.groups与相应字段的@NotNull.groups相呼应时，才进行验证
	 * @param req
	 * @return
	 */
	@Transactional
	@RemexValidate(groups = GroupCreate.class)
	@Override
	public Result<String> createData(DemoSaveReq req) {
		//默认填充entity的ID、createTime、updateTime、isDelete、opUserId、opUserName
		DemoEntity entity = newCreateEntity(DemoEntity.class, req);
		demoDao.insert(entity);
		
		return newResult(entity.getId());
	}

	/**
	 * 修改数据，验证组标识：@RemexValidate.groups与相应字段的@NotNull.groups相呼应时，才进行验证
	 * @param req
	 * @return
	 */
	@Transactional
	@RemexValidate(groups = GroupModify.class)
	@Override
	public Result<Void> modifyData(DemoSaveReq req) {
		//默认填充entity的ID、updateTime、isDelete、opUserId、opUserName
		DemoEntity entity = newUpdateEntity(DemoEntity.class, req);
		demoDao.assertUpdateById(entity);
		return newResult();
	}
	
	/**
	 * 根据ID查询记录
	 * @param req
	 * @return
	 */
	@RemexValidate
	@Override
	public Result<DemoEntity> findDataById(OperationByIdReq req) {
		DemoEntity entity = demoDao.selectById(req.getId());
		return newResult(entity);
	}

	/**
	 * 删除记录
	 * @param req
	 * @return
	 */
	@Transactional
	@RemexValidate
	@Override
	public Result<Void> deleteData(OperationByIdReq req) {
		demoDao.deleteById(req.getId());
		return newResult();
	}

	/**
	 * 根据条件查询记录列表
	 * @param pageReq
	 * @return
	 */
	@RemexValidate
	@Override
	public Result<PageData<DemoEntity>> queryDataList(PageReq<OperationByIdReq> pageReq) {
		QueryWrapper<DemoEntity> queryWrapper = newQueryWrapper("id", pageReq.getReq().getId());
		IPage<DemoEntity> page = demoDao.selectPage(pageReq, queryWrapper);
		return newPageResult(page);
	}
}
