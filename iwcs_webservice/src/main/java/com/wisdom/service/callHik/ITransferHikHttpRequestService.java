package com.wisdom.service.callHik;

import com.wisdom.config.SystemInterfaceLog;
import com.wisdom.iwcs.domain.TPSRequest.ReturnPodRequestDTO;
import com.wisdom.iwcs.domain.hikSync.*;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.*;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceName.*;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_IWCS;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:09
 */
public interface ITransferHikHttpRequestService {
    @SystemInterfaceLog(methodCode = END_TASK_CODE, methodName = END_TASK_NAME, methodThansfer = SRC_IWCS)
    String transferHikEndTask(EndTaskByTpsDTO endTaskDataDTO);

    @SystemInterfaceLog(methodCode = OUT_POD_CODE, methodName = OUT_POD_NAME, methodThansfer = SRC_IWCS)
    String transferHikGetOutPod(OutPodRequestDTO outPodRequestDTO);

    @SystemInterfaceLog(methodCode = RETURN_POD_CODE, methodName = RETURN_POD_NAME, methodThansfer = SRC_IWCS)
    String transferHikReturnPod(ReturnPodRequestDTO returnPodRequestDTO);

    @SystemInterfaceLog(methodCode = ROTATE_POD_CODE, methodName = ROTATE_POD_NAME, methodThansfer = SRC_IWCS)
    String transferHikRotatePod(RotatePodByTpsDTO rotatePodByTpsDTO);

    @SystemInterfaceLog(methodCode = GEN_MOVE_TASK_BY_POD_CODE, methodName = GEN_MOVE_TASK_BY_POD_NAME, methodThansfer = SRC_IWCS)
    String transferHikGenMoveTaskByPodCode(PodChangeStorageAreaByTpsDTO podChangeStorageAreaByTpsDTO);

    /**
     * 生成任务单
     *
     * @param genAgvSchedulingTaskDTO
     * @return
     */
    @SystemInterfaceLog(methodCode = GEN_AGV_SCHEDULING_TASK_CODE, methodName = GEN_AGV_SCHEDULING_TASK_NAME, methodThansfer = SRC_IWCS)
    String transferGenAgvSchedulingTask(GenAgvSchedulingTaskDTO genAgvSchedulingTaskDTO);

    /**
     * 继续执行任务
     *
     * @param continueTaskDTo
     * @return
     */
    @SystemInterfaceLog(methodCode = CONTINUE_TASK_CODE, methodName = CONTINUE_TASK_NAME, methodThansfer = SRC_IWCS)
    String transferContinueTask(ContinueTaskDTo continueTaskDTo);

    /**
     * 取消任务
     *
     * @param continueTaskDTo
     * @return
     */
    @SystemInterfaceLog(methodCode = CANCEL_TASK_CODE, methodName = CANCEL_TASK_NAME, methodThansfer = SRC_IWCS)
    String transferCancelTask(ContinueTaskDTo continueTaskDTo);
}
