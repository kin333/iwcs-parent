package com.wisdom.iwcs.mapper.task;


import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.outstock.dto.OutstockCalPodParamDTO;
import com.wisdom.iwcs.domain.task.AgvTaskOutstockStock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-03-01 09:43:23.
 */
@Repository
public interface AgvTaskOutstockStockMapper extends MyMapperAndIds<AgvTaskOutstockStock> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<AgvTaskOutstockStock> selectPage(Map map);

    /**
     * 获取出库任务存在缺失数量的任务库存信息
     *
     * @return
     */
    List<AgvTaskOutstockStock> selectMissingOutstockStockInfoOrderByCreatedTimeAsc();

    /**
     * 根据具体出库任务获取缺失数量的任务库存信息
     *
     * @param taskNo
     * @return
     */
    List<AgvTaskOutstockStock> selectMissingOutstockStockInfoByTaskNo(String taskNo);

    AgvTaskOutstockStock selectByOutstockCalPodParam(OutstockCalPodParamDTO outstockCalPodParamDTO);

    List<AgvTaskOutstockStock> selectAgvTaskOutStockByTaskNo(String taskNo);

}
