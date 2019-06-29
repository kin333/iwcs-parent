package com.wisdom.iwcs.service.task.maintask;

import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.dto.MainTaskWithSubTaskInfos;
import com.wisdom.iwcs.domain.task.dto.SubTaskInfo;
import com.wisdom.iwcs.service.task.intf.IMainTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 主任务下发器，负责待下发的主任务的下发执行
 */
@Service
public class MainTaskDispatcher {
    private final Logger logger = LoggerFactory.getLogger(MainTaskDispatcher.class);

    public IMainTaskService mainTaskService;

    /**
     * 完成一次主任务下发检测及下发
     */
    public void dispatchMaintask() {
        //获取所有待下发的任务
        List<MainTaskWithSubTaskInfos> allUnDispatchedTask = mainTaskService.getAllUnDispatchedTask();
        //顺序执行主任务可执行性检查及下发
        allUnDispatchedTask.stream().forEach(t -> {
            //检查主任务第一条子任务是否满足执行条件
            Optional<SubTaskInfo> subTaskOptional = t.getSubTaskInfos().stream().findFirst();
            if (subTaskOptional.isPresent()) {
                SubTask firstSubTask = subTaskOptional.get();
                boolean dispatchable = mainTaskService.subtaskPreConditionMetCheck(firstSubTask);
                if (dispatchable) {
                    logger.debug("主任务{}满足执行条件，开始下发", t.getMainTaskNum());
                } else {
                    logger.debug("主任务{}不满足执行条件，跳过本次下发", t.getMainTaskNum());
                }
            } else {
                throw new BusinessException("主任务未生成有效子任务，请检查任务类型配置" + t.getMainTaskTypeCode());
            }

        });


    }

}
