package com.wisdom.iwcs.service.callHik;

import com.wisdom.base.annotation.SystemInterfaceLog;
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
     * @param cancelTaskDTO
     * @return
     */
    @SystemInterfaceLog(methodCode = CANCEL_TASK_CODE, methodName = CANCEL_TASK_NAME, methodThansfer = SRC_IWCS)
    String transferCancelTask(CancelTaskDTO cancelTaskDTO);

    /**
     * 货架与位置绑定、解绑
     * @param
     * @return
     */
    @SystemInterfaceLog(methodCode = Bind_And_Berth_CODE, methodName = Bind_And_Berth_NAME, methodThansfer = SRC_IWCS)
    String transferBindPodAndBerth(BindPodAndBerthDTO bindPodAndBerthDTO);

    /**
     * 超越释放AGV
     * @param genAgvSchedulingTaskDTO
     * @return
     */
    @SystemInterfaceLog(methodCode = FREE_ROBOT, methodName = FREE_ROBOT_NAME, methodThansfer = SRC_IWCS)
    String transferFreeRobot(GenAgvSchedulingTaskDTO genAgvSchedulingTaskDTO);

    /**
     * 自动门 开门到位/开始关门
     * @param
     * @return
     */
    @SystemInterfaceLog(methodCode = NOTIFY_EXCUTE_RESULT_INFO, methodName = NOTIFY_EXCUTE_RESULT_INFO_NAME, methodThansfer = SRC_IWCS)
    String notifyExcuteResultInfo(NotifyExcuteResultInfoDTO notifyExcuteResultInfoDTO);
}
