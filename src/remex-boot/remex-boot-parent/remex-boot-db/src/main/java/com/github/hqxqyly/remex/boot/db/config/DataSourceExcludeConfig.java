package com.github.hqxqyly.remex.boot.db.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 数据源排除配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@EnableAutoConfiguration(exclude = {RemexDataSourceAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class DataSourceExcludeConfig {

}
