package com.wisdom.iwcs.commonDto.dynamicSqlGenerator;

/**
 * 预算符
 *
 * @author ted
 * @create 2019-03-02 下午2:45
 **/
public class OperatorNode extends AbstractNode {
    private String code;

    @Override
    public String interpret(SqlDynamicContext sqlDynamicContext) {
        return code;
    }

    @Override
    public void execute(SqlDynamicContext sqlDynamicContext) {
        sqlDynamicContext.appendToResultStr(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public OperatorNode(String code) {
        this.code = code;
    }
}
