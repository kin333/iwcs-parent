package com.wisdom.iwcs.mapper.task;

import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.TaskRelAction;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-09-09 10:05:30.
 */
@Repository
public interface TaskRelActionMapper extends MyMapperAndIds<TaskRelAction> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<TaskRelAction> selectPage(Map map);

    List<TaskRelAction> selectByTempCodeAndNode(@Param("tempCode") String tempCode,@Param("nodeCode") String nodeCode);

    TaskRelAction selectByActionCode(String actionCode);
}