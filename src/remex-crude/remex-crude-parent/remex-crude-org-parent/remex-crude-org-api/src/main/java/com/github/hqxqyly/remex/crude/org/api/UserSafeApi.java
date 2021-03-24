package com.github.hqxqyly.remex.crude.org.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.hqxqyly.remex.crude.org.entity.IUserSafeEntity;
import com.github.hqxqyly.remex.crude.org.entity.UserEntity;
import com.github.hqxqyly.remex.crude.org.req.UserLoginReq;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;

/**
 * 用户安全接口
 * 
 * @author Qiaoxin.Hong
 *
 * @param <BEAN>
 */
@RequestMapping("/org/userSafe/")
public interface UserSafeApi<BEAN extends UserEntity & IUserSafeEntity> {

	/**
	 * 登录查询用户
	 * @param req
	 * @return
	 */
	@PostMapping("findUserByLogin")
	Result<BEAN> findUserByLogin(UserLoginReq req);
}
