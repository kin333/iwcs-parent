package com.wisdom.iwcs.commonDto.dynamicSqlGenerator;

/**
 * 表达式
 *
 * @author ted
 * @create 2019-03-02 下午2:49
 **/
public class Expression extends AbstractNode {

    private AbstractNode abstractNode;

    @Override
    public String interpret(SqlDynamicContext sqlDynamicContext) {
        return abstractNode.interpret(sqlDynamicContext);
    }

    @Override
    public void execute(SqlDynamicContext sqlDynamicContext) {
        abstractNode.execute(sqlDynamicContext);
    }
}
