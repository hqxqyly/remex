package com.github.hqxqyly.remex.crude.org.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.hqxqyly.remex.boot.constant.BConst;
import com.github.hqxqyly.remex.crude.org.entity.IUserSafeEntity;
import com.github.hqxqyly.remex.crude.org.entity.UserEntity;
import com.github.hqxqyly.remex.crude.org.msg.MsgCrudeOrgEnum;
import com.github.hqxqyly.remex.crude.org.req.UserLoginReq;
import com.github.hqxqyly.remex.fast.common.constant.FastPlusSwaggerNotes;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.AgileBaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户安全接口")
@RequestMapping("/org/userSafe/")
public class UserSafeController<BEAN extends UserEntity & IUserSafeEntity> extends AgileBaseController<BEAN> {

	/**
	 * 创建记录
	 */
	@Transactional
	@ApiOperation(value = "创建记录", notes = FastPlusSwaggerNotes.CREATE_DATA)
	@PostMapping("createData")
	public Result<String> createData(@RequestBody BEAN req) {
		//参数验证
		validate(req);
				
		//断言用户名不能存在
		getDao().assertNotExistByKey("username", req.getUsername(), MsgCrudeOrgEnum.CRUDE_ORG_USERNAME_ALREADY_EXIST);
		//断言用员工编号不能存在
		if (isNotBlank(req.getUserNo()))
			getDao().assertNotExistByKey("user_no", req.getUserNo(), MsgCrudeOrgEnum.CRUDE_ORG_USER_NO_ALREADY_EXIST);
		
		if (req.getIsEnabled() == null) req.setIsEnabled(BConst.YES);
		//加密密码
		encryptPassword(req);
		
		//填充公共属性
		BEAN entity = newCreateEntity(getBeanClass(), req);
		//插入
		getDao().insert(entity);
		
		return newResult(req.getId());
	}
	
	@Transactional
	@ApiOperation(value = "修改记录", notes = FastPlusSwaggerNotes.MODIFY_DATA)
	@PostMapping("modifyData")
	public Result<Void> modifyData(@RequestBody BEAN req) {
		//参数验证
		validateBeanId(req);
		
		//断言用户必须存在
		BEAN dbEntity = getDao().assertExistById(req.getId());
		//断言用户名不能存在
		if (isNotBlank(req.getUsername()) && !req.getUsername().equals(dbEntity.getUsername()))
			getDao().assertNotExistByKey("username", req.getUsername(), MsgCrudeOrgEnum.CRUDE_ORG_USERNAME_ALREADY_EXIST);
		//断言用员工编号不能存在
		if (isNotBlank(req.getUserNo()) && !req.getUserNo().equals(dbEntity.getUserNo()))
			getDao().assertNotExistByKey("user_no", req.getUserNo(), MsgCrudeOrgEnum.CRUDE_ORG_USER_NO_ALREADY_EXIST);

		//加密密码
		encryptPassword(req);
				
		//填充公共属性
		BEAN entity = newUpdateEntity(getBeanClass(), req);
		//修改
		getDao().assertUpdateById(entity);
		
		return newResult();
	}
	
	@ApiOperation("登录查询用户")
	@PostMapping("findUserByLogin")
	public Result<BEAN> findUserByLogin(@RequestBody UserLoginReq req) {
		BEAN entity = getDao().selectOneByKey("username", req.getUsername(), "is_enabled", BConst.YES);
		return newResult(entity);
	}
	
	
	
	
	/**
	 * 加密密码
	 * @param id
	 * @param password
	 * @return
	 */
	protected void encryptPassword(BEAN req) {
		if (isNotBlank(req.getPassword()))
			req.setPassword(encryptPassword(req.getId(), req.getPassword()));
		if (isNotBlank(req.getOpw()))
			req.setOpw(encryptPassword(req.getId(), req.getOpw()));
	}
	
	/**
	 * 加密密码
	 * @param id
	 * @param password
	 * @return
	 */
	protected String encryptPassword(String id, String password) {
		notBlank(id, "id cannot be blank");
		notBlank(id, "password cannot be blank");
		
		return password;
	}
}
