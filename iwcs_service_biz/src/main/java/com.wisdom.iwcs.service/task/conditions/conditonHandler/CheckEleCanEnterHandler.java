package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.mapper.task.SubTaskConditionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 检查电梯是否可以进入前置条件
 * @author han
 */
@Service
public class CheckEleCanEnterHandler implements IConditionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CheckEleCanEnterHandler.class);
    @Autowired
    SubTaskConditionMapper subTaskConditionMapper;

    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        logger.info("子任务{}开始检查电梯是否可以进入", subTaskCondition.getSubTaskNum());
        subTaskCondition.setConditonHandler("checkEleCanEnterHandler");
        while (true) {
            SubTaskCondition tmpSubTaskCondition = subTaskConditionMapper.selectByTaskNumAndHandler(subTaskCondition);
            if (tmpSubTaskCondition != null && TaskConstants.metStatus.CONFORM.equals(tmpSubTaskCondition.getConditionMetStatus())) {
                logger.info("子任务{}检查电梯已可以进入",  subTaskCondition.getSubTaskNum());
                break;
            }
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return true;
    }
}
