package com.smart.remex.ark.fast.builder.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.smart.remex.ark.fast.builder.dto.BuildParamDto;
import com.smart.remex.ark.fast.builder.exception.RemexFastBuilderException;
import com.smart.remex.ark.fast.builder.utils.Assert;
import com.smart.remex.ark.fast.builder.utils.SourceCloseUtils;

/**
 * 构建service
 * 
 * @author Qiaoxin.Hong
 *
 */
public class BuildService {
	/** 独立项目模板路径 */
	public final static String TEMPLATE_PATH_SINGLE = "template\\single";
	
	/** cloud项目模板路径 */
	public final static String TEMPLATE_PATH_CLOUD = "template\\cloud";
	
	/** 模板项目名 */
	public final static String TEMPLATE_PROJECT_NAME = "remexfastbuilderProject";
	
	/** 模板服务名 */
	public final static String TEMPLATE_SERVICE_NAME = "remexfastbuildertemplate";
	
	/** 模板类名 */
	public final static String TEMPLATE_CLASS_NAME = "Remexfastbuildertemplate";
	
	/** 模板包名 */
	public final static String TEMPLATE_PACKAGE_NAME = "remexfastbuilderpackage";
	
	/** 模板jdbc驱动类 */
	public final static String TEMPLATE_JDBC_DRIVER = "remexfastbuilderJdbcDriver";
	
	/** 模板jdbc url */
	public final static String TEMPLATE_JDBC_URL = "remexfastbuilderJdbcUrl";
	
	/** 模板jdbc用户名 */
	public final static String TEMPLATE_JDBC_USERNAME = "remexfastbuilderJdbcUserName";
	
	/** 模板jdbc密码 */
	public final static String TEMPLATE_JDBC_PASSWORD = "remexfastbuilderJdbcPassword";
	
	/**
	 * 构建独立项目
	 */
	public void buildSingle(BuildParamDto paramDto) {
		buildHandle(paramDto, TEMPLATE_PATH_SINGLE);
	}
	
	/**
	 * 构建cloud
	 */
	public void buildCloud(BuildParamDto paramDto) {
		buildHandle(paramDto, TEMPLATE_PATH_CLOUD);
	}
	
	/**
	 * 构建
	 * @param paramDto
	 */
	protected void buildHandle(BuildParamDto paramDto, String tempPath) {
		Assert.notBlank(paramDto.getDomainName(), "域名不能为空");
		Assert.notBlank(paramDto.getProjectName(), "项目名不能为空");
		Assert.notBlank(paramDto.getServiceName(), "项目名不能为空");
		Assert.notBlank(paramDto.getServiceClassName(), "项目类名不能为空");
		Assert.notBlank(paramDto.getFilePath(), "输出地址不能为空");
		Assert.notBlank(paramDto.getServiceName(), "服务名不能为空");
		
		//系统路径
		String rootPath = System.getProperty("user.dir");
		//模板根路径
		String templateRootPath = String.format("%s\\%s", rootPath, tempPath);
		//模板根文件夹
		File templateRootFile = new File(templateRootPath);
		//输出路径
		String outFilePath = packFilePath(paramDto.getFilePath(), paramDto.getProjectName());
		
		File outRootFile = new File(outFilePath);
		if (outRootFile.exists()) {
			throw new RemexFastBuilderException(String.format("输出路径 %s，项目 %s 已存在，请先删除", paramDto.getFilePath()
					, paramDto.getProjectName()));
		}
		
		boolean flag = outRootFile.mkdirs();
		Assert.isTrue(flag, "mkdir directory error, " + outFilePath);
		
		//文件处理
		File[] templateRootFileList = templateRootFile.listFiles();
		for (File templateRootFileItem : templateRootFileList) {
			handleFile(templateRootFileItem, outFilePath, paramDto);
		}
	}
	
	/**
	 * 文件处理
	 * @param srcFile
	 */
	private void handleFile(File srcFile, String outFileParentPath, BuildParamDto paramDto) {
		try {
			String srcFileName = srcFile.getName();
			String outFileName = srcFileName;
			
			//文件夹
			if (srcFile.isDirectory()) {
				String outFilePath =  null;
				//模板包名
				if (TEMPLATE_PACKAGE_NAME.equals(outFileName)) {
					String[] domainNameArr = paramDto.getDomainName().split("\\.");
					outFilePath = outFileParentPath;
					for (String domainName : domainNameArr) {
						outFilePath = packFilePath(outFilePath, domainName);
						File outFile = new File(outFilePath);
						boolean flag = outFile.mkdir();
						Assert.isTrue(flag, "mkdir directory error, " + outFilePath);
					}
				} else if (TEMPLATE_SERVICE_NAME.equals(outFileName)) {  //模板服务名
					outFilePath = packFilePath(outFileParentPath, paramDto.getServiceName());
					File outFile = new File(outFilePath);
					
					boolean flag = outFile.mkdir();
					Assert.isTrue(flag, "mkdir directory error, " + outFilePath);
				} else if (outFileName.startsWith(TEMPLATE_PROJECT_NAME)) {  //模板项目名
					String newFileName = outFileName.replaceFirst(TEMPLATE_PROJECT_NAME, paramDto.getProjectName());
					outFilePath = packFilePath(outFileParentPath, newFileName);
					File outFile = new File(outFilePath);
					
					boolean flag = outFile.mkdir();
					Assert.isTrue(flag, "mkdir directory error, " + outFilePath);
				} else {
					outFilePath = packFilePath(outFileParentPath, outFileName);
					File outFile = new File(outFilePath);
					
					boolean flag = outFile.mkdir();
					Assert.isTrue(flag, "mkdir directory error, " + outFilePath);
				}
				
				File[] subSrcFileList = srcFile.listFiles();
				for (File subSrcFile : subSrcFileList) {
					handleFile(subSrcFile, outFilePath, paramDto);
				}
			} else {  //文件
				outFileName = outFileName.replace(TEMPLATE_SERVICE_NAME, paramDto.getServiceName());
				outFileName = outFileName.replace(TEMPLATE_CLASS_NAME, paramDto.getServiceClassName());
				
				String outFilePath =  packFilePath(outFileParentPath, outFileName);
				File outFile = new File(outFilePath);
				
				boolean flag = outFile.createNewFile();
				Assert.isTrue(flag, "create new file error, " + outFilePath);
				
				InputStream srcIs = null;
				InputStreamReader srcIsr = null;
				BufferedReader srcReader = null;
				OutputStream outOs = null;
				OutputStreamWriter outOsw = null;
				BufferedWriter outWriter = null;
				
				try {
					srcIs = new FileInputStream(srcFile);
					srcIsr = new InputStreamReader(srcIs, "UTF-8");
					srcReader = new BufferedReader(srcIsr);
					outOs = new FileOutputStream(outFile);
					outOsw = new OutputStreamWriter(outOs, "UTF-8");
					outWriter = new BufferedWriter(outOsw);
					
					String content;
					content = srcReader.readLine();
					while (content != null) {
						content = content.replace(TEMPLATE_PACKAGE_NAME, paramDto.getDomainName());
						content = content.replace(TEMPLATE_PROJECT_NAME, paramDto.getProjectName());
						content = content.replace(TEMPLATE_SERVICE_NAME, paramDto.getServiceName());
						content = content.replace(TEMPLATE_CLASS_NAME, paramDto.getServiceClassName());
						content = content.replace(TEMPLATE_JDBC_DRIVER, paramDto.getJdbcDriver());
						content = content.replace(TEMPLATE_JDBC_URL, paramDto.getJdbcUrl());
						content = content.replace(TEMPLATE_JDBC_USERNAME, paramDto.getJdbcUsername());
						content = content.replace(TEMPLATE_JDBC_PASSWORD, paramDto.getJdbcPassword());
						
						outWriter.write(content);
						outWriter.write("\r\n");
						content = srcReader.readLine();
					}
					
					srcReader.close();
					outWriter.close();
				} catch (Exception e) {
					throw new RemexFastBuilderException("handle file error", e);
				} finally {
					SourceCloseUtils.close(outWriter, outOsw, outOs, srcReader, srcIsr, srcIs);
				}
			}
		} catch (Exception e) {
			throw new RemexFastBuilderException("handle file error", e);
		}
	}
	
	/**
	 * 包装文件路径
	 * @param parentPath
	 * @param fileName
	 * @return
	 */
	private String packFilePath(String parentPath, String fileName) {
		String newFilePath =  String.format("%s\\%s", parentPath, fileName);
		return newFilePath;
	}
}
