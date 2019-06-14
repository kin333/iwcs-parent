package com.wisdom.service.instock.instockImpl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.commonDto.fliterConOptions.BasePodDetailConOptions;
import com.wisdom.iwcs.commonDto.fliterConOptions.PodLayerStkStaConOptions;
import com.wisdom.iwcs.commonDto.fliterCondition.PodFliterCondition;
import com.wisdom.iwcs.commonDto.strategy.PodStrategyEnum;
import com.wisdom.iwcs.domain.base.BaseWbBizConfig;
import com.wisdom.iwcs.domain.hikSync.GetOutPodDTO;
import com.wisdom.iwcs.domain.instock.InstockCallStraPri;
import com.wisdom.iwcs.domain.instock.dto.instockrequest.BeginUnloadRequestDTO;
import com.wisdom.iwcs.domain.instock.dto.instockrequest.InstockCallStraParamDetail;
import com.wisdom.iwcs.domain.task.AgvTaskInstockTaskParam;
import com.wisdom.iwcs.mapper.base.BasePodBincodeMapper;
import com.wisdom.iwcs.mapper.base.BaseWbBizConfigMapper;
import com.wisdom.iwcs.mapper.instock.InstockCallStraPriMapper;
import com.wisdom.iwcs.mapper.task.AgvTaskInstockTaskParamMapper;
import com.wisdom.iwcs.mapper.task.WbAgvTaskMapper;
import com.wisdom.service.base.IBaseWbGroupDetailService;
import com.wisdom.service.base.ICommonService;
import com.wisdom.service.callHik.IGetOutPodService;
import com.wisdom.service.common.IPodCal;
import com.wisdom.service.instock.IBeginUnloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.wisdom.iwcs.common.utils.podUtils.PodTaskLockEnum.INSTOCK_TASK;
import static com.wisdom.iwcs.common.utils.taskUtils.AgvTaskConstants.AgvTaskStatusConstants.TASK_ENDED;

/**
 * @Description: 入库呼叫
 * @Author: george
 * @CreateDate: 2019/3/4 9:42
 */
@Service
public class BeginUnloadService implements IBeginUnloadService {
    private final Logger logger = LoggerFactory.getLogger(InboundUnloadService.class);

    @Autowired
    private WbAgvTaskMapper wbAgvTaskMapper;
    @Autowired
    private IBaseWbGroupDetailService IBaseWbGroupDetailService;
    @Autowired
    private IGetOutPodService IGetOutPodService;
    @Autowired
    private BasePodBincodeMapper basePodBincodeMapper;
    @Autowired
    private IPodCal iPodCal;
    @Autowired
    private BaseWbBizConfigMapper baseWbBizConfigMapper;
    @Autowired
    private ICommonService iCommonService;
    @Autowired
    private AgvTaskInstockTaskParamMapper agvTaskInstockTaskParamMapper;
    @Autowired
    private InstockCallStraPriMapper instockCallStraPriMapper;

    /**
     * 入库呼叫
     */
    @Override
    public Result instockCall(BeginUnloadRequestDTO requestDTO) {
        checkInBeginUnloadConfirmParam(requestDTO);

        //处理呼叫货架数量
        Integer callPodNum = 0;
        BaseWbBizConfig baseWbBizConfig = baseWbBizConfigMapper.selectByWbCodeAndBizType(requestDTO.getWbCode(), INSTOCK_TASK.getType());
        if (requestDTO.getPodNum() == null) {
            callPodNum = baseWbBizConfig.getBizDefaultNum();
        } else {
            callPodNum = Integer.valueOf(baseWbBizConfig.getBizBatchMaxNum());
        }

        //通过计算器得到货架
        InstockCallStraParamDetail instockCallStraParamDetail = requestDTO.getInstockCallStraParamDetail();
        //处理呼叫策略参数
        PodFliterCondition podFliterCondition = new PodFliterCondition();
        PodLayerStkStaConOptions podLayerStkStaCon = new PodLayerStkStaConOptions();
        BasePodDetailConOptions basePodDetailCon = new BasePodDetailConOptions();

        //组装处理层级库存
        podLayerStkStaCon.setLayerConType(instockCallStraParamDetail.getLayerConType());
        podLayerStkStaCon.setSpecificLayers(instockCallStraParamDetail.getSpecificLayers());

        //组装处理动态货架属性可选项
        basePodDetailCon.setStgTypeCode(instockCallStraParamDetail.getStgTypeCode());
        basePodDetailCon.setStgCode(instockCallStraParamDetail.getStgCode());
        basePodDetailCon.setPodProp1(instockCallStraParamDetail.getPodProp1());
        basePodDetailCon.setPodProp2(instockCallStraParamDetail.getPodProp2());
        basePodDetailCon.setPodProp3(instockCallStraParamDetail.getPodProp3());
        basePodDetailCon.setPodProp4(instockCallStraParamDetail.getPodProp4());
        basePodDetailCon.setPodProp5(instockCallStraParamDetail.getPodProp5());

        //封装货架计算条件
        podFliterCondition.setBasePodDetailCon(basePodDetailCon);
        podFliterCondition.setPodLayerStkStaCon(podLayerStkStaCon);
        podFliterCondition.setRequiredCount(callPodNum);
        podFliterCondition.setAreaCode(requestDTO.getAreaCode());
        podFliterCondition.setPodTypeCodes(instockCallStraParamDetail.getPodTypeCodes());

        //TODO:获取优先级
        List<InstockCallStraPri> instockCallStraPris = instockCallStraPriMapper.selectStaPriByStraCode(requestDTO.getStraCode());
        List<PodStrategyEnum> podStrategyEnums = new ArrayList<>();
        for (InstockCallStraPri instockCallStraPri : instockCallStraPris) {
            PodStrategyEnum podStrategyEnum = PodStrategyEnum.getByStragegyCode(instockCallStraPri.getPriority());
            if (podStrategyEnum != null) {
                podStrategyEnums.add(podStrategyEnum);
            }
        }
        Class<PodStrategyEnum> podStrategyEnumClass = PodStrategyEnum.class;
        List<String> podCodes = iPodCal.calPodByPodFliterCondition(podFliterCondition);

        //podCodes转bincode
        List<String> bincodes = basePodBincodeMapper.podConvertBincode(podCodes);
        //去重
        List<String> getOutBincodes = iCommonService.distinctBinCodeByPodCode(bincodes);
        GetOutPodDTO getOutPodDTO = new GetOutPodDTO();
        getOutPodDTO.setBincodes(getOutBincodes);
        getOutPodDTO.setWbCode(requestDTO.getWbCode());
        getOutPodDTO.setTaskType(INSTOCK_TASK.getType());
        getOutPodDTO.setLooplb(requestDTO.getLooplb());
        Result newResult = IGetOutPodService.getOutPod(getOutPodDTO);

        //保存本次呼叫策略参数
        AgvTaskInstockTaskParam agvTaskInstockTaskParam = new AgvTaskInstockTaskParam();
        agvTaskInstockTaskParam.setCreatedTime(new Date());
        agvTaskInstockTaskParam.setStraCode(requestDTO.getStraCode());
        agvTaskInstockTaskParam.setTaskNo(newResult.getReturnData().toString());
        agvTaskInstockTaskParam.setStraParam(JSON.toJSONString(podFliterCondition));
        agvTaskInstockTaskParamMapper.insertSelective(agvTaskInstockTaskParam);

        return new Result();
    }

    /**
     * 校验入库确认传入参数
     */
    private void checkInBeginUnloadConfirmParam(BeginUnloadRequestDTO request) {
        String wbCode = request.getWbCode();
        Integer callPodNum = request.getPodNum();
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(wbCode), "请指定点位信息");
        int notCompletedTaskNum = wbAgvTaskMapper.selectCountNotCompletedTaskByWbCodeAndTaskEndStatus(wbCode, TASK_ENDED);
        Preconditions.checkBusinessError(notCompletedTaskNum > 0, "该点位:" + wbCode + "还有未完成的任务，请稍后呼叫");
        List<String> mutexWbCodes = IBaseWbGroupDetailService.selectMutexTypeWbByWbCode(wbCode);
        if (mutexWbCodes.size() > 0) {
            int notCompletedTaskNumByMutexWbCode = wbAgvTaskMapper.selectCountNotCompletedTaskByWbCodeAndTaskEndStatus(wbCode, TASK_ENDED);
            Preconditions.checkBusinessError(notCompletedTaskNum > 0, "该点位:" + wbCode + "存在互斥工作台，互斥工作台有任务，请稍后呼叫");
        }
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(request.getStraCode()), "请指定呼叫货架条件号");
        if (callPodNum != null && callPodNum > 0) {
            boolean wbBizMaxCallNum = iCommonService.checkWbBizMaxCallNum(wbCode, INSTOCK_TASK.getType(), callPodNum);
            Preconditions.checkBusinessError(wbBizMaxCallNum, "该点位:" + wbCode + "呼叫货架数量不能大于设置的入库呼叫最大货架数量");
        }
    }
}
