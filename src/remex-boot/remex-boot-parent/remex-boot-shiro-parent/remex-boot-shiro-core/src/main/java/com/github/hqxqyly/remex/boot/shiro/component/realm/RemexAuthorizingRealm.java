package com.github.hqxqyly.remex.boot.shiro.component.realm;

import java.util.List;

import org.apache.shiro.realm.AuthorizingRealm;

import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * remex AuthorizingRealm
 * 
 * @author Qiaoxin.Hong
 *
 */
public abstract class RemexAuthorizingRealm extends AuthorizingRealm {

	/**
	 * 放行的路径列表
	 * @return
	 */
	public List<String> getAnonFilterChainDefinitionList() {
		return Assist.newList();
	}
}
