package com.wisdom.iwcs.service.task.intf;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.TaskCreateRequest;

public interface ITaskCreateService {
    Result creatTask(TaskCreateRequest taskCreateRequest);

    void subTaskConditionCommonAdd(String mainTaskTypeCode, String subTaskTypeCode, String subTaskNum);
}
