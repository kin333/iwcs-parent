package com.wisdom.iwcs.commonDto.dynamicSqlGenerator;

import java.util.Queue;

/**
 * 队列操作
 *
 * @author ted
 * @create 2019-03-02 下午5:45
 **/
public class QueueOperations extends AbstractNode {
    private Queue<AbstractNode> abstractNodeQueue;

    @Override
    public String interpret(SqlDynamicContext sqlDynamicContext) {
        StringBuilder sb = new StringBuilder(" ");
        for (AbstractNode n : abstractNodeQueue) {
            sb.append(n.interpret(sqlDynamicContext));
        }
        return sb.toString();
    }

    @Override
    public void execute(SqlDynamicContext sqlDynamicContext) {
        StringBuilder sb = new StringBuilder(" ");
        for (AbstractNode n : abstractNodeQueue) {
//            sqlDynamicContext.appendToResultStr(n.interpret(sqlDynamicContext));
            n.execute(sqlDynamicContext);
        }
    }

    public QueueOperations(Queue<AbstractNode> abstractNodeQueue) {
        this.abstractNodeQueue = abstractNodeQueue;
    }
}
