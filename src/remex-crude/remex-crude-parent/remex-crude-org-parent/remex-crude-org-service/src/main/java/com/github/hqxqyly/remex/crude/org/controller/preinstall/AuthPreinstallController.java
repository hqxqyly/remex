package com.github.hqxqyly.remex.crude.org.controller.preinstall;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.web.bind.annotation.RestController;

import com.github.hqxqyly.remex.crude.org.controller.AuthController;
import com.github.hqxqyly.remex.crude.org.entity.AuthEntity;

/**
 * 权限预设接口
 * 
 * @author Qiaoxin.Hong
 *
 */
@ConditionalOnMissingBean(value = AuthController.class, ignored = AuthPreinstallController.class)
@RestController
public class AuthPreinstallController extends AuthController<AuthEntity> {

}
