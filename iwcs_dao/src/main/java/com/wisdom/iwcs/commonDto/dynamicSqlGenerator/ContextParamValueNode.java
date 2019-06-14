package com.wisdom.iwcs.commonDto.dynamicSqlGenerator;

/**
 * 单数值
 *
 * @author ted
 * @create 2019-03-02 下午2:47
 **/
public class ContextParamValueNode extends AbstractNode {
    private String key;

    @Override
    public String interpret(SqlDynamicContext sqlDynamicContext) {
        return sqlDynamicContext.getContextParam(key);
    }

    @Override
    public void execute(SqlDynamicContext sqlDynamicContext) {
        sqlDynamicContext.appendToResultStr("'" + sqlDynamicContext.getContextParam(key) + "'");
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ContextParamValueNode(String paramKey) {
        this.key = paramKey;
    }
}
