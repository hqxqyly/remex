package com.github.hqxqyly.remex.crude.org.gateway.controller.preinstall;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.web.bind.annotation.RestController;

import com.github.hqxqyly.remex.crude.org.gateway.controller.SystemController;
import com.github.hqxqyly.remex.crude.org.rsp.LoginDto;
import com.github.hqxqyly.remex.crude.org.rsp.PrincipalDto;

/**
 * 系统预设接口
 * 
 * @author Qiaoxin.Hong
 *
 */
@ConditionalOnMissingBean(value = SystemController.class, ignored = SystemPreinstallController.class)
@RestController
public class SystemPreinstallController extends SystemController<LoginDto, PrincipalDto> {

}
