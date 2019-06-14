package com.wisdom.service.instock.instockImpl;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.commonDto.fliterCondition.PodFliterCondition;
import com.wisdom.iwcs.domain.control.CallOutByBincodeRequestDTO;
import com.wisdom.iwcs.domain.task.AgvTaskInstockTaskParam;
import com.wisdom.iwcs.domain.task.WbAgvTask;
import com.wisdom.iwcs.mapper.base.BasePodBincodeMapper;
import com.wisdom.iwcs.mapper.task.AgvTaskInstockTaskParamMapper;
import com.wisdom.iwcs.mapper.task.WbAgvTaskMapper;
import com.wisdom.service.base.ICommonService;
import com.wisdom.service.common.PodCalImpl;
import com.wisdom.service.control.ICallOutByPodService;
import com.wisdom.service.instock.ICycleCallPodService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.wisdom.iwcs.common.utils.InStockConstants.cycleCallConstants.CYCLECALL;
import static com.wisdom.iwcs.common.utils.podUtils.PodTaskLockEnum.INSTOCK_TASK;

/**
 * 循环呼叫/补充呼叫
 *
 * @Author: george
 * @CreateDate: 2019/4/8 16:21
 */
@Service
public class CycleCallPodService implements ICycleCallPodService {

    @Resource
    private WbAgvTaskMapper wbAgvTaskMapper;
    @Autowired
    private PodCalImpl podCal;
    @Autowired
    private AgvTaskInstockTaskParamMapper agvTaskInstockTaskParamMapper;
    @Autowired
    private ICallOutByPodService ICallOutByPodService;
    @Autowired
    private ICommonService iCommonService;
    @Autowired
    private BasePodBincodeMapper basePodBincodeMapper;

    /**
     * 循环呼叫货架
     */
    @Override
    public Result cycleCallPods(String wbCode, String areaCode, String callType) {
        //计算入库呼叫策略相同的一个bincode
        List<String> podCodes = new ArrayList<>();
        //查询该点位未结束的入库taskNo
        WbAgvTask wbAgvTask = wbAgvTaskMapper.selectUnCompletedTaskByWbCode(wbCode);
        if (wbAgvTask.getTaskType().equals(INSTOCK_TASK.getType())) {
            //查询该呼叫任务参数
            AgvTaskInstockTaskParam agvTaskInstockTaskParam = agvTaskInstockTaskParamMapper.selectInstockTaskParam(wbAgvTask.getTaskNo());
            JSONObject jsonObject = JSONObject.fromObject(agvTaskInstockTaskParam.getStraParam());
            PodFliterCondition podFliterCondition = (PodFliterCondition) JSONObject.toBean(jsonObject, PodFliterCondition.class);
            if (callType.equals(CYCLECALL)) {
                podFliterCondition.setRequiredCount(1);
            }
            //TODO 策略参数优先级
            podCodes = podCal.calPodByPodFliterCondition(podFliterCondition);
        } else {
            throw new BusinessException("该定位：" + wbCode + "任务已结束，请重新呼叫！");
        }

        //podCodes转bincode
        List<String> bincodes = basePodBincodeMapper.podConvertBincode(podCodes);
        //去重
        List<String> getOutBincodes = iCommonService.distinctBinCodeByPodCode(bincodes);

        CallOutByBincodeRequestDTO callOutByBincodeRequestDTO = new CallOutByBincodeRequestDTO();
        callOutByBincodeRequestDTO.setWbCode(wbCode);
        callOutByBincodeRequestDTO.setAreaCode(areaCode);
        callOutByBincodeRequestDTO.setCallBincodes(getOutBincodes);
        ICallOutByPodService.callOutByBincode(callOutByBincodeRequestDTO);

        return new Result();
    }
}
