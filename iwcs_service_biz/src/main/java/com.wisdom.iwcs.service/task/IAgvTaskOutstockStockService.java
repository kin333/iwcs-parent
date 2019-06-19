package com.wisdom.iwcs.service.task;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.task.dto.AgvTaskOutstockStockDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/19 15:55
 */
public interface IAgvTaskOutstockStockService {
    int insert(AgvTaskOutstockStockDTO record);

    int insertBatch(List<AgvTaskOutstockStockDTO> records);

    AgvTaskOutstockStockDTO selectByPrimaryKey(Integer id);

    List<AgvTaskOutstockStockDTO> selectSelective(AgvTaskOutstockStockDTO record);

    int updateByPrimaryKey(AgvTaskOutstockStockDTO record);

    int updateByPrimaryKeySelective(AgvTaskOutstockStockDTO record);

    int deleteByPrimaryKey(Integer id);

    GridReturnData<AgvTaskOutstockStockDTO> selectPage(GridPageRequest gridPageRequest);

    List<AgvTaskOutstockStockDTO> selectAgvTaskOutStockByTaskNo(String taskNo);
}
