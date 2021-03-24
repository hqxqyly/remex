package com.github.hqxqyly.remex.boot.mybatis.constant;

import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * mybatis配置项
 * 
 * @author Qiaoxin.Hong
 *
 */
public class MybatisOptions {

	/** 默认的mapper文件地址 */
	public static String[] DEFAULT_MAPPER_LOCATIONS = Assist.asArray("classpath:mapper/*.xml", "classpath:mapper/*/*.xml", "classpath:mapper/*/*/*.xml");
	
	/** 默认的实体简单类名通配包名 */
	public static String DEFAULT_TYPE_ALIASES_PACKAGE = "*.*.*.entity, *.*.*.*.entity, *.*.*.*.*.entity";
}
