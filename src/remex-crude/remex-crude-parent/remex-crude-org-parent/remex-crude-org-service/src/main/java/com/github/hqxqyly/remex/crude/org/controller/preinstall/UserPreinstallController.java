package com.github.hqxqyly.remex.crude.org.controller.preinstall;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.web.bind.annotation.RestController;

import com.github.hqxqyly.remex.crude.org.controller.UserController;
import com.github.hqxqyly.remex.crude.org.entity.UserEntity;

/**
 * 用户预设接口
 * 
 * @author Qiaoxin.Hong
 *
 */
@ConditionalOnMissingBean(value = UserController.class, ignored = UserPreinstallController.class)
@RestController
public class UserPreinstallController extends UserController<UserEntity> {

}
