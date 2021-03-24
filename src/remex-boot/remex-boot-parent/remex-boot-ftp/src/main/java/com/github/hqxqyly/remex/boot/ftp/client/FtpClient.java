package com.github.hqxqyly.remex.boot.ftp.client;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.github.hqxqyly.remex.boot.constant.BConst;
import com.github.hqxqyly.remex.boot.exception.io.RemexFileNotFoundException;
import com.github.hqxqyly.remex.boot.fileserver.common.client.IFileServerOutClient;
import com.github.hqxqyly.remex.boot.ftp.exception.RemexFtpConnectFailedException;
import com.github.hqxqyly.remex.boot.ftp.exception.RemexFtpFailedException;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.boot.utils.FileUtils;

/**
 * ftp处理器
 * 
 * @author Qiaoxin.Hong
 *
 */
public class FtpClient implements IFileServerOutClient {
	
	/** ftp服务器地址 */
	protected String hostName;
    
	/** ftp服务器端口 */
	protected Integer port;
    
    /** ftp服务器用户名 */
	protected String userName;
    
    /** ftp服务器密码 */
	protected String password;
    
    /** ftp每次读取文件流时缓存数组的大小，默认1024 */
	protected int bufferSize = 1024;

	/** 文件分割符，考虑到跨环境访问问题，如本地windows访问linux ftp */
	protected String fileSeparator = BConst.SLASH_LEFT;
	
	/** 需要替换掉的文件分割符 */
	protected String needReplacefileSeparator = BConst.SLASH_RIGHT;

	/**
	 * 上传
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param inputStream
	 * @return
	 */
	@Override
	public String upload(String directory, String fileName, InputStream inputStream) {
		notBlank(fileName, "fileName cannot be blank");
		notNull(inputStream, "inputStream cannot be blank");
		
		FTPClient client = null;
		try {
			//连接ftp
			client = connectFtp();
			
			//改变目录路径
			changeWorkingDirectory(client, directory);
			//上传
			boolean flag = client.storeFile(fileName, inputStream);
			Assist.mustTrue(flag, "upload result is false [directory : {}] [fileName : {}]", directory, fileName);
		} catch (Exception e) {
			throw Assist.transferException("upload error [directory : {}] [fileName : {}]", e, directory, fileName);
		} finally {
			closeFtp(client);
			Assist.close(inputStream);
		}
		
		return fileName;
	}
	
	/**
	 * 文件是否存在
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @return
	 */
	@Override
	public boolean exist(String directory, String fileName) {
		notBlank(fileName, "fileName cannot be blank");

		FTPClient client = null;
		try {
			//连接ftp
			client = connectFtp();
			
			//文件是否存在逻辑处理
			return existHandle(client, directory, fileName);
		} catch (Exception e) {
			throw Assist.transferException("file exist error [directory : {}] [fileName : {}]", e, directory, fileName);
		}
	}
	
	/**
	 * 下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param outputStream
	 * @return
	 */
	@Override
	public void download(String directory, String fileName, OutputStream outputStream) {
		notBlank(fileName, "fileName cannot be blank");
		notNull(outputStream, "inputStream cannot be blank");
		
		FTPClient client = null;
		try {
			//连接ftp
			client = connectFtp();
			
			//验证文件是否存在
			ftpValidateExist(client, directory, fileName);
			
			//文件路径
			String filePath = packFilePath(directory, fileName);
			
			client.enterLocalPassiveMode();
			boolean flag =  client.retrieveFile(filePath, outputStream);
			Assist.mustTrue(flag, "download result is false [directory : {}] [fileName : {}]", directory, fileName);
		} catch (Exception e) {
			throw Assist.transferException("download error [directory : {}] [fileName : {}]", e, directory, fileName);
		} finally {
			closeFtp(client);
			Assist.close(outputStream);
		}
	}
	
	/**
	 * 文件删除
	 * @param directory 文件目录
	 * @param fileName 文件名
	 */
	@Override
	public void delete(String directory, String fileName) {
		notBlank(fileName, "fileName cannot be blank");
		
		FTPClient client = null;
		try {
			//连接ftp
			client = connectFtp();
			
			//验证文件是否存在
			ftpValidateExist(client, directory, fileName);
			
			//文件路径
			String filePath = packFilePath(directory, fileName);
			
			boolean flag =  client.deleteFile(filePath);
			Assist.mustTrue(flag, "delete result is false [directory : {}] [fileName : {}]", directory, fileName);
		} catch (Exception e) {
			throw Assist.transferException("delete error [directory : {}] [fileName : {}]", e, directory, fileName);
		} finally {
			closeFtp(client);
		}
	}
	
	
	
	
	
	
	
	
	/**
	 * 连接ftp
	 * @return
	 */
	public FTPClient connectFtp() {
		notBlank(hostName, "hostName cannot be blank");
		notNull(port, "port cannot be null");
		notBlank(userName, "userName cannot be blank");
		notBlank(password, "hostName cannot be blank");
		
		FTPClient client = null;
		try {
			client = new FTPClient();
			client.enterLocalPassiveMode();
			//连接FTP服务器
			client.connect(hostName, port);
			//登录FTP服务器
			client.login(userName, password);
			//以二进制上传文件
			client.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);  
			client.setFileType(FTPClient.BINARY_FILE_TYPE);
			//每次读取文件流时缓存数组的大小
			client.setBufferSize(bufferSize);
		} catch (Exception e) {
			throw new RemexFtpConnectFailedException("ftp [host : {}] connect failed", Assist.join(hostName, port), e) ;
		}
		
		// 是否成功登录服务器
		if (client == null || !FTPReply.isPositiveCompletion(client.getReplyCode()))
			throw new RemexFtpConnectFailedException("ftp [host : {}] connect failed", Assist.join(hostName, port)) ;
		
		return client;
	}
	
	/**
	 * 关闭FTPClient
	 * 
	 * @param client
	 */
	public void closeFtp(FTPClient client) {
		if (client != null) {
			try {
				client.logout();
			} catch (Exception e) {
				getLogger().error("ftp close error", e);
			}
			try {
				client.disconnect();
			} catch (Exception e) {
				getLogger().error("ftp close error", e);
			}
		}
	}
	
	/**
	 * 改变目录路径
	 * @param client ftpClient
	 * @param fileDirectory 文件路径
	 */
	public void changeWorkingDirectory(FTPClient client, String fileDirectory) {
		notNull(client, "ftpClient cannot be null");
		
		if (Assist.isNotBlank(fileDirectory)) {
			try {
				//重新封装文件路径
				String fullFileDirectory = packDirectoryPath(fileDirectory);
				
				//根目录
				if (fileSeparator.equals(fullFileDirectory)) return;
				
				// 改变目录路径
				boolean flag = client.changeWorkingDirectory(fullFileDirectory);
				// 目录路径不存在
				if (!flag) {
					List<String> directoryList = FileUtils.splitFile(fileDirectory);
					for (String directory : directoryList) {
						directory = directory.trim();
						if (Assist.isBlank(directory)) {
							continue;
						}
						
						// 改变目录路径
						flag = client.changeWorkingDirectory(directory);
						// 目录路径不存在
						if (!flag) {
							flag = client.makeDirectory(directory);
							if (!flag) {
								throw new RemexFtpFailedException("ftp make directory error [fileDirectory : {}]", fileDirectory) ;
							}

							// 再次改变目录路径
							flag = client.changeWorkingDirectory(directory);
							// 如果再次失败
							if (!flag) {
								throw new RemexFtpFailedException("ftp change again working directory error [fileDirectory : {}]", fileDirectory);
							}
						}
					}
				}
			} catch (Exception e) {
				throw Assist.transferException("ftp change working directory error [fileDirectory : {}]", e, fileDirectory);
			}
		}
	}
	
	/**
	 * 文件是否存在逻辑处理
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @return
	 */
	public boolean existHandle(FTPClient client, String directory, String fileName) {
		try {
			//文件路径
			String filePath = packFilePath(directory, fileName);
			FTPFile[] ftpFileArr = client.listFiles(filePath);
			
			return ftpFileArr != null && ftpFileArr.length != 0;
		} catch (Exception e) {
			throw Assist.transferException("file exist error [directory : {}] [fileName : {}]", e, directory, fileName);
		}
	}
	
	/**
	 * 验证文件是否存在，不存在则抛出异常
	 * @param directory
	 * @param fileName
	 */
	public void ftpValidateExist(FTPClient client, String directory, String fileName) {
		if (!existHandle(client, directory, fileName))
			throw new RemexFileNotFoundException("file not found [directory : {}] [fileName : {}]", directory, fileName);
	}
	
	/**
	 * 统一文件分割符
	 * @param path
	 * @return
	 */
	@Override
	public String unifyFileSeparator(String path) {
		if (Assist.isBlank(path)) return path;
		return path.replace(needReplacefileSeparator, fileSeparator);
	}
	
	
	
	
	

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

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public String getFileSeparator() {
		return fileSeparator;
	}

	public void setFileSeparator(String fileSeparator) {
		this.fileSeparator = fileSeparator;
	}

	public String getNeedReplacefileSeparator() {
		return needReplacefileSeparator;
	}

	public void setNeedReplacefileSeparator(String needReplacefileSeparator) {
		this.needReplacefileSeparator = needReplacefileSeparator;
	}
}
