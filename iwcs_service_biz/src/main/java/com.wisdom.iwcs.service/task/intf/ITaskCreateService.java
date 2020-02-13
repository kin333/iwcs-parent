package com.wisdom.iwcs.service.task.intf;

import com.wisdom.iwcs.domain.upstream.mes.CreateTaskRequest;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;

public interface ITaskCreateService {

    String mainTaskCommonAdd(String taskTypeCode, String areaCode, Integer priority);
    MesResult pToPTaskCreate(CreateTaskRequest createTaskRequest);
}
