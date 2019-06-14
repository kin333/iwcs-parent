package com.wisdom.iwcs.common.utils.mybatis;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

/**
 * Created by lin on 17-9-6.
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class DataFilterHelper implements Interceptor {
    private String dataType;
    private SqlUtils sqlUtils;

    public static void startFilter(List<FilterEntity> filterEntities) {
        SqlUtils.setLocalFilter(filterEntities);
    }


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        this.sqlUtils = getSqlUtils();
        return this.sqlUtils.parseFilterSql(invocation);
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.dataType = properties.getProperty("dbtype", "mysql");
    }

    public static SqlUtils getSqlUtils() {
        return new SqlUtils();
    }

    public static void clearFilterEntity() {
        SqlUtils.clearLocalFilter();
    }
}
