package com.wisdom.service.task;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.task.dto.AgvTaskDetailOutstockProcessDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/19 15:54
 */
public interface IAgvTaskDetailOutstockProcessService {
    int insert(AgvTaskDetailOutstockProcessDTO record);

    int insertBatch(List<AgvTaskDetailOutstockProcessDTO> records);

    AgvTaskDetailOutstockProcessDTO selectByPrimaryKey(Integer id);

    List<AgvTaskDetailOutstockProcessDTO> selectSelective(AgvTaskDetailOutstockProcessDTO record);

    int updateByPrimaryKey(AgvTaskDetailOutstockProcessDTO record);

    int updateByPrimaryKeySelective(AgvTaskDetailOutstockProcessDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<AgvTaskDetailOutstockProcessDTO> selectPage(GridPageRequest gridPageRequest);

    List<AgvTaskDetailOutstockProcessDTO> selectAgvDetailProcess(List<AgvTaskDetailOutstockProcessDTO> agvTaskDetailOutstockProcessDTOList);
}
