package com.github.hqxqyly.remex.fast.server.config;

import org.springframework.context.annotation.Import;

import com.github.hqxqyly.remex.boot.db.config.DataSourceExcludeConfig;
import com.github.hqxqyly.remex.boot.mybatis.config.MybatisExcludeConfig;
import com.github.hqxqyly.remex.boot.mybatis.plus.config.MybatisPlusExcludeConfig;

/**
 * fast server db相关排除配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@Import({DataSourceExcludeConfig.class, MybatisExcludeConfig.class, MybatisPlusExcludeConfig.class})
public class FastServerDbExcludeConfig {

}
