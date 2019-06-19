package com.wisdom.iwcs.service.control.controlImpl;

import com.google.common.base.Strings;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.ValidFlagEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.BaseWb;
import com.wisdom.iwcs.domain.control.CallOutByBincodeRequestDTO;
import com.wisdom.iwcs.domain.control.CallOutByPodCodeRequestDTO;
import com.wisdom.iwcs.domain.hikSync.GetOutPodDTO;
import com.wisdom.iwcs.mapper.base.BasePodBincodeMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.base.BaseWbMapper;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.callHik.IGetOutPodService;
import com.wisdom.iwcs.service.control.ICallOutByPodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.wisdom.iwcs.common.utils.DeleteFlagEnum.NOT_DELETED;
import static com.wisdom.iwcs.common.utils.podUtils.PodTaskLockEnum.CALL_TASK;
import static com.wisdom.iwcs.common.utils.podUtils.PodTaskLockEnum.NO_TASK;

/**
 * 按货架呼叫
 *
 * @Author: cecilia.yang
 * @Date: 2019/2/26 18:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CallOutByPodService implements ICallOutByPodService {
    private final Logger logger = LoggerFactory.getLogger(CallOutByPodService.class);
    @Autowired
    private BaseWbMapper baseWbMapper;
    @Autowired
    private ICommonService ICommonService;
    @Autowired
    private IGetOutPodService IGetOutPodService;
    @Autowired
    private BasePodDetailMapper basePodDetailMapper;
    @Autowired
    private BasePodBincodeMapper basePodBincodeMapper;

    /**
     * 按货架呼叫
     *
     * @param requestDTO
     * @return
     */
    @Override
    public Result callOutByBincode(CallOutByBincodeRequestDTO requestDTO) {
        checkCallOutByPodParam(requestDTO);
        GetOutPodDTO getOutPodDTO = new GetOutPodDTO();
        getOutPodDTO.setWbCode(requestDTO.getWbCode());
        getOutPodDTO.setBincodes(requestDTO.getCallBincodes());
        getOutPodDTO.setTaskType(CALL_TASK.getType());
        getOutPodDTO.setSrcUserCode(requestDTO.getUserId());
        getOutPodDTO.setSrcReqCode(requestDTO.getSrcReqCode());
        return IGetOutPodService.getOutPod(getOutPodDTO);
    }

    /**
     * 根据指定货架号呼叫
     *
     * @param requestDTO
     * @return
     */
    @Override
    public Result callOutByPodCode(CallOutByPodCodeRequestDTO requestDTO) {
        boolean lackCallPodCode = requestDTO.getCallPodCodes() == null || requestDTO.getCallPodCodes().size() == 0;
        Preconditions.checkBusinessError(lackCallPodCode, "缺少呼叫货架号");
        List<String> randomBincodeList = basePodBincodeMapper.selectRandomBincodeListByPodCodeListAndValidFlagAndDeleteFlag(requestDTO.getCallPodCodes(), ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
        CallOutByBincodeRequestDTO callOutByBincodeRequestDTO = new CallOutByBincodeRequestDTO();
        callOutByBincodeRequestDTO.setAreaCode(requestDTO.getAreaCode());
        callOutByBincodeRequestDTO.setCallBincodes(randomBincodeList);
        callOutByBincodeRequestDTO.setUserId(requestDTO.getUserId());
        callOutByBincodeRequestDTO.setWbCode(requestDTO.getWbCode());
        checkCallOutByPodParam(callOutByBincodeRequestDTO);

        //调用货架出库
        GetOutPodDTO getOutPodDTO = new GetOutPodDTO();
        getOutPodDTO.setWbCode(requestDTO.getWbCode());
        getOutPodDTO.setBincodes(randomBincodeList);
        getOutPodDTO.setTaskType(CALL_TASK.getType());
        getOutPodDTO.setSrcUserCode(requestDTO.getUserId());
        getOutPodDTO.setSrcReqCode(requestDTO.getSrcReqCode());
        return IGetOutPodService.getOutPod(getOutPodDTO);
    }

    private void checkCallOutByPodParam(CallOutByBincodeRequestDTO requestDTO) {
//        Preconditions.checkBusinessError(Strings.isNullOrEmpty(requestDTO.getAreaCode()),"缺少库区信息");
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(requestDTO.getWbCode()), "缺少呼叫点位信息");
        BaseWb baseWb = baseWbMapper.selectByWbCodeAndValidFlagAndDeleteFlag(requestDTO.getWbCode(), ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());


//        boolean isNotSameAreaCode =  !baseWb.getAreaCode().equals(requestDTO.getAreaCode());
//        Preconditions.checkBusinessError(isNotSameAreaCode,"出库点位:"+requestDTO.getWbCode()+"与选择的出库库区不同，不允许跨库区呼叫！");

        boolean isHaveMutexTask = ICommonService.checkWbCodeIfHaveMutexWbcodeTask(requestDTO.getWbCode());
        Preconditions.checkBusinessError(isHaveMutexTask, "出库点位:" + requestDTO.getWbCode() + "互斥工作台存在点位任务，请等待或更换工作台");

        boolean missingCallBincodes = requestDTO.getCallBincodes() == null || requestDTO.getCallBincodes().size() == 0;
        Preconditions.checkBusinessError(missingCallBincodes, "缺少呼叫货架信息");

        requestDTO.getCallBincodes().stream().forEach(bincode -> {
            String podCode = bincode.substring(0, 6);
            BasePodDetail basePodDetail = basePodDetailMapper.selectByPodCodeAndValidFlagAndDeleteFlag(podCode, ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
            Preconditions.checkBusinessError(basePodDetail == null, "仓位号:" + bincode + "在系统中无有效信息，请确认");

//            boolean bincodeIsNotSameAreaCode = !basePodDetail.getAreaCode().equals(requestDTO.getAreaCode());
//            Preconditions.checkBusinessError(bincodeIsNotSameAreaCode,"仓位号:"+bincode+"所在库区与选择的库区不同，不允许跨库区呼叫！");

            boolean isBusy = !basePodDetail.getLockStat().equals(NO_TASK.getTaskValue());
            Preconditions.checkBusinessError(isBusy, "货架:" + podCode + "有其他任务，不允许指定呼叫！");

        });

    }


}
