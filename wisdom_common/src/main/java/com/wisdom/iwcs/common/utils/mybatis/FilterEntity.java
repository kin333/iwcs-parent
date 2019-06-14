package com.wisdom.iwcs.common.utils.mybatis;

/**
 * Created by lin on 17-9-6.
 */
public class FilterEntity {
    private String column;
    private String expression;
    private Object value;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
