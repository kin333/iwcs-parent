package com.wisdom.iwcs.mapper.task;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.SubTaskAction;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-09-09 10:04:41.
 */
@Repository
public interface SubTaskActionMapper extends MyMapperAndIds<SubTaskAction> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<SubTaskAction> selectPage(Map map);

    SubTaskAction selectByActionCode(@Param("actionCode") String actionCode,@Param("subTaskNum") String subTaskNum);

    List<Long> selectIdNoSend();
}