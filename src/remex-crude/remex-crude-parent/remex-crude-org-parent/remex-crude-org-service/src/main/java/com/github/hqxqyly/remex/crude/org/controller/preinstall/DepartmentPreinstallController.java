package com.github.hqxqyly.remex.crude.org.controller.preinstall;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.web.bind.annotation.RestController;

import com.github.hqxqyly.remex.crude.org.controller.DepartmentController;
import com.github.hqxqyly.remex.crude.org.entity.DepartmentEntity;

/**
 * 部门预设接口
 * 
 * @author Qiaoxin.Hong
 *
 */
@ConditionalOnMissingBean(value = DepartmentController.class, ignored = DepartmentPreinstallController.class)
@RestController
public class DepartmentPreinstallController extends DepartmentController<DepartmentEntity> {

}
