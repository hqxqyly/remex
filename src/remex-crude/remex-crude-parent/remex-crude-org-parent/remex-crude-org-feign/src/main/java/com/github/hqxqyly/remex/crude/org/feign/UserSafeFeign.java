package com.github.hqxqyly.remex.crude.org.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.github.hqxqyly.remex.crude.org.api.UserSafeApi;
import com.github.hqxqyly.remex.crude.org.entity.UserSafeEntity;

/**
 * 用户安全feign
 * 
 * @author Qiaoxin.Hong
 *
 */
@FeignClient(name = "${remexProjectName:Please configure the properties remexProjectName}${remexOrgProjectName:-org-service}"
	, contextId = "remex-crude-org-${remexProjectName:Please configure the properties remexProjectName}${remexOrgProjectName:-org-service}")
public interface UserSafeFeign extends UserSafeApi<UserSafeEntity> {

}
