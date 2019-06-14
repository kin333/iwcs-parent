package com.wisdom.iwcs.commonDto.dynamicSqlGenerator;

/**
 * 抽象表达式
 *
 * @author ted
 * @create 2019-03-02 下午1:55
 **/
public abstract class AbstractNode {
    /**
     * 翻译
     *
     * @param sqlDynamicContext
     */
    public abstract String interpret(SqlDynamicContext sqlDynamicContext);

    public abstract void execute(SqlDynamicContext sqlDynamicContext);
}
