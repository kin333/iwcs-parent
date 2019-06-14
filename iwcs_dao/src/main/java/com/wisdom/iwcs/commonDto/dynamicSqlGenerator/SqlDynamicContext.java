package com.wisdom.iwcs.commonDto.dynamicSqlGenerator;

import java.util.Map;
import java.util.StringTokenizer;

/**
 * sql动态拼接上下文
 *
 * @author ted
 * @create 2019-03-02 下午1:51
 **/
public class SqlDynamicContext {
    /**
     * 动态参数map类
     */
    private Map<String, Object> mapperParams;
    /**
     * StringTokenizer类，用于将字符串分解为更小的字符串标记(Token)，默认情况下以空格作为分隔符
     */
    private StringTokenizer tokenizer; //
    /**
     * 当前字符串标记
     */
    private String currentToken;
    private StringBuilder resultStrBuilder;

    public SqlDynamicContext(String text, Map<String, Object> mapperParams) {
        //通过传入的指令字符串创建StringTokenizer对象
        tokenizer = new StringTokenizer(text);
        this.mapperParams = mapperParams;
        nextToken();
    }

    //返回下一个标记
    public String nextToken() {
        if (tokenizer.hasMoreTokens()) {
            currentToken = tokenizer.nextToken();
        } else {
            currentToken = null;
        }
        return currentToken;
    }

    /**
     * 是否有下一个标记
     */

    public boolean hasNextToken() {

        return tokenizer.hasMoreTokens();
    }

    //返回当前的标记
    public String currentToken() {
        return currentToken;
    }

    //跳过一个标记
    public void skipToken(String token) {
        if (!token.equals(currentToken)) {
            System.err.println("错误提示：" + currentToken + "解释错误！");
        }
        nextToken();
    }

    //如果当前的标记是一个数字，则返回对应的数值
    public int currentNumber() {
        int number = 0;
        try {
            number = Integer.parseInt(currentToken); //将字符串转换为整数
        } catch (NumberFormatException e) {
            System.err.println("错误提示：" + e);
        }
        return number;
    }

    public String getContextParam(String paramKey) {
        return (String) mapperParams.get(paramKey);
    }

    public void putContextParam(String paramKey, String value) {
        mapperParams.put(paramKey, value);
    }

    public void appendToResultStr(String nodeResult) {
        System.out.println("appendToResultStr 被调用,新参数：" + nodeResult);
        if (resultStrBuilder == null) {
            this.resultStrBuilder = new StringBuilder(" ");
        }
        this.resultStrBuilder.append(nodeResult).append(" ");
        System.out.println();
    }

    public String geFinalResultStr() {
        if (this.resultStrBuilder == null) {
            return null;
        } else {
            return resultStrBuilder.toString();
        }
    }


}
