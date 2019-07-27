package com.wisdom.iwcs.service.base.baseImpl;

import com.google.common.base.Strings;
import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.iwcs.common.utils.ValidFlagEnum;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.common.utils.podUtils.PodTaskLockEnum;
import com.wisdom.iwcs.domain.base.*;
import com.wisdom.iwcs.domain.base.dto.WbcodeSameTypeTaskDTO;
import com.wisdom.iwcs.domain.task.WbAgvTask;
import com.wisdom.iwcs.mapper.base.*;
import com.wisdom.iwcs.mapper.stock.StockMapper;
import com.wisdom.iwcs.mapper.task.WbAgvTaskMapper;
import com.wisdom.iwcs.mapper.task.WbTaskDetailMapper;
import com.wisdom.iwcs.service.base.ICommonService;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.wisdom.iwcs.common.utils.DeleteFlagEnum.NOT_DELETED;
import static com.wisdom.iwcs.common.utils.podUtils.PodConstants.BinCargoCapacityStatus.EMPTY_BIN;
import static com.wisdom.iwcs.common.utils.podUtils.PodConstants.BinCargoCapacityStatus.NOT_FULL_BIN;
import static com.wisdom.iwcs.common.utils.taskUtils.AgvTaskConstants.AgvTaskStatusConstants.TASK_CREATED;
import static com.wisdom.iwcs.common.utils.taskUtils.AgvTaskConstants.AgvTaskStatusConstants.TASK_ENDED;

@Service
@Transactional(rollbackFor = Exception.class)
public class CommonService implements ICommonService {
    private final Logger logger = LoggerFactory.getLogger(CommonService.class);

    @Autowired
    private ApplicationProperties applicationProperties;
    @Autowired
    private WbAgvTaskMapper wbAgvTaskMapper;
    @Autowired
    private BaseBincodeDetailMapper baseBincodeDetailMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private BasePodDetailMapper basePodDetailMapper;
    @Autowired
    private WbTaskDetailMapper wbTaskDetailMapper;
    @Autowired
    private BasePodMapper basePodMapper;
    @Autowired
    private BasePodTypeMapper basePodTypeMapper;
    @Autowired
    private com.wisdom.iwcs.service.base.IBaseWbGroupDetailService IBaseWbGroupDetailService;
    @Autowired
    private BasePodBincodeMapper basePodBincodeMapper;
    @Autowired
    private BaseWbMapper baseWbMapper;
    @Autowired
    private BasePodLayerStkMapper basePodLayerStkMapper;
    @Autowired
    private BaseWbBizConfigMapper baseWbBizConfigMapper;
    @Autowired
    private BaseMapBerthMapper baseMapBerthMapper;

    /**
     * 入库、出库确认时是否传入工作台
     * 开启 支持同一货架多个点位货架
     */
    @Override
    public Boolean IsOpenWbCode() {
        boolean isOpen = false;
        ApplicationProperties.Wbcode wbcode = applicationProperties.getWbcode();
        if (wbcode.isOpenWbCode()) {
            isOpen = true;
        }
        return isOpen;
    }

    /**
     * 根据任务类型、任务货架返回点位信息
     *
     * @param wbCode
     * @param podCode
     * @param taskType
     * @return
     */
    @Override
    public String returnWbCodeByWbCodeOrByTaskPodCodeAndTaskType(String wbCode, String podCode, String taskType) {
        boolean openWbcode = IsOpenWbCode();
        if (openWbcode) {
            Preconditions.checkBusinessError(Strings.isNullOrEmpty(wbCode), "当前设置为点位信息必填，请提供相应的点位信息");
            BaseWb baseWb = baseWbMapper.selectByWbCodeAndValidFlagAndDeleteFlag(wbCode, ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
            Preconditions.checkBusinessError(baseWb == null, "点位：" + wbCode + "无效，请确认");
            return wbCode;
        }

        String returnWbCode = returnWbCodeByTaskPodCodeAndTaskType(podCode, taskType);
        return returnWbCode;
    }

    /**
     * 根据任务货架号、任务类型获取任务点位信息
     *
     * @param podCode
     * @param taskType
     * @return
     */
    @Override
    public String returnWbCodeByTaskPodCodeAndTaskType(String podCode, String taskType) {
        String wbCode = "";
        List<WbAgvTask> agvTasks = wbAgvTaskMapper.selectUnCompletedTaskInfoByPodCodeAndTaskType(podCode, taskType);
        if (agvTasks.size() == 1) {
            wbCode = agvTasks.get(0).getWbCode();
            return wbCode;
        }
        List<WbAgvTask> notifyAgvTaskList = wbAgvTaskMapper.selectArrivedTaskByPodCodeAndTaskType(podCode, taskType);
        Preconditions.checkBusinessError(notifyAgvTaskList.size() == 0, "货架号：" + podCode + "未获取到到达点位信息，多任务模式请等待货架到达工作台再继续操作");
        wbCode = notifyAgvTaskList.get(0).getWbCode();
        return wbCode;
    }

    /**
     * 根据仓位号更新和货架有关的动态库存信息
     * 如仓位存货情况、pod的空满仓位数等
     *
     * @param changePodCode：发生库存变动podCode
     */
    @Override
    public void updatePodStockInfoByChangePodCode(String changePodCode) {
        List<BaseBincodeDetail> baseBincodeDetailList = baseBincodeDetailMapper.selectByPodCodeAndValidFlagAndDeletedFlag(changePodCode, ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());

        baseBincodeDetailList.stream().forEach(baseBincodeDetail -> {
            String bincode = baseBincodeDetail.getBincode();
            int countStock = stockMapper.selectCountByBincodeAndDeleteFlag(bincode, NOT_DELETED.getStatus());
            if (countStock == 0) {
                baseBincodeDetail.setCargoCapacityStatus(EMPTY_BIN);
            } else {
                baseBincodeDetail.setCargoCapacityStatus(NOT_FULL_BIN);
            }
            baseBincodeDetailMapper.updateByPrimaryKeySelective(baseBincodeDetail);
        });


        List<BasePodLayerStk> basePodLayerStkList = basePodLayerStkMapper.selectByPodCode(changePodCode);
        basePodLayerStkList.stream().forEach(basePodLayerStk -> {
            int countEbincode = baseBincodeDetailMapper.selectCountByPodCodeAndCargoCapacityStatusAndLayer(basePodLayerStk.getPodCode(), EMPTY_BIN, basePodLayerStk.getLayer());
            int countFbincode = baseBincodeDetailMapper.selectCountByPodCodeAndNotCargoCapacityStatusAndLayer(basePodLayerStk.getPodCode(), EMPTY_BIN, basePodLayerStk.getLayer());
            basePodLayerStk.seteBincode(countEbincode);
            basePodLayerStk.setfBincode(countFbincode);
            basePodLayerStkMapper.updateByPrimaryKeySelective(basePodLayerStk);
        });
    }

    /**
     * 根据货架号、任务类型更新货架状态
     *
     * @param podCodes
     * @param taskType
     */
    @Override
    public void updatePodLockByPodCodes(List<String> podCodes, String taskType, String taskStatus) {
        Integer podTaskValue = PodTaskLockEnum.returnTaskValueByType(taskType);
        switch (taskStatus) {
            case TASK_CREATED:
                basePodDetailMapper.updatePodTaskLockByPodCodesAndLockStat(podCodes, podTaskValue);
                break;
            case TASK_ENDED:
                basePodDetailMapper.removePodTaskLockByPodCodesAndLockStat(podCodes, podTaskValue);
                break;
            default:
                logger.info("updatePodLockByPodCodes unexpectedError,{}", taskStatus);
                throw new BusinessException("未定义的任务节点，无法改变锁状态");
        }
    }

    /**
     * bincode按照货架号去重
     *
     * @param bincodes
     * @return
     */
    @Override
    public List<String> distinctBinCodeByPodCode(List<String> bincodes) {
        List<String> resultBinCodes = new ArrayList<>();
        List<String> podCodes = new ArrayList<>();
        bincodes.stream().filter(b -> !podCodes.contains(b.substring(0, 6))).forEach(bincode -> {
            resultBinCodes.add(bincode);
            podCodes.add(bincode.substring(0, 6));
        });

        return resultBinCodes;
    }

    /**
     * 处理海康返回数据，
     *
     * @param hikResponse
     */
    @Override
    public void handleHikResponseAndThrowException(String hikResponse) {
        try {
            JSONObject obj = new JSONObject(hikResponse);
            if (!obj.getString("code").equals("0")) {
                throw new BusinessException(obj.getString("message"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据货架号返回入库策略
     *
     * @param podCode
     * @return
     */
    @Override
    public String returnPodStrategyByPodCode(String podCode) {
        List<String> podTypeCodes = basePodMapper.selectPodTypeCodeByPodCode(podCode);
        Preconditions.checkBusinessError(podTypeCodes.size() == 0, "货架号：" + podCode + "无该信息，请确认");
        BasePodType basePodType = basePodTypeMapper.selectByPodTypeCodeAndValidFlagAndDeleteFlag(podTypeCodes.get(0), ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
        String returnStrategy = basePodType.getDefaultReturnStrategy();
        return returnStrategy;
    }


    /**
     * 校验传入的工作台点位是否允许创建点位任务
     *
     * @param wbCode
     * @return
     */
    @Override
    public boolean checkWbCodeIfHaveMutexWbcodeTask(String wbCode) {
        boolean isHave = false;
        List<String> mutexWbCodes = IBaseWbGroupDetailService.selectMutexTypeWbByWbCode(wbCode);
        if (mutexWbCodes.size() == 0) {
            return isHave;
        }
        int countMutexTask = wbAgvTaskMapper.selectCountNotCompletedTaskByWbCodes(mutexWbCodes);
        if (countMutexTask != 0) {
            isHave = true;
            return isHave;
        }
        return isHave;
    }

    /**
     * 校验点位、任务类型
     *
     * @param wbCode
     * @param taskType
     * @return
     */
    @Override
    public WbcodeSameTypeTaskDTO checkWbCodeIfAllowCreateTaskByWbCodeAndTaskType(String wbCode, String taskType) {
        WbcodeSameTypeTaskDTO wbcodeSameTypeTaskDTO = new WbcodeSameTypeTaskDTO();
        //判断工作台能否执行该类型业务
        BaseWbBizConfig baseWbBizConfig = baseWbBizConfigMapper.selectByWbCodeAndBizType(wbCode, taskType);
        Preconditions.checkBusinessError(baseWbBizConfig == null, "点位编号:" + wbCode + "无业务类型:" + taskType + "配置信息，不允许在该点位进行此业务类型呼叫，如需更改配置信息请联系实施工程师");
        //判断工作台是否有未结束的其他类型任务
        int countOtherTypeUnEndTask = wbAgvTaskMapper.selectCountNotCompletedTaskByWbCodeAndNotPointTaskType(wbCode, taskType);
        Preconditions.checkBusinessError(countOtherTypeUnEndTask != 0, "点位编号:" + wbCode + "正在执行其他类型任务，请完成后再呼叫");
        //获取该工作台是否有该类型任务
        WbAgvTask wbAgvTask = wbAgvTaskMapper.selectUnCompletedTaskByWbCode(wbCode);
        if (wbAgvTask != null) {
            wbcodeSameTypeTaskDTO.setHaveSameTypeTask(true);
            wbcodeSameTypeTaskDTO.setWbAgvTask(wbAgvTask);
        } else {
            wbcodeSameTypeTaskDTO.setHaveSameTypeTask(false);
        }
        //判断互斥工作台任务
        boolean mutexWbcodeHaveTask = checkWbCodeIfHaveMutexWbcodeTask(wbCode);
        Preconditions.checkBusinessError(mutexWbcodeHaveTask, "点位编号:" + wbCode + "的互斥工作台存在点位任务，请完成后再呼叫");

        return wbcodeSameTypeTaskDTO;
    }

    @Override
    public String checkReturnBincodesIfHaveDifferentPodAndReturnOneRandomBincode(List<String> bincodes) {
        List<String> bincodesDistinctByPod = distinctBinCodeByPodCode(bincodes);
        Preconditions.checkBusinessError(bincodesDistinctByPod.size() > 1, "回库操作只允许执行单货架");
        String returnBincode = bincodesDistinctByPod.get(0);
        return returnBincode;
    }

    public List<String> underBizOrderNoByWbCode(String bizOrderNo) {

        return wbAgvTaskMapper.underBizOrderNoByWbCode(bizOrderNo);
    }

    /**
     * 呼叫默认数量
     *
     * @param wbCode
     * @param bizType
     * @return defaultNum
     */
    @Override
    public Integer selectWbBizDefaultNum(String wbCode, String bizType) {
        Integer defaultNum = 0;
        BaseWbBizConfig baseWbBizConfig = baseWbBizConfigMapper.selectByWbCodeAndBizType(wbCode, bizType);
        Preconditions.checkBusinessError(baseWbBizConfig == null, "工作台：" + wbCode + "业务" + bizType + "没有配置基础信息");
        defaultNum = baseWbBizConfig.getBizDefaultNum();
        return defaultNum;
    }

    /**
     * 校验呼叫数量是否大于最大呼叫数量
     *
     * @param wbCode
     * @param bizType
     * @param callNum
     * @return beyondBizBatchMaxNum
     */
    @Override
    public Boolean checkWbBizMaxCallNum(String wbCode, String bizType, Integer callNum) {
        boolean beyondBizBatchMaxNum = false;
        BaseWbBizConfig baseWbBizConfig = baseWbBizConfigMapper.selectByWbCodeAndBizType(wbCode, bizType);
        Preconditions.checkBusinessError(baseWbBizConfig == null, "工作台：" + wbCode + "业务" + bizType + "没有配置基础信息");
        if (Integer.valueOf(baseWbBizConfig.getBizBatchMaxNum()) < callNum) {
            beyondBizBatchMaxNum = true;
        }
        return beyondBizBatchMaxNum;
    }

    /**
     * 校验货架表坐标和地图表货架是否一致
     * @param podCode
     * @return podPointAgreement
     */
    @Override
    public Boolean checkPodPointAgreement(String podCode){
        boolean podPointAgreement = false;
        String berCode = basePodDetailMapper.selectBerCodeByPodCode(podCode);
        Preconditions.checkBusinessError(berCode == null, "查询货架：" + podCode + "坐标信息为空或已被锁定");
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(berCode);
        Preconditions.checkBusinessError(baseMapBerth == null, "货架：" + podCode + "货架表中记录的坐标" + berCode + "在地图信息表中未查找到");
        if (podCode.equals(baseMapBerth.getPodCode())) {
            podPointAgreement = true;
        }
        return podPointAgreement;
    }

    /**
     * 随机产生16进制数
     * @param len
     */
    @Override
    public String randomHexString(int len)  {
        try {
            StringBuffer result = new StringBuffer();
            for(int i=0;i<len;i++) {
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            return result.toString().toUpperCase();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

        }
        return null;
    }
}

