package com.github.hqxqyly.remex.boot.poi.utils;

import java.util.ArrayList;
import java.util.List;

import com.github.hqxqyly.remex.boot.constant.BConst;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * 列参数工厂
 * 
 * @author Qiaoxin.Hong
 *
 */
public class ColParamFactory {
	
	/** excel列标题列表 */
    protected List<String> colNameList = new ArrayList<>();
    
    /** 字段属性列表 */
	protected List<String> fieldList = new ArrayList<>();
    
    /** excel列宽度列表，默认乘256 */
    protected List<Integer> colWidthList = new ArrayList<>();

	/**
	 * 参数列表，一种值（标题名称），","隔开，如：姓名,年龄
	 * @param colParams
	 */
	public static ColParamFactory createOne(String colParams) {
		ColParamFactory factory = new ColParamFactory();
		if (Assist.isBlank(colParams)) return factory;
		
		//参数切分
		List<String> colParamList = Assist.splitToList(colParams, BConst.COMMA);
		//去掉前后空格
		colParamList = Assist.trimToList(colParamList);
		
		Assist.add(factory.getColNameList(), colParamList);
			
		return factory;
	}
	
	/**
	 * 参数列表，二种值（标题名称, 字段属性名），","隔开，如：姓名, name, 年龄, age
	 * @param colParams
	 */
	public static ColParamFactory createTwo(String colParams) {
		ColParamFactory factory = new ColParamFactory();
		if (Assist.isBlank(colParams)) return factory;
		
		//参数切分
		List<String> colParamList = Assist.splitToList(colParams, BConst.COMMA);
		//去掉前后空格
		colParamList = Assist.trimToList(colParamList);
		
		for (int i = 0; i < colParamList.size(); i+=2) {
			factory.getColNameList().add(colParamList.get(i));
			factory.getFieldList().add(colParamList.get(i + 1));
		}
			
		return factory;
	}
	
	/**
	 * 参数列表，三种值（标题名称, 字段属性名, 列宽度），","隔开，如：姓名, name, 30, 年龄, age, 20 
	 * @param colParams
	 */
	public static ColParamFactory createThree(String colParams) {
		ColParamFactory factory = new ColParamFactory();
		if (Assist.isBlank(colParams)) return factory;
		
		//参数切分
		List<String> colParamList = Assist.splitToList(colParams, BConst.COMMA);
		//去掉前后空格
		colParamList = Assist.trimToList(colParamList);
		
		for (int i = 0; i < colParamList.size(); i+=3) {
			factory.getColNameList().add(colParamList.get(i));
			factory.getFieldList().add(colParamList.get(i + 1));
			factory.getColWidthList().add(Assist.toInteger(colParamList.get(i + 3)));
		}
			
		return factory;
	}
	
	/**
	 * 添加一列
	 * @param colName
	 * @return
	 */
	public ColParamFactory addCol(String colName) {
		getColNameList().add(colName);
		return this;
	}
	
	/**
	 * 添加一列
	 * @param colName
	 * @param field
	 * @return
	 */
	public ColParamFactory addCol(String colName, String field) {
		getColNameList().add(colName);
		getFieldList().add(field);
		return this;
	}
	
	/**
	 * 添加一列
	 * @param colName
	 * @param field
	 * @param colWidth
	 * @return
	 */
	public ColParamFactory addCol(String colName, String field, Integer colWidth) {
		getColNameList().add(colName);
		getFieldList().add(field);
		getColWidthList().add(colWidth);
		return this;
	}

	public List<String> getColNameList() {
		return colNameList;
	}

	public void setColNameList(List<String> colNameList) {
		this.colNameList = colNameList;
	}

	public List<String> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<String> fieldList) {
		this.fieldList = fieldList;
	}

	public List<Integer> getColWidthList() {
		return colWidthList;
	}

	public void setColWidthList(List<Integer> colWidthList) {
		this.colWidthList = colWidthList;
	}
}
