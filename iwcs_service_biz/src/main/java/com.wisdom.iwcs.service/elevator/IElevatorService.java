package com.wisdom.iwcs.service.elevator;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.elevator.dto.ElevatorDTO;

import java.util.List;

public interface IElevatorService {
    int insert(ElevatorDTO record);

    int insertBatch(List<ElevatorDTO> records);

    ElevatorDTO selectByPrimaryKey(Integer id);

    List<ElevatorDTO> selectSelective(ElevatorDTO record);

    int updateByPrimaryKey(ElevatorDTO record);

    int updateByPrimaryKeySelective(ElevatorDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<ElevatorDTO> selectPage(GridPageRequest gridPageRequest);
}
