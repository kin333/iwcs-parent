package com.wisdom.iwcs.service.base;


import com.wisdom.iwcs.domain.base.dto.WbcodeSameTypeTaskDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:06
 */
public interface ICommonService {
    /**
     * 判断是否开启点位必填开关
     *
     * @return
     */
    Boolean IsOpenWbCode();

    /**
     * 根据点位、货架号、任务类型返回点位
     *
     * @param wbCode
     * @param podCode
     * @param taskType
     * @return
     */
    String returnWbCodeByWbCodeOrByTaskPodCodeAndTaskType(String wbCode, String podCode, String taskType);

    /**
     * 根据任务货架号、任务类型返回点位
     *
     * @param podCode
     * @param taskType
     * @return
     */
    String returnWbCodeByTaskPodCodeAndTaskType(String podCode, String taskType);

    /**
     * 发生库存变化的货架更新货架库存状态
     *
     * @param changePodCode
     */
    void updatePodStockInfoByChangePodCode(String changePodCode);

    /**
     * 根据任务状态、任务类型、货架列表，给货架上锁
     *
     * @param podCodes
     * @param taskType
     * @param taskStatus
     */
    void updatePodLockByPodCodes(List<String> podCodes, String taskType, String taskStatus);

    /**
     * 根据podCode给仓位列表去重
     *
     * @param bincodes
     * @return
     */
    List<String> distinctBinCodeByPodCode(List<String> bincodes);

    /**
     * 统一处理海康返回的结果
     *
     * @param hikResponse
     */
    void handleHikResponseAndThrowException(String hikResponse);

    /**
     * 根据podCode返回回库策略
     *
     * @param podCode
     * @return
     */
    String returnPodStrategyByPodCode(String podCode);

    /**
     * 校验点位的互斥点位是否有任务
     *
     * @param wbCode
     * @return
     */
    boolean checkWbCodeIfHaveMutexWbcodeTask(String wbCode);

    /**
     * 校验点位编码是否允许执行该类型的任务，如果当前点位有正在执行的任务，返回true和wbAgvTask
     *
     * @param wbCode
     * @param taskType
     * @return
     */
    WbcodeSameTypeTaskDTO checkWbCodeIfAllowCreateTaskByWbCodeAndTaskType(String wbCode, String taskType);

    /**
     * 校验回库货架只允许有一个货架，且返回任意一个bincode
     *
     * @param bincodes
     * @return
     */
    String checkReturnBincodesIfHaveDifferentPodAndReturnOneRandomBincode(List<String> bincodes);

    /**
     * 呼叫默认数量
     *
     * @param wbCode
     * @param bizType
     * @return defaultNum
     */
    Integer selectWbBizDefaultNum(String wbCode, String bizType);

    /**
     * 校验呼叫数量是否大于最大呼叫数量
     *
     * @param wbCode
     * @param bizType
     * @param callNum
     * @return beyondBizBatchMaxNum
     */
    Boolean checkWbBizMaxCallNum(String wbCode, String bizType, Integer callNum);
}
