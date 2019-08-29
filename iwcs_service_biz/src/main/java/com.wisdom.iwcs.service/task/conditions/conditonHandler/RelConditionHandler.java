package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.googlecode.aviator.AviatorEvaluator;
import com.wisdom.iwcs.domain.task.TaskRelCondition;
import com.wisdom.iwcs.domain.task.dto.BaseContextInfo;
import com.wisdom.iwcs.service.task.template.TemplateRelatedServer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;

@Component("relCreateConditionHandler")
public class RelConditionHandler implements IRelConditionHandler {
    private final Logger logger = LoggerFactory.getLogger(EnterWorkLineHandler.class);
    @Autowired
    TemplateRelatedServer templateRelatedServer;

    @Override
    public boolean handleCondition(String maintaskNum, TaskRelCondition taskRelCondition) {
        Assert.notNull(maintaskNum, "主任务号不可为空");
        Assert.notNull(taskRelCondition, "任务条件配置不可为空");
        BaseContextInfo baseContextInfo = templateRelatedServer.getMainTaskContext(maintaskNum);
        HashMap<String, Object> env = new HashMap<>();
        env.put("bci", baseContextInfo);
        String createConditionExpress = taskRelCondition.getConExpression();
        if (StringUtils.isBlank(createConditionExpress)) {
            return true;
        } else {
            Object executeResult = AviatorEvaluator.execute(createConditionExpress, env);
            if (executeResult instanceof Boolean) {
                return (Boolean) executeResult;
            } else {
                logger.error("子任务创建条件表达式计算错误，结果不是boolean 类型,结果{}，默认任务该条件不满足", executeResult);
                return false;
            }
        }
    }
}
