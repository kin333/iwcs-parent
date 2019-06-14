package com.wisdom.iwcs.commonDto.dynamicSqlGenerator;

import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * 结构解释工具
 *
 * @author ted
 * @create 2019-03-02 下午3:00
 **/
public class SqlDymaticInstructionHandler {

    private String instruction;
    private AbstractNode node;
    private SqlDynamicContext sqlDynamicContext;


    public void handle(String instruction, Map<String, Object> parms) {
        SqlDynamicContext sqlDynamicContext = new SqlDynamicContext(instruction, parms);
        this.sqlDynamicContext = sqlDynamicContext;

        //声明一个栈对象用于存储抽象语法树
        Stack stack = new Stack();
        LinkedList<AbstractNode> queueOp = new LinkedList<>();
        QueueOperations queueOperations = new QueueOperations(queueOp);
        while (true) {
            String currentToken = sqlDynamicContext.currentToken();

            //有未处理字符串字符串，继续解析
            if (currentToken != null) {

                currentToken = currentToken.trim();

                if (currentToken.startsWith("#{")) {
                    //环境参数
                    String paramKey = currentToken.substring(2, currentToken.length() - 1).trim();
                    AbstractNode contextParamValueNode = new ContextParamValueNode(paramKey);
                    queueOp.offer(contextParamValueNode);

                } else if (currentToken.startsWith("[") && currentToken.endsWith("]")) {
                    //表名
                    String columnName = currentToken.substring(1, currentToken.length() - 1).trim();
                    AbstractNode contextParamValueNode = new ColumnNode(columnName);
                    queueOp.offer(contextParamValueNode);
                } else if (currentToken.startsWith("$")) {
                    String constant = currentToken.substring(1).trim();
                    OperatorNode operatorNode = new OperatorNode(constant);
                    queueOp.offer(operatorNode);
                } else {
                    OperatorNode operatorNode = new OperatorNode(currentToken);
                    queueOp.offer(operatorNode);
                }
                sqlDynamicContext.nextToken();
            } else {
                stack.push(queueOperations);
                break;
            }
        }

        //将全部表达式从栈中弹出
        this.node = (AbstractNode) stack.pop();


    }

    public String output() {

        String result = node.interpret(this.sqlDynamicContext); //解释表达式
        return result;
    }

    public String getResultStr() {
        //解释表达式
        node.execute(this.sqlDynamicContext);
        return sqlDynamicContext.geFinalResultStr();
    }


}
