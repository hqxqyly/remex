package com.github.hqxqyly.remex.crude.fileserver.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.crude.fileserver.framework.IFileServerController;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;
import com.github.hqxqyly.remex.solution.fileserver.utils.FileServerUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "文件缩略图模式接口")
@RestController
@RequestMapping("/fileserver/thumbnail/")
public class FileServerThumbnailController implements IFileServerController {

	@RequestMapping(value = "upload", method = {RequestMethod.GET, RequestMethod.POST})
	@ApiOperation("文件上传")
	public Result<String> upload(@RequestParam("fileEnum") String fileEnum, @RequestPart("file") MultipartFile file) {
		//提取文件目录
		String directory = fetchDirectory(fileEnum);
		//文件上传
		String fileName = FileServerUtils.thumbnailUpload(directory, file);
		return newResult(fileName);
	}
	
	@RequestMapping(value = "uploadBatch", method = {RequestMethod.GET, RequestMethod.POST})
	@ApiOperation("文件批量上传")
	public Result<List<String>> uploadBatch(@RequestParam("fileEnum") String fileEnum, @RequestPart("fileList") MultipartFile[] fileList) {
		//提取文件目录
		String directory = fetchDirectory(fileEnum);
		//文件上传
		
		List<String> fileNameList = FileServerUtils.thumbnailUploadBatch(directory, fileList);
		return newResult(fileNameList);
	}

	@RequestMapping(value = "uploadForDate", method = {RequestMethod.GET, RequestMethod.POST})
	@ApiOperation("文件上传，以日期分目录")
	public Result<String> uploadForDate(@RequestParam("fileEnum") String fileEnum, @RequestPart("file") MultipartFile file) {
		//提取文件目录
		String directory = fetchDirectory(fileEnum);
		//文件上传
		String fileName = FileServerUtils.thumbnailUploadForDate(directory, file);
		return newResult(fileName);
	}
	
	@RequestMapping(value = "uploadBatchForDate", method = {RequestMethod.GET, RequestMethod.POST})
	@ApiOperation("文件批量上传，以日期分目录")
	public Result<List<String>> uploadBatchForDate(@RequestParam("fileEnum") String fileEnum, @RequestPart("fileList") MultipartFile[] fileList) {
		//提取文件目录
		String directory = fetchDirectory(fileEnum);
		//文件上传
		List<String> fileNameList = FileServerUtils.thumbnailUploadBatchForDate(directory, fileList);
		return newResult(fileNameList);
	}
	
	@RequestMapping(value = "download", method = {RequestMethod.GET, RequestMethod.POST})
	@ApiOperation("文件下载")
	public void download(@RequestParam("fileEnum") String fileEnum, @RequestParam("file") String file
			, @RequestParam("modelKey") String modelKey) {
		//提取文件目录
		String directory = fetchDirectory(fileEnum);
		//文件下载
		FileServerUtils.thumbnailDownloadToResponse(directory, file, modelKey);
	}
	
	@RequestMapping(value = "downloadEach", method = {RequestMethod.GET, RequestMethod.POST})
	@ApiOperation("多文件下载，直到下载到")
	public void downloadEach(@RequestParam("fileEnumArr") String[] fileEnumArr, @RequestParam("file") String file
			, @RequestParam("modelKey") String modelKey) {
		//提取文件目录
		List<String> directoryList = Assist.forEachToList(fileEnumArr, this::fetchDirectory);
		//文件下载
		FileServerUtils.thumbnailDownloadEachToResponse(directoryList, file, modelKey);
	}
	
	@RequestMapping(value = "downloadBase64", method = {RequestMethod.GET, RequestMethod.POST})
	@ApiOperation("文件下载Base64")
	public Result<String> downloadBase64(@RequestParam("fileEnum") String fileEnum, @RequestParam("file") String file
			, @RequestParam("modelKey") String modelKey) {
		//提取文件目录
		String directory = fetchDirectory(fileEnum);
		//文件下载
		String txt = FileServerUtils.thumbnailDownloadBase64(directory, file, modelKey);
		return newResult(txt);
	}
}
