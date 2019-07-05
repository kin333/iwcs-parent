package com.wisdom.iwcs.mapper.task;


import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.MainTask;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-06-25 14:18:25.
 */
@Repository
public interface MainTaskMapper extends MyMapperAndIds<MainTask> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<MainTask> selectPage(Map map);

    /**
     * 根据主任务编号查询
     */
    MainTask selectByMainTaskNum(String mainTaskNum);

    /**
     * 根据任务状态查询主任务列表
     */
    List<MainTask> selectByTaskStatus(String taskStatus);
}