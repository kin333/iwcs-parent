package com.wisdom.controller.control;

import com.wisdom.base.annotation.SystemInterfaceLog;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.control.*;
import com.wisdom.iwcs.service.base.IBaseWbService;
import com.wisdom.iwcs.service.callHik.IEndTaskService;
import com.wisdom.iwcs.service.callHik.IRotatePodService;
import com.wisdom.iwcs.service.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.*;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceName.*;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_OUTER;

/**
 * 控制，如一键结束、旋转等
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/controlManagement")
public class ControlManagementController {
    @Autowired
    private IEndTaskService iEndTaskService;

    @Autowired
    private IRotatePodService IRotatePodService;
    @Autowired
    private ICallOutByPodService ICallOutByPodService;
    @Autowired
    private IInitPodService IInitPodService;
    @Autowired
    private IMovePodService IMovePodService;
    @Autowired
    private IReturnByPodService ireturnByPodService;
    @Autowired
    private IManualUpdatePodLockService iManualUpdatePodLockService;
    @Autowired
    private IBaseWbService iBaseWbService;


    /**
     * 一键结束
     *
     * @param requestDTO
     * @return
     */
    @PostMapping(value = "/endTask")
    @SystemInterfaceLog(methodCode = END_TASK_CODE, methodName = END_TASK_NAME, methodThansfer = SRC_OUTER)
    public Result endTask(@RequestBody EndTaskRequestDTO requestDTO) {
        return iEndTaskService.endTaskByWbCode(requestDTO);
    }

    /**
     * 旋转货架
     *
     * @param requestDTO
     * @return
     */
    @PostMapping(value = "/rotatePod")
    @SystemInterfaceLog(methodCode = ROTATE_POD_CODE, methodName = ROTATE_POD_NAME, methodThansfer = SRC_OUTER)
    public Result rotatePod(@RequestBody RotatePodRequestDTO requestDTO) {
        return IRotatePodService.rotatePod(requestDTO);
    }

    /**
     * 根据指定仓位号呼叫
     *
     * @param requestDTO
     * @return
     */
    @PostMapping(value = "/callOutByBincode")
    @SystemInterfaceLog(methodCode = CALL_BY_BINCODE, methodName = CALL_BY_POD_NAME, methodThansfer = SRC_OUTER)
    public Result callOutByBincode(@RequestBody CallOutByBincodeRequestDTO requestDTO) {
        return ICallOutByPodService.callOutByBincode(requestDTO);
    }

    /**
     * 根据指定货架号呼叫
     *
     * @param requestDTO
     * @return
     */
    @PostMapping(value = "/callOutByPodCode")
    @SystemInterfaceLog(methodCode = CALL_BY_POD_CODE, methodName = CALL_BY_POD_NAME, methodThansfer = SRC_OUTER)
    public Result callOutByPodCode(@RequestBody CallOutByPodCodeRequestDTO requestDTO) {
        return ICallOutByPodService.callOutByPodCode(requestDTO);
    }

    /**
     * 货架初始化
     *
     * @param requestDTO
     * @return
     */
    @PostMapping(value = "/initPod")
    @SystemInterfaceLog(methodCode = INIT_POD_CODE, methodName = INIT_POD_NAME, methodThansfer = SRC_OUTER)
    public Result initPod(@RequestBody InitPodRequestDTO requestDTO) {
        return IInitPodService.initPod(requestDTO);
    }

    /**
     * 移动货架存储区
     *
     * @param requestDTO
     * @return
     */
    @PostMapping(value = "/moveByBincode")
    @SystemInterfaceLog(methodCode = MOVE_POD_CODE, methodName = MOVE_POD_NAME, methodThansfer = SRC_OUTER)
    public Result moveByPod(@RequestBody MoveByPodRequestDTO requestDTO) {
        return IMovePodService.moveByBincode(requestDTO);
    }

    /**
     * 指定货架回库
     * 按BINCODE回库
     *
     * @param requestDTO
     * @return
     */
    @PostMapping(value = "/returnByBincode")
    @SystemInterfaceLog(methodCode = RETURN_BY_BINCODE, methodName = RETURN_BY_BINCODE_NAME, methodThansfer = SRC_OUTER)
    public Result returnByBincode(@RequestBody ReturnByBincodeRequestDTO requestDTO) {
        return ireturnByPodService.returnByBincode(requestDTO);
    }

    /**
     * 指定货架回库
     * 按PODCODE回库
     *
     * @param requestDTO
     * @return
     */
    @PostMapping(value = "/returnByPodCode")
    @SystemInterfaceLog(methodCode = RETURN_BY_POD_CODE, methodName = RETURN_BY_BINCODE_NAME, methodThansfer = SRC_OUTER)
    public Result returnByPodCode(@RequestBody ReturnByPodCodeRequestDTO requestDTO) {
        return ireturnByPodService.returnByPodCode(requestDTO);
    }

    /**
     * 工作台解锁/上锁货架
     *
     * @param requestDTO
     * @return
     */
    @PostMapping(value = "/manualUpdatePodLock")
    @SystemInterfaceLog(methodCode = MANUAL_UPDATE_POD_LOCK_CODE, methodName = MANUAL_UPDATE_POD_LOCK_NAME, methodThansfer = SRC_OUTER)
    public Result manualUpdatePodLock(@RequestBody ManualUpdatePodLockRequestDTO requestDTO) {
        return iManualUpdatePodLockService.manualUpdatePodLock(requestDTO);
    }

    /**
     * 获取空闲点位信息
     *
     * @return
     */
    @GetMapping(value = "/getFreeWbInfo")
    @SystemInterfaceLog(methodCode = GET_FREE_WB_INFO_CODE, methodName = GET_FREE_WB_INFO_NAME, methodThansfer = SRC_OUTER)
    public Result getFreeWbInfo() {
        return iBaseWbService.getFreeWbInfo();
    }
}
