package com.github.hqxqyly.remex.crude.org.controller.preinstall;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.web.bind.annotation.RestController;

import com.github.hqxqyly.remex.crude.org.controller.AreaController;
import com.github.hqxqyly.remex.crude.org.entity.AreaEntity;

/**
 * 地区预设接口
 * 
 * @author Qiaoxin.Hong
 *
 */
@ConditionalOnMissingBean(value = AreaController.class, ignored = AreaPreinstallController.class)
@RestController
public class AreaPreinstallController extends AreaController<AreaEntity> {

}
