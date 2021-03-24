package com.github.hqxqyly.remex.crude.org.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.hqxqyly.remex.boot.constant.BConst;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.crude.org.dao.RoleAuthDao;
import com.github.hqxqyly.remex.crude.org.daofn.AuthBaseMapper;
import com.github.hqxqyly.remex.crude.org.entity.AuthEntity;
import com.github.hqxqyly.remex.crude.org.entity.RoleAuthEntity;
import com.github.hqxqyly.remex.crude.org.req.AuthInfoByRoleIdQueryReq;
import com.github.hqxqyly.remex.crude.org.req.RoleAuthModifyReq;
import com.github.hqxqyly.remex.crude.org.rsp.AuthTreeByRepairRoleDto;
import com.github.hqxqyly.remex.fast.common.constant.FastPlusSwaggerNotes;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;
import com.github.hqxqyly.remex.fast.common.structure.rsp.preinstall.TreeDto;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.AgileBaseController;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.injector.IAgileControllerQueryDictList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "权限接口")
@RequestMapping("/org/auth/")
public class AuthController<BEAN extends AuthEntity> extends AgileBaseController<BEAN>
	implements IAgileControllerQueryDictList<BEAN> {
	
	@Autowired
	RoleAuthDao roleAuthDao;

	@Transactional
	@ApiOperation(value = "创建记录", notes = FastPlusSwaggerNotes.CREATE_DATA)
	@PostMapping("createData")
	public Result<String> createData(BEAN req) {
		//参数验证
		validate(req);
		//断言权限编号不能存在
		getDao().assertExistByKey("code", req.getCode());
		defaultValGet(req::getIsEnabled, BConst.YES, req::setIsEnabled);

		//填充公共属性
		BEAN entity = newCreateEntity(getBeanClass(), req);
		//插入
		getDao().insert(entity);

		return newResult(entity.getId());
	}
	
	@Transactional
	@ApiOperation(value = "修改记录", notes = FastPlusSwaggerNotes.MODIFY_DATA)
	@PostMapping("modifyData")
	public Result<Void> modifyData(BEAN req) {
		//参数验证
		validateBeanId(req);

		//断言权限必须存在
		BEAN dbEntity = getDao().assertExistById(req.getId());
		//断言权限编号不能存在
		if (isNotBlank(req.getCode()) && !req.getCode().equals(dbEntity.getCode()))
			getDao().assertExistByKey("code", req.getCode());

		//填充公共属性
		BEAN entity = newUpdateEntity(getBeanClass(), req);
		//修改
		getDao().assertUpdateById(entity);

		return newResult();
	}
	
	@ApiOperation("查询权限树列表")
	@PostMapping("queryAuthTreeList")
	public Result<List<TreeDto<BEAN>>> queryAuthTreeList() {
		List<BEAN> list = getDao().selectListAllAsc("sort");
		List<TreeDto<BEAN>> dtoList = Assist.forEachToList(list, TreeDto::new);
		List<TreeDto<BEAN>> treeList = Assist.toTree(dtoList, dto -> dto.getCur().getId(), dto -> dto.getCur().getParentId()
				, TreeDto::addChild);
		return newResult(treeList);
	}
	
	@ApiOperation("查询某角色有权限的权限url列表")
	@PostMapping("queryAuthUrlListByRole")
	public Result<List<String>> queryAuthUrlListByRole(@RequestBody AuthInfoByRoleIdQueryReq req) {
		List<String> list = getDaoFn().queryAuthUrlListByRole(req.getRoleId());
		return newResult(list);
	}
	
	@ApiOperation("查询某角色有权限的权限编号列表")
	@PostMapping("queryAuthCodeListByRole")
	public Result<List<String>> queryAuthCodeListByRole(@RequestBody AuthInfoByRoleIdQueryReq req) {
		List<String> list = getDaoFn().queryAuthCodeListByRole(req.getRoleId());
		return newResult(list);
	}
	
	@ApiOperation("查询某角色维护的权限树列表")
	@PostMapping("queryAuthTreeListByRole")
	public Result<List<AuthTreeByRepairRoleDto<BEAN>>> queryAuthTreeListByRepairRole(@RequestBody AuthInfoByRoleIdQueryReq req) {
		//角色有权限的权限ID列表
		Set<String> authIdList = new HashSet<>();
		//角色有二次操作码权限的权限ID列表
		Set<String> secondOpwAuthIdList = new HashSet<>();
		
		//角色的权限设定列表
		List<RoleAuthEntity> roleAuthList = roleAuthDao.selectListByKey("role_id", req.getRoleId());
		Assist.forEach(roleAuthList, roleAuth -> {
			if (isBlank(roleAuth.getAuthId())) return;
			authIdList.add(roleAuth.getAuthId());
			if (BConst.YES.equals(roleAuth.getIsAuthSecondOpw()))
				secondOpwAuthIdList.add(roleAuth.getAuthId());
		});
		
		List<BEAN> list = getDao().selectListAllAsc("sort");
		List<AuthTreeByRepairRoleDto<BEAN>> dtoList = Assist.forEachToList(list, entity -> {
			Integer isAuth = authIdList.contains(entity.getId()) ? BConst.YES : BConst.NO;
			Integer isAuthSecondOpw = secondOpwAuthIdList.contains(entity.getId()) ? BConst.YES : BConst.NO;
			return new AuthTreeByRepairRoleDto<>(entity, isAuth, isAuthSecondOpw);
		});
		
		List<AuthTreeByRepairRoleDto<BEAN>> treeList = Assist.toTree(dtoList, dto -> dto.getCur().getId()
				, dto -> dto.getCur().getParentId(), AuthTreeByRepairRoleDto::addChild);
		return newResult(treeList);
	}
	
	@Transactional
	@ApiOperation("修改角色权限")
	@PostMapping("modifyRoleAuth")
	public Result<Void> modifyRoleAuth(@RequestBody RoleAuthModifyReq req) {
		//删除角色的所有权限
		roleAuthDao.deleteByKey("role_id", req.getRoleId());
		
		Assist.forEach(req.getAuthIdList(), authId -> {
			RoleAuthEntity entity = new RoleAuthEntity();
			entity.setRoleId(req.getRoleId());
			entity.setAuthId(authId);
			Integer isAuthSecondOpw = req.getSecondOpwAuthIdList().contains(authId) ? BConst.YES : BConst.NO;
			entity.setIsAuthSecondOpw(isAuthSecondOpw);
			roleAuthDao.insert(entity);
		});
		
		return newResult();
	}
	
	
	
	
	
	/**
	 * 取得dao扩展
	 * @return
	 */
	protected AuthBaseMapper<BEAN> getDaoFn() {
		return (AuthBaseMapper<BEAN>) getDao();
	}
}
