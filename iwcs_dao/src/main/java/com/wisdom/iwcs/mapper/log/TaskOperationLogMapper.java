package com.wisdom.iwcs.mapper.log;

import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.log.TaskOperationLog;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-07-16 09:10:33.
 */
@Repository
public interface TaskOperationLogMapper extends DeleteLogicMapper<TaskOperationLog>, MyMapperAndIds<TaskOperationLog> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<TaskOperationLog> selectPage(Map map);

    /**
     * 根据子单号更新日志
     * @param taskOperationLog
     * @return
     */
    int updateBySubTaskNum(TaskOperationLog taskOperationLog);

    /**
     * 根据子单号查询日志数量
     * @param taskOperationLog
     * @return
     */
    int selectLogCount(TaskOperationLog taskOperationLog);
}