package com.wisdom.iwcs.service.task.intf;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.task.dto.TaskRelConditionDTO;

import java.util.List;

public interface ITaskRelConditionsService {
    int insert(TaskRelConditionDTO record);

    int insertBatch(List<TaskRelConditionDTO> records);

    TaskRelConditionDTO selectByPrimaryKey(Integer id);

    List<TaskRelConditionDTO> selectSelective(TaskRelConditionDTO record);

    int updateByPrimaryKey(TaskRelConditionDTO record);

    int updateByPrimaryKeySelective(TaskRelConditionDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<TaskRelConditionDTO> selectPage(GridPageRequest gridPageRequest);
}
