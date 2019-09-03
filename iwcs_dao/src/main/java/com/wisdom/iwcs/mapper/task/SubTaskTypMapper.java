package com.wisdom.iwcs.mapper.task;


import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.SubTaskTyp;
import com.wisdom.iwcs.domain.task.TaskRel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-06-20 11:46:52.
 * DeleteLogicMapper<SubTaskTyp>,
 */
@Repository
public interface SubTaskTypMapper extends  MyMapperAndIds<SubTaskTyp> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<SubTaskTyp> selectPage(Map map);

    /**
     * 根据子任务类型编号查询
     * @param subTaskTypCode
     * @return
     */
    SubTaskTyp selectByTypeCode(String subTaskTypCode);

    List<SubTaskTyp> selectByMainCode(@Param("subTaskCode") List<String> subTaskCode);
}