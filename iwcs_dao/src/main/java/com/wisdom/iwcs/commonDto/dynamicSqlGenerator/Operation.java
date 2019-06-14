package com.wisdom.iwcs.commonDto.dynamicSqlGenerator;

/**
 * 操作
 *
 * @author ted
 * @create 2019-03-02 下午2:48
 **/
public class Operation extends AbstractNode {
    private Expression leftExpression;
    private OperatorNode operatorNode;
    private Expression rightExpression;

    @Override
    public String interpret(SqlDynamicContext sqlDynamicContext) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(leftExpression.interpret(sqlDynamicContext));
        stringBuilder.append(" ");
        stringBuilder.append(operatorNode.interpret(sqlDynamicContext));
        stringBuilder.append(" ");
        stringBuilder.append(rightExpression.interpret(sqlDynamicContext));
        stringBuilder.append(" ");
        return stringBuilder.toString();
    }

    @Override
    public void execute(SqlDynamicContext sqlDynamicContext) {
        leftExpression.execute(sqlDynamicContext);
        operatorNode.execute(sqlDynamicContext);
        rightExpression.execute(sqlDynamicContext);

    }
}
