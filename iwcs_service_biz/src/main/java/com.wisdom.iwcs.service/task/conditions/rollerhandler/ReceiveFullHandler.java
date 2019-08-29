package com.wisdom.iwcs.service.task.conditions.rollerhandler;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.iwcs.domain.hikSync.HikRollerData;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.domain.task.TaskContext;
import com.wisdom.iwcs.domain.task.dto.PublicContextDTO;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapper.task.TaskContextMapper;
import com.wisdom.iwcs.service.task.conditions.conditonHandler.IConditionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 接满料箱前置条件
 * @author han
 */
@Component
public class ReceiveFullHandler implements IConditionHandler {
    @Autowired
    SubTaskMapper subTaskMapper;
    @Autowired
    MainTaskMapper mainTaskMapper;
    @Autowired
    TaskContextMapper taskContextMapper;

    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        //查找接料信息
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskCondition.getSubTaskNum());
        TaskContext taskContext = taskContextMapper.selectByMainTaskNum(subTask.getMainTaskNum());
        String context = taskContext.getContext();
        PublicContextDTO publicContextDTO = JSONObject.parseObject(context, PublicContextDTO.class);

        //将接料信息转换为json
        HikRollerData hikRollerData = new HikRollerData();
        hikRollerData.setRcvFull(publicContextDTO.getStartGetNum().toString());

        return true;
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return true;
    }
}
