package com.github.hqxqyly.remex.crude.org.controller.preinstall;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.web.bind.annotation.RestController;

import com.github.hqxqyly.remex.crude.org.api.UserSafeApi;
import com.github.hqxqyly.remex.crude.org.controller.UserSafeController;
import com.github.hqxqyly.remex.crude.org.entity.UserSafeEntity;

/**
 * 用户安全预设接口
 * 
 * @author Qiaoxin.Hong
 *
 */
@ConditionalOnMissingBean(value = UserSafeController.class, ignored = UserSafePreinstallController.class)
@RestController
public class UserSafePreinstallController extends UserSafeController<UserSafeEntity> implements UserSafeApi<UserSafeEntity> {

}
