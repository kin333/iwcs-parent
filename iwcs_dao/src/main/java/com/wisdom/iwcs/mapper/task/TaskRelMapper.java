package com.wisdom.iwcs.mapper.task;


import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.TaskModal;
import com.wisdom.iwcs.domain.task.TaskRel;
import com.wisdom.iwcs.domain.task.TaskRelSubMain;
import com.wisdom.iwcs.domain.task.dto.TaskRelDTO;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-06-20 11:50:19.
 * DeleteLogicMapper<TaskRel>,
 */
@Repository
public interface TaskRelMapper extends MyMapperAndIds<TaskRel> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<TaskRel> selectPage(Map map);

    List<TaskRel> selectByMainTaskType(String mainTaskTypeCode);

    /**
     * 根据模板编号搜索控制
     *
     * @param templCode
     * @return
     */
    TaskRel selectByTemplCode(String templCode);

    List<TaskRel> selectSubCodeByTemplCode(@Param("templCodeList") List<String> templCodeList);

    List<TaskRel> selectPageByGroup();

    List<TaskRel> selectByMainCode(String mainCode);

    List<TaskRel> selectBySubCode(TaskModal taskModal);

    TaskRel selectDataByTemplCode(TaskRel templCode);

    List<TaskRelSubMain> selectSubMainByMainCode(String mainCode);

    Integer updateTaskByTemplCode(TaskRelDTO taskRel);

    Integer deleteByTemplCode(Integer id);

    List<TaskRelSubMain> selectSubTaskTypeByCode(TaskRel taskRel);

    Integer deleteByTemplCodes(@Param("templCodeList") List<String> templCodeList);
    Integer deleteByMainCode(@Param("mainTaskCode") String mainTaskCode);

    int selectByMainTaskTypeAndStaticPod(String mainTaskType);

    List<TaskRel> selectByMainTaskTypeAndStaticPoint(String mainTaskType);
}