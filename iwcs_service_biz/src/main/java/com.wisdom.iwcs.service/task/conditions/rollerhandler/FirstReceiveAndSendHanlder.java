package com.wisdom.iwcs.service.task.conditions.rollerhandler;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.iwcs.domain.hikSync.HikRollerData;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.domain.task.dto.PublicContextDTO;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapper.task.TaskContextMapper;
import com.wisdom.iwcs.service.task.conditions.conditonHandler.IConditionHandler;
import com.wisdom.iwcs.service.task.impl.TaskContextService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 第一个送慢料箱收空料箱任务前置条件
 * @author han
 */
@Component
public class FirstReceiveAndSendHanlder implements IConditionHandler {
    private static final Logger logger = LoggerFactory.getLogger(FirstReceiveAndSendHanlder.class);

    @Autowired
    SubTaskMapper subTaskMapper;
    @Autowired
    TaskContextMapper taskContextMapper;
    @Autowired
    TaskContextService taskContextService;

    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        //查找接料信息
        PublicContextDTO publicContextDTO = taskContextService.getPublicContext(subTaskCondition.getSubTaskNum());

        if (publicContextDTO.getEndBerCodeReady() == null) {
            logger.info("子任务{}第一个送料点未准备就绪", subTaskCondition.getSubTaskNum());
            return false;
        }

        //将接料信息转换为json
        HikRollerData hikRollerData = new HikRollerData();
        hikRollerData.setSendFull(publicContextDTO.getEndSendNum().toString());
        hikRollerData.setRcvNull(publicContextDTO.getEmptyRecycleNum().toString());
        String jsonString = JSONObject.toJSONString(hikRollerData);

        //更新数据库
        subTaskMapper.updateJsonData(subTaskCondition.getSubTaskNum(), jsonString);
        logger.info("子任务{}第一个送慢料箱收空料箱data生成结束,生成data为:{}", subTaskCondition.getSubTaskNum(), jsonString);

        return true;
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        int rows = subTaskMapper.updateJsonData(subTaskCondition.getSubTaskNum(), "");
        if (rows <= 0) {
            logger.info("子任务{}第一个送慢料箱收空料箱前置条件回滚失败", subTaskCondition.getSubTaskNum());
            return false;
        }
        logger.info("子任务{}第一个送慢料箱收空料箱前置条件回滚成功", subTaskCondition.getSubTaskNum());
        return true;
    }
}
