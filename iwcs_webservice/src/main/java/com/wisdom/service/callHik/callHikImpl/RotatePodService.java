package com.wisdom.service.callHik.callHikImpl;

import com.google.common.base.Strings;
import com.wisdom.config.ApplicationProperties;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.ValidFlagEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseBincodeDetail;
import com.wisdom.iwcs.domain.base.BaseWb;
import com.wisdom.iwcs.domain.control.RotatePodRequestDTO;
import com.wisdom.iwcs.domain.hikSync.RotatePodByTpsDTO;
import com.wisdom.iwcs.domain.system.Dictionary;
import com.wisdom.iwcs.domain.task.WbAgvTask;
import com.wisdom.iwcs.domain.task.WbTaskDetail;
import com.wisdom.iwcs.mapper.base.BaseBincodeDetailMapper;
import com.wisdom.iwcs.mapper.base.BaseWbMapper;
import com.wisdom.iwcs.mapper.system.DictionaryMapper;
import com.wisdom.iwcs.mapper.task.WbAgvTaskMapper;
import com.wisdom.iwcs.mapper.task.WbTaskDetailMapper;
import com.wisdom.service.base.ICommonService;
import com.wisdom.service.callHik.IRotatePodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.wisdom.iwcs.common.utils.DeleteFlagEnum.NOT_DELETED;
import static com.wisdom.iwcs.common.utils.DictionaryConstants.DictionaryTypeConstants.HIK_PARAM;

/**
 * 旋转货架
 *
 * @Author: cecilia.yang
 * @Date: 2019/2/26 18:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RotatePodService implements IRotatePodService {
    private final Logger logger = LoggerFactory.getLogger(RotatePodService.class);

    @Autowired
    private ICommonService ICommonService;
    @Autowired
    private BaseWbMapper baseWbMapper;
    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Autowired
    private com.wisdom.service.callHik.ITransferHikHttpRequestService ITransferHikHttpRequestService;
    @Autowired
    private ApplicationProperties applicationProperties;
    @Autowired
    private BaseBincodeDetailMapper baseBincodeDetailMapper;
    @Autowired
    private WbAgvTaskMapper wbAgvTaskMapper;
    @Autowired
    private WbTaskDetailMapper wbTaskDetailMapper;

    /**
     * 旋转货架
     *
     * @param requestDTO
     * @return
     */
    @Override
    public Result rotatePod(RotatePodRequestDTO requestDTO) {
        BaseWb baseWb = checkRotatePodParamAndReturnBaseWb(requestDTO);
        RotatePodByTpsDTO rotatePodByTpsDTO = new RotatePodByTpsDTO();
        List<Dictionary> hikParam = dictionaryMapper.selectByDictType(HIK_PARAM);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String reqTime = formatter.format(new Date());
        rotatePodByTpsDTO.setReqCode(String.valueOf(System.currentTimeMillis()));
        rotatePodByTpsDTO.setClientCode(applicationProperties.getHikParam().getClientCode());
        rotatePodByTpsDTO.setTokenCode(applicationProperties.getHikParam().getTokenCode());
        rotatePodByTpsDTO.setReqTime(reqTime);
        rotatePodByTpsDTO.setWbCode(baseWb.getBerCode());
        rotatePodByTpsDTO.setBinCode(requestDTO.getBincode());
        String response = ITransferHikHttpRequestService.transferHikRotatePod(rotatePodByTpsDTO);
        ICommonService.handleHikResponseAndThrowException(response);
        return new Result();
    }

    /**
     * 校验参数必填
     *
     * @param requestDTO
     */
    private BaseWb checkRotatePodParamAndReturnBaseWb(RotatePodRequestDTO requestDTO) {
        String requestPodCode = requestDTO.getBincode().substring(0, 6);
        String requestAreaCode = requestDTO.getAreaCode();
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(requestAreaCode), "缺少库区信息");
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(requestDTO.getBincode()), "缺少货架信息");
        String wbCode = ICommonService.returnWbCodeByWbCodeOrByTaskPodCodeAndTaskType(requestDTO.getWbCode(), requestPodCode, null);
        BaseWb baseWb = baseWbMapper.selectByWbCodeAndValidFlagAndDeleteFlag(wbCode, ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
        Preconditions.checkBusinessError(baseWb == null, "未定义到点位信息，请确认");
        BaseBincodeDetail baseBincodeDetail = baseBincodeDetailMapper.selectByBincodeAndValidFlagAndDeleteFlag(requestDTO.getBincode(), ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
        Preconditions.checkBusinessError(baseBincodeDetail == null, "仓位号：" + requestDTO.getBincode() + "无效的仓位信息，请确认");
        boolean isNotSameAreaCode = !baseWb.getAreaCode().equals(requestAreaCode) || !baseBincodeDetail.getAreaCode().equals(requestAreaCode);
        Preconditions.checkBusinessError(isNotSameAreaCode, "旋转库区信息与点位/货架所在的库区不同，请确认");
        WbAgvTask wbAgvTask = wbAgvTaskMapper.selectUnCompletedTaskByWbCode(requestDTO.getWbCode());
        Preconditions.checkBusinessError(wbAgvTask == null, "点位：" + requestDTO.getWbCode() + "无货架任务，请确认");
        List<WbTaskDetail> wbTaskDetails = wbTaskDetailMapper.selectUnCompletedTaskByWbTaskNo(wbAgvTask.getTaskNo());
        Preconditions.checkBusinessError(wbTaskDetails.size() == 0, "点位：" + requestDTO.getWbCode() + "无货架任务，请确认");
        List<String> taskPods = wbTaskDetails.stream().map(WbTaskDetail::getPodCode).collect(Collectors.toList());
        Preconditions.checkBusinessError(!taskPods.contains(requestPodCode), "点位：" + requestDTO.getWbCode() + "无货架:" + requestPodCode + "任务，请确认");
        return baseWb;
    }


}
