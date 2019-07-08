package com.wisdom.iwcs.service.log;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.log.dto.TaskOperationLogDTO;

import java.util.List;

public interface ITaskLogService {

    int insert(TaskOperationLogDTO record);

    int insertBatch(List<TaskOperationLogDTO> records);

    TaskOperationLogDTO selectByPrimaryKey(Integer id);

    List<TaskOperationLogDTO> selectSelective(TaskOperationLogDTO record);

    int updateByPrimaryKey(TaskOperationLogDTO record);

    int updateByPrimaryKeySelective(TaskOperationLogDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    int deleteMoreLogic(List<String> ids);

    GridReturnData<TaskOperationLogDTO> selectPage(GridPageRequest gridPageRequest);
}
