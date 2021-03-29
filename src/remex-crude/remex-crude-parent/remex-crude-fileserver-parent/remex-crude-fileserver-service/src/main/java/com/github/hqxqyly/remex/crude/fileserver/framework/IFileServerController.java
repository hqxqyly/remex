package com.github.hqxqyly.remex.crude.fileserver.framework;

import com.github.hqxqyly.remex.crude.fileserver.api.msg.MsgFileServerEnum;
import com.github.hqxqyly.remex.fast.framework.structure.controller.IBaseBasicController;
import com.github.hqxqyly.remex.solution.fileserver.utils.FileServerUtils;

/**
 * 基础文件接口
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IFileServerController extends IBaseBasicController {

	/**
	 * 提取文件目录
	 * @param fileEnum
	 */
	default String fetchDirectory(String fileEnum) {
		validateNotBlank(fileEnum, "fieldName");
		
		String directory = FileServerUtils.fetchFileEnumPath(fileEnum);
		notBlank(directory, MsgFileServerEnum.CRUDE_FILE_ENUM_NOT_FOUND);
		return directory;
	}
}
