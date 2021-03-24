package com.github.hqxqyly.remex.boot.ftp.properties;

/**
 * ftp properties
 * 
 * @author Qiaoxin.Hong
 *
 */
public class FtpProperties {
	
	/** properties配置文件前缀 */
	public static final String PREFIX = "ftp";

	/** ftp服务器地址 */
	protected String hostName;
    
	/** ftp服务器端口 */
	protected Integer port;
    
    /** ftp服务器用户名 */
	protected String userName;
    
    /** ftp服务器密码 */
	protected String password;
    
    /** ftp每次读取文件流时缓存数组的大小 */
	protected Integer bufferSize;

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(Integer bufferSize) {
		this.bufferSize = bufferSize;
	}
}
