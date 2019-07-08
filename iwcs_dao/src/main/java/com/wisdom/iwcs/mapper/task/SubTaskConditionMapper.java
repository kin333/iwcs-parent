package com.wisdom.iwcs.mapper.task;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-06-26 11:44:27.
 */
@Repository
public interface SubTaskConditionMapper extends MyMapperAndIds<SubTaskCondition> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<SubTaskCondition> selectPage(Map map);

    /**
     * 根据子任务编号和前置或后置条件查询
     * @param  subTaskNum 子任务编号
     * @param  conditonTriger 前置或后置条件
     * @return list
     */
    List<SubTaskCondition> selectByTaskNumAndTrigerType(@Param("subTaskNum") String subTaskNum,@Param("conditonTriger") String conditonTriger);

    /**
     * 通过子任务编号更新条件状态
     * @param subTaskNum
     * @param metStatus
     */
    void updateMetStatusBySubTaskNum(@Param("subTaskNum") String subTaskNum,@Param("metStatus") String metStatus);
}
