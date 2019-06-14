package com.wisdom.service.control.controlImpl;

import com.google.common.base.Strings;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.ValidFlagEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.control.ManualUpdatePodLockRequestDTO;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.service.base.ICommonService;
import com.wisdom.service.control.IManualUpdatePodLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static com.wisdom.iwcs.common.utils.DeleteFlagEnum.NOT_DELETED;
import static com.wisdom.iwcs.common.utils.podUtils.PodTaskLockEnum.HAND_TASK;
import static com.wisdom.iwcs.common.utils.podUtils.PodTaskLockEnum.NO_TASK;
import static com.wisdom.iwcs.common.utils.taskUtils.AgvTaskConstants.AgvTaskStatusConstants.TASK_CREATED;

/**
 * 按货架回库
 *
 * @Author: cecilia.yang
 * @Date: 2019/2/26 18:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ManualUpdatePodLockService implements IManualUpdatePodLockService {
    @Autowired
    private BasePodDetailMapper basePodDetailMapper;
    @Autowired
    private ICommonService iCommonService;


    @Override
    public Result manualUpdatePodLock(ManualUpdatePodLockRequestDTO requestDTO) {
        String podCode = requestDTO.getPodCode();
        checkParamBusinessValid(requestDTO);
        //调用解锁/上锁
        iCommonService.updatePodLockByPodCodes(Arrays.asList(podCode), HAND_TASK.getType(), requestDTO.getOperationType());
        return new Result();
    }

    private void checkParamBusinessValid(ManualUpdatePodLockRequestDTO requestDTO) {
        String requestPodCode = requestDTO.getPodCode();
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(requestPodCode), "缺少货架号");
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(requestDTO.getOperationType()), "缺少操作类型");
        BasePodDetail basePodDetail = basePodDetailMapper.selectByPodCodeAndValidFlagAndDeleteFlag(requestPodCode, ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
        Preconditions.checkBusinessError(basePodDetail == null, "货架号" + requestPodCode + "未找到对应信息，请确认");
        boolean podIsInOtherTask = !basePodDetail.getLockStat().equals(NO_TASK.getTaskValue()) && requestDTO.getOperationType().equals(TASK_CREATED);
        Preconditions.checkBusinessError(podIsInOtherTask, "货架号" + requestPodCode + "正在执行其他任务，请确认");


    }
}
