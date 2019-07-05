package com.wisdom.iwcs.service.task.intf;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.dto.MainTaskDTO;

import java.util.List;

public interface IMainTaskService {
    int insert(MainTaskDTO record);

    int insertBatch(List<MainTaskDTO> records);

    MainTaskDTO selectByPrimaryKey(Integer id);

    List<MainTaskDTO> selectSelective(MainTaskDTO record);

    int updateByPrimaryKey(MainTaskDTO record);

    int updateByPrimaryKeySelective(MainTaskDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<MainTaskDTO> selectPage(GridPageRequest gridPageRequest);

    public List<MainTask> getAllUnDispatchedTask();

    /**
     * 检查一个子任务前置条件是否满足
     *
     * @param firstSubTask
     * @return
     */
    boolean subtaskPreConditionMetCheck(SubTask firstSubTask);

    /**
     * 尝试结束主任务：检查主任务是否符合结束状态，符合则更新任务单状态
     *
     * @param mainTaskNum
     * @return
     */
    boolean endMainTask(String mainTaskNum);
}
