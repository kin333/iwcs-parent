package com.wisdom.iwcs.mapper.task;


import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.SubTask;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-06-20 11:44:31.
 * DeleteLogicMapper<TsSubTask>,
 */
@Repository
public interface SubTaskMapper extends MyMapperAndIds<SubTask> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<SubTask> selectPage(Map map);

    List<SubTask> selectByMainTaskNum(String mainTaskNum);

    /**
     * 根据子任务编号查询数据
     * @param subTaskNum
     * @return
     */
    SubTask selectBySubTaskNum(String subTaskNum);
}