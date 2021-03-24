package com.github.hqxqyly.remex.boot.filelocal.properties;

/**
 * 本地文件处理properties
 * 
 * @author Qiaoxin.Hong
 *
 */
public class FileLocalProperties {

	/** properties配置文件前缀 */
	public static final String PREFIX = "filelocal";
	
	/** 文件根路径 */
	protected String rootPath;
	
	/** 是否验证越级访问 */
	protected Boolean isValidateFilePassLevelAccess;

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public Boolean getIsValidateFilePassLevelAccess() {
		return isValidateFilePassLevelAccess;
	}

	public void setIsValidateFilePassLevelAccess(Boolean isValidateFilePassLevelAccess) {
		this.isValidateFilePassLevelAccess = isValidateFilePassLevelAccess;
	}
}
