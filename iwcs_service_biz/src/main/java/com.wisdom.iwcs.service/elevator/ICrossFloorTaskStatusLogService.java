package com.wisdom.iwcs.service.elevator;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.elevator.dto.CrossFloorTaskStatusLogDTO;

import java.util.List;

public interface ICrossFloorTaskStatusLogService {
    int insert(CrossFloorTaskStatusLogDTO record);

    int insertBatch(List<CrossFloorTaskStatusLogDTO> records);

    CrossFloorTaskStatusLogDTO selectByPrimaryKey(Integer id);

    List<CrossFloorTaskStatusLogDTO> selectSelective(CrossFloorTaskStatusLogDTO record);

    int updateByPrimaryKey(CrossFloorTaskStatusLogDTO record);

    int updateByPrimaryKeySelective(CrossFloorTaskStatusLogDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<CrossFloorTaskStatusLogDTO> selectPage(GridPageRequest gridPageRequest);
}
