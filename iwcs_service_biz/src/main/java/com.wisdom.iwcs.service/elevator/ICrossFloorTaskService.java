package com.wisdom.iwcs.service.elevator;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.elevator.dto.CrossFloorTaskDTO;

import java.util.List;

public interface ICrossFloorTaskService {
    int insert(CrossFloorTaskDTO record);

    int insertBatch(List<CrossFloorTaskDTO> records);

    CrossFloorTaskDTO selectByPrimaryKey(Integer id);

    List<CrossFloorTaskDTO> selectSelective(CrossFloorTaskDTO record);

    int updateByPrimaryKey(CrossFloorTaskDTO record);

    int updateByPrimaryKeySelective(CrossFloorTaskDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    int deleteMoreLogic(List<String> ids);

    GridReturnData<CrossFloorTaskDTO> selectPage(GridPageRequest gridPageRequest);
}
