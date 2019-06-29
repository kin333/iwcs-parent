package com.wisdom.iwcs.service.task.intf;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.task.dto.TaskRelConditionsDTO;

import java.util.List;

public interface ITaskRelConditionsService {
    int insert(TaskRelConditionsDTO record);

    int insertBatch(List<TaskRelConditionsDTO> records);

    TaskRelConditionsDTO selectByPrimaryKey(Integer id);

    List<TaskRelConditionsDTO> selectSelective(TaskRelConditionsDTO record);

    int updateByPrimaryKey(TaskRelConditionsDTO record);

    int updateByPrimaryKeySelective(TaskRelConditionsDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<TaskRelConditionsDTO> selectPage(GridPageRequest gridPageRequest);
}
