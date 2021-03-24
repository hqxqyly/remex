package com.github.hqxqyly.remex.crude.org.controller.preinstall;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.web.bind.annotation.RestController;

import com.github.hqxqyly.remex.crude.org.controller.CompanyController;
import com.github.hqxqyly.remex.crude.org.entity.CompanyEntity;

/**
 * 公司预设接口
 * 
 * @author Qiaoxin.Hong
 *
 */
@ConditionalOnMissingBean(value = CompanyController.class, ignored = CompanyPreinstallController.class)
@RestController
public class CompanyPreinstallController extends CompanyController<CompanyEntity> {

}
