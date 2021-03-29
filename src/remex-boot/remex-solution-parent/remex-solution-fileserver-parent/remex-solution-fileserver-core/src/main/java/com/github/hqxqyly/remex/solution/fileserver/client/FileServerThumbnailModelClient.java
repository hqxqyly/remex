package com.github.hqxqyly.remex.solution.fileserver.client;

import java.util.ArrayList;
import java.util.List;

import com.github.hqxqyly.remex.boot.interfaces.assist.IAssistClient;
import com.github.hqxqyly.remex.boot.utils.FileUtils;
import com.github.hqxqyly.remex.solution.fileserver.bean.FileServerThumbnailModelBean;

/**
 * 文件服务器缩略图模型处理器
 * 
 * @author Qiaoxin.Hong
 *
 */
public class FileServerThumbnailModelClient implements IAssistClient {

	/** 缩略图模型列表 */
	protected List<FileServerThumbnailModelBean> thumbnailModelList = new ArrayList<>();
	
	/**
	 * 取得默认缩略图模型
	 * @return
	 */
	public FileServerThumbnailModelBean getModel() {
		return getModel(null);
	}
	
	/**
	 * 取得缩略图模型，未获取到则以默认模型作为缺省值
	 * @param key
	 * @return
	 */
	public FileServerThumbnailModelBean getModel(String key) {
		//默认模型
		FileServerThumbnailModelBean defaultModel = null;

		if (isNotEmpty(thumbnailModelList)) {
			for (FileServerThumbnailModelBean curModel : thumbnailModelList) {
				//有配置的模型，则直接使用
				if (isNotBlank(key) && key.equals(curModel.getKey()))
					return curModel;
				
				if (defaultModel == null)
					defaultModel = curModel;
				else {
					//默认模型已经为原图，则不再选择
					if (!defaultModel.isPrototypeSize()) {
						//取原图或最大的
						if (curModel.isPrototypeSize() || curModel.getSize() > defaultModel.getSize()) 
							defaultModel = curModel;
					}
				}
			}
		}
		
		//如果默认模型也为null，则直接返回一个原图根目录的模型
		if (defaultModel == null)
			defaultModel = new FileServerThumbnailModelBean();

		//如果未获取到则以默认模型作为缺省值
		return defaultModel;
	}
	
	/**
	 * 添加缩略图模型
	 * @param model
	 */
	public void addThumbnailModel(FileServerThumbnailModelBean model) {
		notNull(model, "model cannot be null");
		notBlank(model.getKey(), "model key cannot be null");
		thumbnailModelList.add(model);
	}
	
	/**
	 * 加入缩略图模型的文件路径生成方案
	 * @param directory
	 * @return
	 */
	public String packModelPath(String directory, String modelKey) {
		FileServerThumbnailModelBean model = notNull(getModel(modelKey), "model cannot be null");
		return packModelPath(directory, model);
	}
	
	/**
	 * 加入缩略图模型的文件路径生成方案
	 * @param directory
	 * @return
	 */
	public String packModelPath(String directory, FileServerThumbnailModelBean model) {
		notNull(model, "model cannot be null");
		
		boolean isPrefix = isNotBlank(model.getPrefix());
		boolean isSuffix = isNotBlank(model.getSuffix());
		
		//原图且未配置前后缀，则直接返回根目录
		if (model.isPrototypeSize() && !isPrefix && !isSuffix) {
			return directory;
		}
		
		//如果有配置前缀或后缀，则以此生成新的路径
		if (isPrefix || isSuffix) {
			//根目录下不再添加前缀
			if (isNotBlank(directory)) {
				//添加前缀
				if (isPrefix)
					directory = FileUtils.packFilePath(model.getPrefix(), directory);
			}
			
			//添加后缀
			if (isSuffix)
				directory = FileUtils.packFilePath(directory, model.getSuffix());
		} else {  //未配置前缀或后缀，则以模型key作为前缀
			directory = FileUtils.packFilePath(model.getKey(), directory);
		}
		
		
		return directory;
	}
	
	
	
	
	
	
	
	
	public void setThumbnailModelList(List<FileServerThumbnailModelBean> thumbnailModelList) {
		this.thumbnailModelList = thumbnailModelList;
	}
	
	public List<FileServerThumbnailModelBean> getThumbnailModelList() {
		return thumbnailModelList;
	}
}
