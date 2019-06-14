package com.wisdom.iwcs.mapper.task;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.AgvTaskDetailOutstockProcess;
import com.wisdom.iwcs.domain.task.dto.AgvTaskDetailOutstockProcessDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-03-01 09:42:43.
 */
@Repository
public interface AgvTaskDetailOutstockProcessMapper extends MyMapperAndIds<AgvTaskDetailOutstockProcess> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<AgvTaskDetailOutstockProcess> selectPage(Map map);

    /**
     * 根据任务编号、货架号、库存唯一编号获取出库进度信息
     *
     * @param taskNo
     * @param binCode
     * @param stkCode
     * @return
     */
    AgvTaskDetailOutstockProcess selectOutstockProcessByTaskNoAndBinCodeAndStkCode(@Param("taskNo") String taskNo, @Param("binCode") String binCode, @Param("stkCode") String stkCode);

    List<AgvTaskDetailOutstockProcess> selectUnCompletedOutStockProcessByTaskNo(String taskNo);

    List<AgvTaskDetailOutstockProcess> selectAgvDetailProcess(List<AgvTaskDetailOutstockProcessDTO> list);

}
