package com.wisdom.iwcs.service.elevator;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.elevator.dto.ConnectionPointDTO;

import java.util.List;

public interface IConnectionPointService {
    int insert(ConnectionPointDTO record);

    int insertBatch(List<ConnectionPointDTO> records);

    ConnectionPointDTO selectByPrimaryKey(Integer id);

    List<ConnectionPointDTO> selectSelective(ConnectionPointDTO record);

    int updateByPrimaryKey(ConnectionPointDTO record);

    int updateByPrimaryKeySelective(ConnectionPointDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<ConnectionPointDTO> selectPage(GridPageRequest gridPageRequest);
}
