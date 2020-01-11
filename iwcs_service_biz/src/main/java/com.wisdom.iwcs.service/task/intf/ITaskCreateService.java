package com.wisdom.iwcs.service.task.intf;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.TaskCreateRequest;
import com.wisdom.iwcs.domain.upstream.mes.AgvHandlingTaskCreateRequest;
import com.wisdom.iwcs.domain.upstream.mes.CreateTaskRequest;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;

public interface ITaskCreateService {
    Result creatTask(TaskCreateRequest taskCreateRequest);
    MesResult pToPHandlingTask(CreateTaskRequest createTaskRequest,String reqCode);


    String mainTaskCommonAdd(String taskTypeCode, String areaCode, Integer priority);

    void subTaskConditionCommonAdd(String mainTaskTypeCode, String subTaskTypeCode, String subTaskNum);

    MesResult agvHandlingTaskCreate(AgvHandlingTaskCreateRequest agvHandlingTaskCreateRequest, String reqCode);

    MesResult generalInterface(CreateTaskRequest createTaskRequest,String reqCode);
}
