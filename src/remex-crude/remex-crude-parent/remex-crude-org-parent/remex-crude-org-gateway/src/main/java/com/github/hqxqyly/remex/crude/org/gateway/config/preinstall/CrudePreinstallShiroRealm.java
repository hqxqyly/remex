package com.github.hqxqyly.remex.crude.org.gateway.config.preinstall;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import com.github.hqxqyly.remex.crude.org.entity.UserSafeEntity;
import com.github.hqxqyly.remex.crude.org.gateway.config.CrudeShiroRealm;
import com.github.hqxqyly.remex.crude.org.rsp.PrincipalDto;

/**
 * crude预设shiro realm
 * 
 * @author Qiaoxin.Hong
 * 
 */
@ConditionalOnMissingBean(value = CrudeShiroRealm.class, ignored = CrudePreinstallShiroRealm.class)
public class CrudePreinstallShiroRealm extends CrudeShiroRealm<UserSafeEntity, PrincipalDto> {

}
