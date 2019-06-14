package com.wisdom.iwcs.common.utils.mybatis;

import com.google.common.base.Joiner;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lin on 17-9-6.
 */
public class SqlUtils {
    private static final ThreadLocal<List<FilterEntity>> LOCAL_FILTER = new ThreadLocal();

    public static void setLocalFilter(List<FilterEntity> filterEntities) {
        LOCAL_FILTER.set(filterEntities);
    }

    public static List<FilterEntity> getLocalFilterEntity() {
        return LOCAL_FILTER.get();
    }

    public static void clearLocalFilter() {
        LOCAL_FILTER.remove();
    }

    public Object parseFilterSql(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStmtHandler = SystemMetaObject.forObject(statementHandler);
        while (metaStmtHandler.hasGetter("h")) {
            Object object = metaStmtHandler.getValue("h");
            metaStmtHandler = SystemMetaObject.forObject(object);
        }

        while (metaStmtHandler.hasGetter("target")) {
            Object object = metaStmtHandler.getValue("target");
            metaStmtHandler = SystemMetaObject.forObject(object);
        }

        String sql = (String) metaStmtHandler.getValue("delegate.boundSql.sql");

        List<FilterEntity> filterEntities = getLocalFilterEntity();
        boolean notNeedParse = filterEntities == null || filterEntities.size() == 0;
        if (notNeedParse) {
            return invocation.proceed();
        }

        int reset = 0;
        String limitSql = getLimit(sql, reset);
        reset += limitSql.length();
        String orderBySql = getOrderBy(sql, reset);
        reset += orderBySql.length();
        String groupBySql = getGroupBy(sql, reset);
        reset += groupBySql.length();
        String whereSql = getWhere(sql, reset);
        reset += whereSql.length();
        String selectSql = getSelect(sql, reset);
        String filterWhereSql = getFilterWhere(filterEntities);
        String parseSql = Joiner.on(" ").skipNulls().join(Arrays.asList(selectSql, "where", filterWhereSql, whereSql, groupBySql, orderBySql, limitSql));
        metaStmtHandler.setValue("delegate.boundSql.sql", parseSql);
        //XXX:正确？错误?
        //clearLocalFilter();
        return invocation.proceed();
    }

    private static String getFilterWhere(List<FilterEntity> filterEntities) {
        StringBuffer sb = new StringBuffer();
        filterEntities.stream().forEach(
                filterEntity -> {
                    sb.append(" ");
                    sb.append(filterEntity.getColumn());
                    sb.append(" ");
                    sb.append(filterEntity.getExpression());
                    sb.append(" \'");
                    sb.append(filterEntity.getValue());
                    sb.append("\' ");
                    sb.append(" and ");
                }
        );
        return sb.toString();
    }

    private static String getLimit(String sql, int reset) {
        int index;
        if ((index = sql.toLowerCase().lastIndexOf("limit")) != -1) {
            return sql.substring(index, sql.length() - reset);
        }
        return "";
    }

    private static String getOrderBy(String sql, int reset) {
        int index;
        if ((index = sql.toLowerCase().lastIndexOf("order by")) != -1) {
            return sql.substring(index, sql.length() - reset);
        }
        return "";
    }

    private static String getGroupBy(String sql, int reset) {
        int index;
        if ((index = sql.toLowerCase().lastIndexOf("group by")) != -1) {
            return sql.substring(index, sql.length() - reset);
        }
        return "";
    }

    private static String getWhere(String sql, int reset) {
        int index;
        if ((index = sql.toLowerCase().lastIndexOf("where")) != -1) {
            //去除where
            return sql.substring(index + "where".length(), sql.length() - reset);
        }
        return "";
    }

    private static String getSelect(String sql, int reset) {
        //去除where
        return sql.substring(0, sql.length() - reset - "where".length());
    }
}
