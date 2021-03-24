package com.github.hqxqyly.remex.boot.poi.utils;

import java.io.OutputStream;
import java.util.List;

import com.github.hqxqyly.remex.boot.poi.client.ExcelExportClient;
import com.github.hqxqyly.remex.boot.poi.constant.TableTheme;
import com.github.hqxqyly.remex.boot.poi.data.WriteDataCellHandleDpcf;
import com.github.hqxqyly.remex.boot.poi.data.WriteDataCellHandleProperty;
import com.github.hqxqyly.remex.boot.poi.data.WriteDataFetchAll;
import com.github.hqxqyly.remex.boot.poi.interfaces.IWriteDataCellHandle;
import com.github.hqxqyly.remex.boot.poi.interfaces.IWriteDataFetch;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * excel导出工具类
 *
 * @author Qiaoxin.Hong
 */
public class ExcelExportUtils {

	/**
     * excel导出
     * @param <T>
     * @param outputStream 文件输出流
     * @param writeDataFetch 数据提取器
     * @param writeDataCellHandle 列数据处理器
     * @param colParams 列参数工厂
     * @param tableTheme excel样式
     */
    public static <T> void write(OutputStream outputStream, IWriteDataFetch<T> writeDataFetch
    		, IWriteDataCellHandle<T> writeDataCellHandle, ColParamFactory colParamFactory, TableTheme tableTheme) {
    	new ExcelExportClient<T>()
    		.setOutputStream(outputStream)
    		.setColParam(colParamFactory)
			.setWriteDataFetch(writeDataFetch)
	        .setWriteDataCellHandle(writeDataCellHandle)
	        .setTableTheme(tableTheme)
	        .write();
    }
    
    
    
    /**
     * excel导出，默认使用属性反射列数据处理
     * @param <T>
     * @param outputStream 文件输出流
     * @param dataList 数据列表
     * @param colParams 参数列表，","隔开，例：姓名, name, 年龄, age
     */
    public static <T> void write(OutputStream outputStream, List<T> dataList, String colParams) {
    	write(outputStream, dataList, colParams, null);
    }
    
    /**
     * excel导出，默认使用属性反射列数据处理
     * @param <T>
     * @param outputStream 文件输出流
     * @param dataList 数据列表
     * @param colParams 参数列表，","隔开，例：姓名, name, 年龄, age
     * @param tableTheme excel样式
     */
    public static <T> void write(OutputStream outputStream, List<T> dataList, String colParams, TableTheme tableTheme) {
    	write(outputStream, dataList, ColParamFactory.createTwo(colParams), tableTheme);
    }
    
    /**
     * excel导出，默认使用属性反射列数据处理
     * @param <T>
     * @param outputStream 文件输出流
     * @param dataList 数据列表
     * @param colParamFactory 列参数工厂
     */
    public static <T> void write(OutputStream outputStream, List<T> dataList, ColParamFactory colParamFactory) {
    	write(outputStream, dataList, colParamFactory, null);
    }
    
    /**
     * excel导出，默认使用属性反射列数据处理
     * @param <T>
     * @param outputStream 文件输出流
     * @param dataList 数据列表
     * @param colParamFactory 列参数工厂
     * @param tableTheme excel样式
     */
    public static <T> void write(OutputStream outputStream, List<T> dataList, ColParamFactory colParamFactory, TableTheme tableTheme) {
    	write(outputStream, new WriteDataFetchAll<T>(dataList), colParamFactory, tableTheme);
    }
    
    /**
     * excel导出，默认使用属性反射列数据处理
     * @param <T>
     * @param outputStream 文件输出流
     * @param writeDataFetch 数据提取器
     * @param colParams 参数列表，","隔开，例：姓名, name, 年龄, age
     */
    public static <T> void write(OutputStream outputStream, IWriteDataFetch<T> writeDataFetch, String colParams) {
    	write(outputStream, writeDataFetch, colParams, null);
    }
    
    /**
     * excel导出，默认使用属性反射列数据处理
     * @param <T>
     * @param outputStream 文件输出流
     * @param writeDataFetch 数据提取器
     * @param colParams 参数列表，","隔开，例：姓名, name, 年龄, age
     * @param tableTheme excel样式
     */
    public static <T> void write(OutputStream outputStream, IWriteDataFetch<T> writeDataFetch, String colParams
    		, TableTheme tableTheme) {
    	Assist.notBlank(colParams, "colParams cannot be blank");
    	
    	write(outputStream, writeDataFetch, ColParamFactory.createTwo(colParams), tableTheme);
    }
    
    /**
     * excel导出，默认使用属性反射列数据处理
     * @param <T>
     * @param outputStream 文件输出流
     * @param writeDataFetch 数据提取器
     * @param colParamFactory 列参数工厂
     */
    public static <T> void write(OutputStream outputStream, IWriteDataFetch<T> writeDataFetch, ColParamFactory colParamFactory) {
    	write(outputStream, writeDataFetch, colParamFactory, null);
    }
    
    /**
     * excel导出，默认使用属性反射列数据处理
     * @param <T>
     * @param outputStream 文件输出流
     * @param writeDataFetch 数据提取器
     * @param colParamFactory 列参数工厂
     * @param tableTheme excel样式
     */
    public static <T> void write(OutputStream outputStream, IWriteDataFetch<T> writeDataFetch, ColParamFactory colParamFactory
    		, TableTheme tableTheme) {
    	Assist.notNull(colParamFactory, "colParamFactory cannot be blank");
    	Assist.notEmpty(colParamFactory.getFieldList(), "colParamFactory fieldList cannot be empty");
    	
    	write(outputStream, writeDataFetch, new WriteDataCellHandleProperty<T>(colParamFactory.getFieldList())
    			, colParamFactory, tableTheme);
    }
    
    /**
     * excel导出，默认使用pdcf列数据处理
     * @param <T>
     * @param outputStream 文件输出流
     * @param dataList 数据列表
     * @param colParams 参数列表，","隔开，例：姓名, name, 年龄, age
     */
    public static <T> void writeForPdcf(OutputStream outputStream, List<T> dataList, String colParams) {
    	writeForPdcf(outputStream, dataList, colParams, null);
    }
    
    /**
     * excel导出，默认使用pdcf列数据处理
     * @param <T>
     * @param outputStream 文件输出流
     * @param dataList 数据列表
     * @param colParams 参数列表，","隔开，例：姓名, name, 年龄, age
     * @param tableTheme excel样式
     */
    public static <T> void writeForPdcf(OutputStream outputStream, List<T> dataList, String colParams, TableTheme tableTheme) {
    	writeForPdcf(outputStream, dataList, ColParamFactory.createTwo(colParams), tableTheme);
    }
    
    /**
     * excel导出，默认使用pdcf列数据处理
     * @param <T>
     * @param outputStream 文件输出流
     * @param dataList 数据列表
     * @param colParamFactory 列参数工厂
     */
    public static <T> void writeForPdcf(OutputStream outputStream, List<T> dataList, ColParamFactory colParamFactory) {
    	writeForPdcf(outputStream, dataList, colParamFactory, null);
    }
    
    /**
     * excel导出，默认使用pdcf列数据处理
     * @param <T>
     * @param outputStream 文件输出流
     * @param dataList 数据列表
     * @param colParamFactory 列参数工厂
     * @param tableTheme excel样式
     */
    public static <T> void writeForPdcf(OutputStream outputStream, List<T> dataList, ColParamFactory colParamFactory, TableTheme tableTheme) {
    	writeForPdcf(outputStream, new WriteDataFetchAll<T>(dataList), colParamFactory, tableTheme);
    }
    
    /**
     * excel导出，默认使用pdcf列数据处理
     * @param <T>
     * @param outputStream 文件输出流
     * @param writeForPdcfDataFetch 数据提取器
     * @param colParams 参数列表，","隔开，例：姓名, name, 年龄, age
     */
    public static <T> void writeForPdcf(OutputStream outputStream, IWriteDataFetch<T> writeForPdcfDataFetch, String colParams) {
    	writeForPdcf(outputStream, writeForPdcfDataFetch, colParams, null);
    }
    
    /**
     * excel导出，默认使用pdcf列数据处理
     * @param <T>
     * @param outputStream 文件输出流
     * @param writeForPdcfDataFetch 数据提取器
     * @param colParams 参数列表，","隔开，例：姓名, name, 年龄, age
     * @param tableTheme excel样式
     */
    public static <T> void writeForPdcf(OutputStream outputStream, IWriteDataFetch<T> writeForPdcfDataFetch, String colParams
    		, TableTheme tableTheme) {
    	Assist.notBlank(colParams, "colParams cannot be blank");
    	
    	writeForPdcf(outputStream, writeForPdcfDataFetch, ColParamFactory.createTwo(colParams), tableTheme);
    }
    
    /**
     * excel导出，默认使用pdcf列数据处理
     * @param <T>
     * @param outputStream 文件输出流
     * @param writeForPdcfDataFetch 数据提取器
     * @param colParamFactory 列参数工厂
     */
    public static <T> void writeForPdcf(OutputStream outputStream, IWriteDataFetch<T> writeForPdcfDataFetch, ColParamFactory colParamFactory) {
    	writeForPdcf(outputStream, writeForPdcfDataFetch, colParamFactory, null);
    }
    
    /**
     * excel导出，默认使用pdcf列数据处理
     * @param <T>
     * @param outputStream 文件输出流
     * @param writeDataFetch 数据提取器
     * @param colParamFactory 列参数工厂
     * @param tableTheme excel样式
     */
	public static <T> void writeForPdcf(OutputStream outputStream, IWriteDataFetch<T> writeDataFetch, ColParamFactory colParamFactory
    		, TableTheme tableTheme) {
    	Assist.notNull(colParamFactory, "colParamFactory cannot be blank");
    	Assist.notEmpty(colParamFactory.getFieldList(), "colParamFactory fieldList cannot be empty");
    	
    	write(outputStream, writeDataFetch, new WriteDataCellHandleDpcf<T>(colParamFactory.getFieldList())
    			, colParamFactory, tableTheme);
    }
}
