package com.github.hqxqyly.remex.boot.mybatis.plus.constant;

/**
 * mybatis sql方法
 * 
 * @see com.baomidou.mybatisplus.core.enums.SqlMethod
 * 
 * @author Qiaoxin.Hong
 *
 */
public enum RemexSqlMethod {
	
	SELECT_COUNT_LIMIT("selectCountLimit", "查询记录数，限制在一定数量范围内，避免全量查询", "<script>%s SELECT COUNT(%s) FROM %s %s %s\n</script>"),
	
	REAL_DELETE_BY_KEY("realDeleteByKey", "根据某字段值真实删除数据", "<script>\n DELETE FROM %s WHERE ${key} = #{value} \n</script>"),

	REAL_DELETE_BY_ID("realDeleteById", "根据ID真实删除一条数据", "<script>\nDELETE FROM %s WHERE %s=#{%s}\n</script>"),
	
	;
	
	private final String method;
    private final String desc;
    private final String sql;

    RemexSqlMethod(String method, String desc, String sql) {
        this.method = method;
        this.desc = desc;
        this.sql = sql;
    }

    public String getMethod() {
        return method;
    }

    public String getDesc() {
        return desc;
    }

    public String getSql() {
        return sql;
    }
}
