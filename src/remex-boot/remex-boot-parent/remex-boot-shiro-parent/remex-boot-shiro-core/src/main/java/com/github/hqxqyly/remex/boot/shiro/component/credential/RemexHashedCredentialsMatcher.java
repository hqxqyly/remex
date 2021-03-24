package com.github.hqxqyly.remex.boot.shiro.component.credential;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import com.github.hqxqyly.remex.boot.encrypt.RemexBCryptUtils;

/**
 * <pre>
 * 密码比对，支持二次动态密码加密比对
 * @see com.github.hqxqyly.remex.encrypt.RemexBCryptPasswordEncoder
 * <pre>
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RemexHashedCredentialsMatcher extends HashedCredentialsMatcher {
	
	/** 是否开启密码二次动态加密 */
	protected boolean isEncryptBcrypt = true;
	
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		//二次动态加密验证
		if (isEncryptBcrypt) {
			Object tokenHashedCredentials = hashProvidedCredentials(token, info);
			Object accountCredentials = info.getCredentials();
			return RemexBCryptUtils.matches(tokenHashedCredentials.toString(), accountCredentials.toString());
		} else {  //原始验证
			return super.doCredentialsMatch(token, info);
		}
	}

	
	
	
	
	public boolean isEncryptBcrypt() {
		return isEncryptBcrypt;
	}

	public void setEncryptBcrypt(boolean isEncryptBcrypt) {
		this.isEncryptBcrypt = isEncryptBcrypt;
	}
}
