package com.wisdom.iwcs.service.task.impl;

import com.google.common.base.Strings;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.MainTaskType;
import com.wisdom.iwcs.domain.task.PlAutoWbCallPodRequest;
import com.wisdom.iwcs.domain.task.TaskCreateRequest;
import com.wisdom.iwcs.mapper.task.MainTaskTypeMapper;
import com.wisdom.iwcs.service.task.intf.IPlAutoWbCallPodService;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.*;

/**
 * 任务创建
 * @Author george
 * @Date 2019/7/3 9:14
 */
@Service
public class TaskCreateService implements ITaskCreateService {
    private final Logger logger = LoggerFactory.getLogger(TaskCreateService.class);

    @Autowired
    private MainTaskTypeMapper mainTaskTypeMapper;
    @Autowired
    private IPlAutoWbCallPodService iPlAutoWbCallPodService;

    /**
     * 创建任务
     * @param  taskCreateRequest
     * @return
     */
    @Override
    public Result creatTask(TaskCreateRequest taskCreateRequest){

        //校验参数
        checkCreateTaskConfirmParam(taskCreateRequest);

        //根据任务编号调相应service
        MainTaskType mainTaskType = mainTaskTypeMapper.selectByMainTaskTypeCode(taskCreateRequest.getTaskTypeCode());
        String taskTypeCode = mainTaskType.getMainTaskTypeCode();
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(taskTypeCode), "未配置任务类型编号");
        switch (taskTypeCode){
            case PLAUTOWBCALLPOD:
                PlAutoWbCallPodRequest plAutoWbCallPodRequest = new PlAutoWbCallPodRequest();
                plAutoWbCallPodRequest.setPriority(taskCreateRequest.getPriority());
                plAutoWbCallPodRequest.setTaskTypeCode(taskTypeCode);
                plAutoWbCallPodRequest.setWbCode(taskCreateRequest.getWbCode());
                iPlAutoWbCallPodService.plAutoWbCallPod(plAutoWbCallPodRequest);
                break;
            case PLBUFSUPPLY:

                break;
            case PLTOAGING:
                break;
            case AGINGTOQUAINSP:
                break;
            default:
                logger.error("wrong task Code:{}",taskTypeCode);
        }
        return new Result();
    }

    private void checkCreateTaskConfirmParam(TaskCreateRequest request){
        String taskCode = request.getTaskTypeCode();
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(taskCode), "缺少任务类型编号");
    }

}
