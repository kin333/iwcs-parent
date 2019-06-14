package com.wisdom.iwcs.commonDto.dynamicSqlGenerator;

/**
 * 列名节点
 *
 * @author ted
 * @create 2019-03-02 下午2:00
 **/
public class ColumnNode extends AbstractNode {
    private String columnName;
    private String table;

    @Override
    public String interpret(SqlDynamicContext sqlDynamicContext) {
        return columnName;
    }

    @Override
    public void execute(SqlDynamicContext sqlDynamicContext) {
        sqlDynamicContext.appendToResultStr(columnName);
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public ColumnNode(String columnName) {
        this.columnName = columnName;
    }
}
