package com.wisdom.service.control.controlImpl;

import com.google.common.base.Strings;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.ValidFlagEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseBincodeDetail;
import com.wisdom.iwcs.domain.control.ReturnByBincodeRequestDTO;
import com.wisdom.iwcs.domain.control.ReturnByPodCodeRequestDTO;
import com.wisdom.iwcs.domain.hikSync.ReturnPodDTO;
import com.wisdom.iwcs.domain.task.WbAgvTask;
import com.wisdom.iwcs.domain.task.WbTaskDetail;
import com.wisdom.iwcs.mapper.base.BaseBincodeDetailMapper;
import com.wisdom.iwcs.mapper.base.BasePodBincodeMapper;
import com.wisdom.iwcs.mapper.task.WbAgvTaskMapper;
import com.wisdom.iwcs.mapper.task.WbTaskDetailMapper;
import com.wisdom.service.base.ICommonService;
import com.wisdom.service.callHik.IReturnPodService;
import com.wisdom.service.control.IReturnByPodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.wisdom.iwcs.common.utils.DeleteFlagEnum.NOT_DELETED;

/**
 * 按货架回库
 *
 * @Author: cecilia.yang
 * @Date: 2019/2/26 18:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReturnByPodService implements IReturnByPodService {
    private final Logger logger = LoggerFactory.getLogger(ReturnByPodService.class);
    @Autowired
    private ICommonService ICommonService;
    @Autowired
    private BaseBincodeDetailMapper baseBincodeDetailMapper;
    @Autowired
    private IReturnPodService IReturnPodService;
    @Autowired
    private WbAgvTaskMapper wbAgvTaskMapper;
    @Autowired
    private WbTaskDetailMapper wbTaskDetailMapper;
    @Autowired
    private BasePodBincodeMapper basePodBincodeMapper;


    /**
     * 按仓位号回库
     *
     * @param requestDTO
     * @return
     */
    @Override
    public Result returnByBincode(ReturnByBincodeRequestDTO requestDTO) {
        String wbCode = checkReturnPodParamAndReturnWbCode(requestDTO);

        ReturnPodDTO returnPodDTO = new ReturnPodDTO();
        returnPodDTO.setWbCode(wbCode);
        returnPodDTO.setBincode(requestDTO.getReturnBincode());

        return IReturnPodService.returnPodRequest(returnPodDTO);
    }

    /**
     * 按货架号回库
     *
     * @param requestDTO
     * @return
     */
    @Override
    public Result returnByPodCode(ReturnByPodCodeRequestDTO requestDTO) {
        String randomBincode = basePodBincodeMapper.selectRandomBincodeByPodCodeAndValidFlagAndDeletedFlag(requestDTO.getReturnPodCode(), ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
        ReturnByBincodeRequestDTO returnByBincodeRequestDTO = new ReturnByBincodeRequestDTO();
        returnByBincodeRequestDTO.setAreaCode(requestDTO.getAreaCode());
        returnByBincodeRequestDTO.setReturnBincode(randomBincode);
        returnByBincodeRequestDTO.setUserId(requestDTO.getUserId());
        returnByBincodeRequestDTO.setWbCode(requestDTO.getWbCode());
        String returnWbCode = checkReturnPodParamAndReturnWbCode(returnByBincodeRequestDTO);

        ReturnPodDTO returnPodDTO = new ReturnPodDTO();
        returnPodDTO.setWbCode(returnWbCode);
        returnPodDTO.setBincode(randomBincode);

        return IReturnPodService.returnPodRequest(returnPodDTO);
    }

    private String checkReturnPodParamAndReturnWbCode(ReturnByBincodeRequestDTO requestDTO) {
        String requestBincode = requestDTO.getReturnBincode();
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(requestDTO.getUserId()), "未指定操作人");
//        Preconditions.checkBusinessError(Strings.isNullOrEmpty(requestDTO.getAreaCode()),"未指定库区信息");
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(requestBincode), "未指定回库bincode");
        String requestPodCode = requestDTO.getReturnBincode().substring(0, 6);
        String wbCode = ICommonService.returnWbCodeByWbCodeOrByTaskPodCodeAndTaskType(requestDTO.getWbCode(), requestPodCode, null);
//        String requestAreaCode = requestDTO.getAreaCode();
        boolean wbCodeRequired = ICommonService.IsOpenWbCode();
        boolean lackWbCode = wbCodeRequired && Strings.isNullOrEmpty(requestDTO.getWbCode());
        Preconditions.checkBusinessError(lackWbCode, "未指定点位信息");

        BaseBincodeDetail baseBincodeDetail = baseBincodeDetailMapper.selectByBincodeAndValidFlagAndDeleteFlag(requestBincode, ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
        Preconditions.checkBusinessError(baseBincodeDetail == null, "仓位:" + requestBincode + "未定义到有效信息，请确认");
//        boolean isNotSameAreaCode = !baseBincodeDetail.getAreaCode().equals(requestAreaCode);
//        Preconditions.checkBusinessError(isNotSameAreaCode ,"仓位:"+requestBincode+"不是返回库区中的货架，不允许跨库区操作");

        WbAgvTask wbAgvTask = wbAgvTaskMapper.selectUnCompletedTaskByWbCode(requestDTO.getWbCode());
        Preconditions.checkBusinessError(wbAgvTask == null, "点位：" + requestDTO.getWbCode() + "无货架任务，请确认");
        List<WbTaskDetail> wbTaskDetails = wbTaskDetailMapper.selectUnCompletedTaskByWbTaskNo(wbAgvTask.getTaskNo());
        Preconditions.checkBusinessError(wbTaskDetails.size() == 0, "点位：" + requestDTO.getWbCode() + "无货架任务，请确认");
        List<String> taskPods = wbTaskDetails.stream().map(WbTaskDetail::getPodCode).collect(Collectors.toList());
        Preconditions.checkBusinessError(!taskPods.contains(requestPodCode), "点位：" + requestDTO.getWbCode() + "无货架:" + requestPodCode + "任务，请确认");

        return wbCode;
    }


}
