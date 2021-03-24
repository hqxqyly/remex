package com.github.hqxqyly.remex.crude.org.controller.preinstall;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.web.bind.annotation.RestController;

import com.github.hqxqyly.remex.crude.org.controller.RoleController;
import com.github.hqxqyly.remex.crude.org.entity.RoleEntity;

/**
 * 角色预设接口
 * 
 * @author Qiaoxin.Hong
 *
 */
@ConditionalOnMissingBean(value = RoleController.class, ignored = RolePreinstallController.class)
@RestController
public class RolePreinstallController extends RoleController<RoleEntity> {

}
