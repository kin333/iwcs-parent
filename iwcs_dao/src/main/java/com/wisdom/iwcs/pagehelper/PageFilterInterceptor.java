/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2017 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.wisdom.iwcs.pagehelper;

import com.github.pagehelper.Dialect;
import com.github.pagehelper.PageException;
import com.github.pagehelper.PageInterceptor;
import com.github.pagehelper.cache.Cache;
import com.github.pagehelper.cache.CacheFactory;
import com.github.pagehelper.util.MSUtils;
import com.github.pagehelper.util.StringUtil;
import com.wisdom.iwcs.domain.system.DataFilterRule;
import com.wisdom.iwcs.pagehelper.annotation.DataFilter;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Mybatis - 通用分页拦截器<br/>
 * 项目地址 : http://git.oschina.net/free/Mybatis_PageHelper
 *
 * @author liuzh/abel533/isea533
 * @version 5.0.0
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Intercepts(
        {
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        }
)
public class PageFilterInterceptor extends PageInterceptor {

    //缓存count查询的ms
    protected Cache<String, MappedStatement> msCountMap = null;
    private Dialect dialect;
    private String default_dialect_class = "com.github.pagehelper.PageHelper";
    private Field additionalParametersField;
    private String countSuffix = "_COUNT";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        try {
            Object[] args = invocation.getArgs();
            MappedStatement ms = (MappedStatement) args[0];
            Object parameter = args[1];
            RowBounds rowBounds = (RowBounds) args[2];
            ResultHandler resultHandler = (ResultHandler) args[3];
            Executor executor = (Executor) invocation.getTarget();
            CacheKey cacheKey;
            BoundSql boundSql;
            //由于逻辑关系，只会进入一次
            if (args.length == 4) {
                //4 个参数时
                boundSql = ms.getBoundSql(parameter);
                cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
            } else {
                //6 个参数时
                cacheKey = (CacheKey) args[4];
                boundSql = (BoundSql) args[5];
            }

            //根据数据权限去获取查询条件
            DataFilter vl = DataFilterResolver.resolve(boundSql, ms);
//            System.out.println(vl.authority() + vl.authority().length());
            if (vl.authority() != null && vl.authority().length() > 0) {
                List<DataFilterRule> dataFilterRuleList = getDataFilterRules(ms, parameter, vl.authority());
                if (dataFilterRuleList.size() != 0) {
                    for (DataFilterRule dataFilterRule : dataFilterRuleList) {
                        this.setSelectCondition(parameter, dataFilterRule);
                    }

                    String newSql = this.addWhereSql(boundSql.getSql(), dataFilterRuleList);

                    boundSql = new BoundSql(ms.getConfiguration(), newSql, boundSql.getParameterMappings(), parameter);
                }

            }

            List resultList;
            //调用方法判断是否需要进行分页，如果不需要，直接返回结果
            if (!dialect.skip(ms, parameter, rowBounds)) {
                //反射获取动态参数
                String msId = ms.getId();
                Configuration configuration = ms.getConfiguration();
                Map<String, Object> additionalParameters = (Map<String, Object>) additionalParametersField.get(boundSql);
                //判断是否需要进行 count 查询
                if (dialect.beforeCount(ms, parameter, rowBounds)) {
                    String countMsId = msId + countSuffix;
                    Long count;
                    //先判断是否存在手写的 count 查询
                    MappedStatement countMs = getExistedMappedStatement(configuration, countMsId);
                    if (countMs != null) {
                        count = executeManualCount(executor, countMs, parameter, boundSql, resultHandler);
                    } else {
                        countMs = msCountMap.get(countMsId);
                        //自动创建
                        if (countMs == null) {
                            //根据当前的 ms 创建一个返回值为 Long 类型的 ms
                            countMs = MSUtils.newCountMappedStatement(ms, countMsId);
                            msCountMap.put(countMsId, countMs);
                        }
                        count = executeAutoCount(executor, countMs, parameter, boundSql, rowBounds, resultHandler);
                    }
                    //处理查询总数
                    //返回 true 时继续分页查询，false 时直接返回
                    if (!dialect.afterCount(count, parameter, rowBounds)) {
                        //当查询总数为 0 时，直接返回空的结果
                        return dialect.afterPage(new ArrayList(), parameter, rowBounds);
                    }
                }
                //判断是否需要进行分页查询
                if (dialect.beforePage(ms, parameter, rowBounds)) {
                    //生成分页的缓存 key
                    CacheKey pageKey = cacheKey;
                    //处理参数对象
                    parameter = dialect.processParameterObject(ms, parameter, boundSql, pageKey);
                    //调用方言获取分页 sql
                    String pageSql = dialect.getPageSql(ms, boundSql, parameter, rowBounds, pageKey);
                    BoundSql pageBoundSql = new BoundSql(configuration, pageSql, boundSql.getParameterMappings(), parameter);
                    //设置动态参数
                    for (String key : additionalParameters.keySet()) {
                        pageBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
                    }
                    //执行分页查询
                    resultList = executor.query(ms, parameter, RowBounds.DEFAULT, resultHandler, pageKey, pageBoundSql);
                } else {
                    //不执行分页的情况下，也不执行内存分页
                    resultList = executor.query(ms, parameter, RowBounds.DEFAULT, resultHandler, cacheKey, boundSql);
                }
            } else {
                //rowBounds用参数值，不使用分页插件处理时，仍然支持默认的内存分页
                resultList = executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);
            }
            return dialect.afterPage(resultList, parameter, rowBounds);
        } finally {
            dialect.afterAll();
        }
    }

    /**
     * 执行手动设置的 count 查询，该查询支持的参数必须和被分页的方法相同
     *
     * @param executor
     * @param countMs
     * @param parameter
     * @param boundSql
     * @param resultHandler
     * @return
     * @throws SQLException
     */
    private Long executeManualCount(Executor executor, MappedStatement countMs,
                                    Object parameter, BoundSql boundSql,
                                    ResultHandler resultHandler) throws SQLException {
        CacheKey countKey = executor.createCacheKey(countMs, parameter, RowBounds.DEFAULT, boundSql);
        BoundSql countBoundSql = countMs.getBoundSql(parameter);
        Object countResultList = executor.query(countMs, parameter, RowBounds.DEFAULT, resultHandler, countKey, countBoundSql);
        Long count = ((Number) ((List) countResultList).get(0)).longValue();
        return count;
    }

    /**
     * 执行自动生成的 count 查询
     *
     * @param executor
     * @param countMs
     * @param parameter
     * @param boundSql
     * @param rowBounds
     * @param resultHandler
     * @return
     * @throws IllegalAccessException
     * @throws SQLException
     */
    private Long executeAutoCount(Executor executor, MappedStatement countMs,
                                  Object parameter, BoundSql boundSql,
                                  RowBounds rowBounds, ResultHandler resultHandler) throws IllegalAccessException, SQLException {
        Map<String, Object> additionalParameters = (Map<String, Object>) additionalParametersField.get(boundSql);
        //创建 count 查询的缓存 key
        CacheKey countKey = executor.createCacheKey(countMs, parameter, RowBounds.DEFAULT, boundSql);
        //调用方言获取 count sql
        String countSql = dialect.getCountSql(countMs, boundSql, parameter, rowBounds, countKey);
        //countKey.update(countSql);
        BoundSql countBoundSql = new BoundSql(countMs.getConfiguration(), countSql, boundSql.getParameterMappings(), parameter);
        //当使用动态 SQL 时，可能会产生临时的参数，这些参数需要手动设置到新的 BoundSql 中
        for (String key : additionalParameters.keySet()) {
            countBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
        }
        //执行 count 查询
        Object countResultList = executor.query(countMs, parameter, RowBounds.DEFAULT, resultHandler, countKey, countBoundSql);
        Long count = (Long) ((List) countResultList).get(0);
        return count;
    }

    /**
     * 尝试获取已经存在的在 MS，提供对手写count和page的支持
     *
     * @param configuration
     * @param msId
     * @return
     */
    private MappedStatement getExistedMappedStatement(Configuration configuration, String msId) {
        MappedStatement mappedStatement = null;
        try {
            mappedStatement = configuration.getMappedStatement(msId, false);
        } catch (Throwable t) {
            //ignore
        }
        return mappedStatement;
    }

    @Override
    public Object plugin(Object target) {
        //TODO Spring bean 方式配置时，如果没有配置属性就不会执行下面的 setProperties 方法，就不会初始化，因此考虑在这个方法中做一次判断和初始化
        //TODO https://github.com/pagehelper/Mybatis-PageHelper/issues/26
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        //缓存 count ms
        msCountMap = CacheFactory.createCache(properties.getProperty("msCountCache"), "ms", properties);
        String dialectClass = properties.getProperty("dialect");
        if (StringUtil.isEmpty(dialectClass)) {
            dialectClass = default_dialect_class;
        }
        try {
            Class<?> aClass = Class.forName(dialectClass);
            dialect = (Dialect) aClass.newInstance();
        } catch (Exception e) {
            throw new PageException(e);
        }
        dialect.setProperties(properties);

        String countSuffix = properties.getProperty("countSuffix");
        if (StringUtil.isNotEmpty(countSuffix)) {
            this.countSuffix = countSuffix;
        }

        try {
            //反射获取 BoundSql 中的 additionalParameters 属性
            additionalParametersField = BoundSql.class.getDeclaredField("additionalParameters");
            additionalParametersField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new PageException(e);
        }
    }

    private List<DataFilterRule> getDataFilterRules(MappedStatement mappedStatement, Object parameter, String authority) {
//        Integer userId = SecurityUtils.getCurrentUserId();
        List<DataFilterRule> ruleList = new ArrayList<>();

        String whereSql = null;
        String getRoleSql = "SELECT sdr.rule_column as ruleColumn, sdr.rule_conditions as ruleConditions, sdr.rule_value as ruleValue FROM s_data_rule sdr INNER JOIN s_authority sa ON sa.id = sdr.authority_id AND sa.auth_type = 3 INNER JOIN s_authority sa1 ON sa1.id = sa.parent_id AND sa1.name = ? INNER JOIN s_role_authority sra ON sra.authority_id = sdr.authority_id AND sra.roleid IN (SELECT role_ids FROM s_user WHERE id = ? );";
        Connection connection = null;
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        try {
            connection = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
            countStmt = connection.prepareStatement(getRoleSql);
            countStmt.setString(1, authority);
//            countStmt.setInt(2, userId);
            rs = countStmt.executeQuery();
            String role = null;
            while (rs.next()) {
                DataFilterRule dataFilterRule = new DataFilterRule();
                dataFilterRule.setRuleColumn(rs.getString(1));
                dataFilterRule.setRuleConditions(rs.getString(2));
                dataFilterRule.setRuleValue(rs.getString(3));

                ruleList.add(dataFilterRule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                countStmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //List<String> ruleList = new ArrayList<>();
//			//需要的数据的获取渠道：１．从参数中获得，参数分集中情况　对象　Map @param　２. 从User中获取
//			for (DataFilterRule dataFilterRule : dataFilterRuleList) {
//				ruleList.add(this.getSelectCondition(parameterObject, dataFilterRule));
//			}

        return ruleList;
    }

    private void setSelectCondition(Object paramObj, DataFilterRule dataFilterRule) {

//        Boolean gotParam = null;
//        String ruleValue = dataFilterRule.getRuleValue();
//        if(ruleValue.contains("#{")&&ruleValue.contains("}")){
//            gotParam = false;
//
//            String realRuleValueVariable = ruleValue.substring(ruleValue.indexOf("#{") + 2, ruleValue.indexOf("}"));
//            Object realRuleValue = null;
//
//            //从参数中获取数据
//            if(paramObj instanceof MapperMethod.ParamMap<?>) {
//                MapperMethod.ParamMap<?> mmp = (MapperMethod.ParamMap<?>) paramObj;
//                if(mmp.containsKey(realRuleValueVariable)){
//                    realRuleValue = mmp.get(realRuleValueVariable);
//                    gotParam = true;
//                }
//                // 2、Process Map param
//            } else if (paramObj instanceof Map) {
//                if(((Map) paramObj).containsKey(realRuleValueVariable)){
//                    realRuleValue = ((Map) paramObj).get(realRuleValueVariable);
//                    gotParam = true;
//                }
//                // 3、Process POJO entity param
//            } else {
//
//            }
//
//            //从User中获取数据
//            if(gotParam!=null && !gotParam){
//                UserInfoExt userInfoExt = SecurityUtils.getCurrentUser().getUserInfo();
//                Method[] methods = userInfoExt.getClass().getMethods();
//                if(methods != null && methods.length > 0) {
//                    List<Method> theMethods = Arrays.asList(methods).stream().filter(method -> method.getName().equals("get" + realRuleValueVariable.substring(0, 1).toUpperCase() + realRuleValueVariable.substring(1))).collect(Collectors.toList());
//                    if(theMethods !=null && theMethods.size() == 1) {
//                        realRuleValue = theMethods.get(0).invoke(userInfoExt);
//                        gotParam = true;
//                    }
//                }
//            }
//
//            if(gotParam){
//                realRuleValue = ruleValue.replace("#{" + realRuleValueVariable + "}", realRuleValue.toString());
////                return dataFilterRule.getRuleColumn() + " " + dataFilterRule.getRuleConditions() + " " + realRuleValue;
//                dataFilterRule.setRuleValue(realRuleValue.toString());
//            }else {
//                throw new TypeException("Can not get the datafilterrule value");
//            }
//
//        }

    }

    private String addWhereSql(String sql, List<DataFilterRule> rules) {

        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Select select = null;
        try {
            select = (Select) parserManager.parse(new StringReader(sql));

            PlainSelect ps = (PlainSelect) select.getSelectBody();

            String tableAlias = "";
            if (null != ps.getFromItem().getAlias()) {
                tableAlias = ps.getFromItem().getAlias().getName().trim() + ".";
            }

            List<String> whereSqlList = new ArrayList<>();
            for (DataFilterRule rule : rules) {
                String str = tableAlias + rule.getRuleColumn() + " " + rule.getRuleConditions() + " " + rule.getRuleValue();
                whereSqlList.add(str);
            }

            String whereSql = String.join(" AND ", whereSqlList);
            PageStringValue whereStringValue = new PageStringValue(ps.getWhere() + " AND " + whereSql);

            ps.setWhere(whereStringValue);
//            System.out.println(ps);

            return ps.toString();

        } catch (JSQLParserException e) {
            e.printStackTrace();
        }

        return sql;

//        return null;
    }

    public static class PageStringValue extends StringValue {
        public PageStringValue(String escapedValue) {
            super(escapedValue);
        }

        @Override
        public String toString() {
            return this.getValue();
        }
    }

    /*public static void main(String[] args) {
        String sql = "select (select u.name from t_test u where u.id = t.customer_id) as customerName, (select u.departname from s_depart u where u.ID = t.sys_org_code) as sysQrgCodeName, userName,t.* from t_test t left join (select u.real_name as userName,u.id as aa from s_user u) a on aa = t.user_id where 1 = 1 and t.delete_flag = 0 \n and userName like CONCAT('%', ?, '%')order by t.last_modified_time desc";
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Select select = null;
        try {
            select = (Select) parserManager.parse(new StringReader(sql));

            PlainSelect ps = (PlainSelect) select.getSelectBody();
            System.out.println(ps.getWhere().toString());
            Expression whereExpression = ps.getWhere();

            System.out.println(ps.getFromItem());
            System.out.println(ps.getFromItem().getAlias());
            System.out.println(ps.getFromItem().getAlias().getName().trim());
            System.out.println(ps.getFromItem().getAlias().isUseAs());
            System.out.println(ps.getFromItem().getAlias().toString());

            System.out.println(select.getSelectBody());

            PageStringValue whereStringValue = new PageStringValue(ps.getWhere() + " AND " + "aa = bb AND cc like 1");

            ps.setWhere(whereStringValue);

            System.out.println(ps);

        } catch (JSQLParserException e) {
            e.printStackTrace();
        }


    }*/
}
