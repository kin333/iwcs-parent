package com.wisdom.iwcs.service.task.impl;

import com.wisdom.iwcs.common.utils.constant.CondtionTriger;
import com.wisdom.iwcs.common.utils.idUtils.CodeBuilder;
import com.wisdom.iwcs.common.utils.taskUtils.TaskPriorityEnum;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.domain.task.TaskRelAction;
import com.wisdom.iwcs.domain.task.TaskRelCondition;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import com.wisdom.iwcs.mapper.task.SubTaskConditionMapper;
import com.wisdom.iwcs.mapper.task.TaskRelActionMapper;
import com.wisdom.iwcs.mapper.task.TaskRelConditionMapper;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.wisdom.iwcs.common.utils.TaskConstants.handlerName.ACTIONCHECKHANDLER;
import static com.wisdom.iwcs.common.utils.TaskConstants.mainTaskStatus.MAIN_NOT_ISSUED;

/**
 * 任务创建
 * @Author george
 * @Date 2019/7/3 9:14
 */
@Service
public class TaskCreateService implements ITaskCreateService {
    private final Logger logger = LoggerFactory.getLogger(TaskCreateService.class);

    @Autowired
    private MainTaskMapper mainTaskMapper;
    @Autowired
    MesRequestService mesRequestService;
    @Autowired
    TaskRelActionMapper taskRelActionMapper;
    @Autowired
    MapResouceService mapResouceService;
    @Autowired
    private SubTaskConditionMapper subTaskConditionMapper;
    @Autowired
    private TaskRelConditionMapper taskRelConditionMapper;

    /**
     * 添加子任务条件
     * @param
     * @return
     */
    public void addSubTaskCondition(String templCode, String subTaskNum){
        //通过主任务编号和子任务编号查询
        List<TaskRelCondition> taskRelConditionList = taskRelConditionMapper.selectByTemplCode(templCode);
        for (TaskRelCondition taskRelCondition: taskRelConditionList){
            SubTaskCondition subTaskCondition = new SubTaskCondition();
            subTaskCondition.setSubTaskNum(subTaskNum);
            subTaskCondition.setSubscribeEvent(taskRelCondition.getSubscribeEvent());
            subTaskCondition.setConditonTriger(taskRelCondition.getConditonTriger());
            subTaskCondition.setConditonHandler(taskRelCondition.getConditonHandler());
            subTaskCondition.setCreateDate(new Date());
            subTaskCondition.setStrategyCode(taskRelCondition.getStrategyCode());
            subTaskConditionMapper.insertSelective(subTaskCondition);
        }

        //添加活动检查后置条件
        List<TaskRelAction> actions = taskRelActionMapper.selectExecuteModeByTempCode(templCode);
        if(actions.size() > 0) {
            SubTaskCondition subTaskCondition = new SubTaskCondition();
            subTaskCondition.setSubTaskNum(subTaskNum);
            subTaskCondition.setConditonTriger(CondtionTriger.POST_CONDITION.getCode());
            subTaskCondition.setConditonHandler(ACTIONCHECKHANDLER);
            subTaskCondition.setCreateDate(new Date());
            subTaskConditionMapper.insertSelective(subTaskCondition);
        }
    }

    /**
     * 添加主任务
     * @param
     * @return
     */
    @Override
    public String mainTaskCommonAdd(String taskTypeCode, String areaCode, Integer priority){
        String mainTaskNum = "";
        //创建主任务
        MainTask mainTaskCreate = new MainTask();
        mainTaskNum = CodeBuilder.codeBuilder("M");
        mainTaskCreate.setMainTaskNum(mainTaskNum);
        mainTaskCreate.setCreateDate(new Date());
        mainTaskCreate.setPriority(priority);
        mainTaskCreate.setMainTaskTypeCode(taskTypeCode);
        mainTaskCreate.setAreaCode(areaCode);
        mainTaskCreate.setTaskStatus(MAIN_NOT_ISSUED);
        mainTaskMapper.insertSelective(mainTaskCreate);

        return mainTaskNum;
    }


    /**
     * 通用主任务
     * @param taskType
     * @param
     * @param taskPri
     * @return
     */
    public String createMainTasks(String taskType,String taskPri,String jsonString){
        String mainTaskNum = "";
        MainTask mainTaskCreate = new MainTask();
        mainTaskNum = CodeBuilder.codeBuilder("M");
        mainTaskCreate.setMainTaskNum(mainTaskNum);
        mainTaskCreate.setCreateDate(new Date());
        if (StringUtils.isNotEmpty(taskPri)){
            mainTaskCreate.setPriority(TaskPriorityEnum.getPriorityByCode(taskPri));
        }
        mainTaskCreate.setMainTaskTypeCode(taskType);
        // mainTaskCreate.setAreaCode(areaCode);
        if (StringUtils.isNotEmpty(jsonString)){
            mainTaskCreate.setStaticViaPaths(jsonString);
        }
        mainTaskCreate.setTaskStatus(MAIN_NOT_ISSUED);
        mainTaskMapper.insertSelective(mainTaskCreate);
        return mainTaskNum;
    }

}
