package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.dto.LockMapBerthCondition;
import com.wisdom.iwcs.domain.base.dto.LockStorageDto;
import com.wisdom.iwcs.domain.task.AreaCondition;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.task.impl.MapResouceService;
import liquibase.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 锁定某个区域的一个空储位的公共服务(工具类)
 * @author han
 */
@Service
public class BaseLockEmptyMapService {
    private Logger logger = LoggerFactory.getLogger(BaseLockEmptyMapService.class);

    @Autowired
    MapResouceService mapResouceService;
    @Autowired
    SubTaskMapper subTaskMapper;


    /**
     * handleCondition 工具方法
     * @param subTaskCondition
     * @param areaConditions 包含点位目标的区域以及次级区域
     * @return
     */
    public boolean handleConditionService(SubTaskCondition subTaskCondition, List<AreaCondition> areaConditions) {
        logger.info("子任务{},开始锁定空储位", subTaskCondition.getSubTaskNum());
        if (areaConditions.size() <= 0) {
            throw new BusinessException("子任务{}请求异常,请初始化目标区域");
        }
        String subTaskNum = subTaskCondition.getSubTaskNum();
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskNum);

        logger.debug("子任务{},开始生成锁定条件", subTaskCondition.getSubTaskNum());
        //添加锁定条件
        List<LockMapBerthCondition> lockMapBerthConditions = new ArrayList<>();
        for (AreaCondition areaCondition : areaConditions) {
            LockMapBerthCondition lockMapBerthCondition = new LockMapBerthCondition();
            lockMapBerthCondition.setMapCode(subTask.getMapCode());
            lockMapBerthCondition.setBizType(areaCondition.getArea());
            lockMapBerthCondition.setLockSource(subTask.getSubTaskNum());
            if (StringUtils.isNotEmpty(areaCondition.getBizSecondArea())) {
                lockMapBerthCondition.setBizSecondAreaCode(areaCondition.getBizSecondArea());
            }
            lockMapBerthConditions.add(lockMapBerthCondition);
        }

        //锁定该任务点
        Result result = mapResouceService.lockEmptyStorageByBizTypeList(lockMapBerthConditions);
        if (result.getReturnCode() != HttpStatus.OK.value()) {
            logger.warn("子任务{}锁定空储位失败", subTaskCondition.getSubTaskNum());
            return false;
        }
        BaseMapBerth baseMapBerth = (BaseMapBerth)result.getReturnData();
        logger.info("子任务{},已锁定空储位,开始同步子任务单信息", subTaskCondition.getSubTaskNum());
        //更新子任务单中的结束点信息
        subTaskMapper.updateEndCodeBySubTaskCode(subTask.getSubTaskNum(), baseMapBerth);

        return true;
    }

    /**
     * handleCondition
     * @param subTaskCondition
     * @param areaConditions 包含点位目标的区域以及次级区域（超越项目）
     * @return
     */
    public boolean handleConditionServices(SubTaskCondition subTaskCondition, List<AreaCondition> areaConditions) {
        logger.info("子任务{},开始锁定空储位", subTaskCondition.getSubTaskNum());
        if (areaConditions.size() <= 0) {
            throw new BusinessException("子任务{}请求异常,请初始化目标区域");
        }
        String subTaskNum = subTaskCondition.getSubTaskNum();
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskNum);

        logger.debug("子任务{},开始生成锁定条件", subTaskCondition.getSubTaskNum());
        //添加锁定条件
        List<LockMapBerthCondition> lockMapBerthConditions = new ArrayList<>();
        for (AreaCondition areaCondition : areaConditions) {
            LockMapBerthCondition lockMapBerthCondition = new LockMapBerthCondition();
            lockMapBerthCondition.setMapCode(subTask.getMapCode());
            if (StringUtils.isNotEmpty(areaCondition.getArea())){
                lockMapBerthCondition.setBizType(areaCondition.getArea());
            }
            if (StringUtils.isNotEmpty(areaCondition.getBizType())){
                lockMapBerthCondition.setOperateAreaCode(areaCondition.getBizType());
            }
            lockMapBerthCondition.setLockSource(subTask.getSubTaskNum());
            if (StringUtils.isNotEmpty(areaCondition.getBizSecondArea())) {
                lockMapBerthCondition.setBizSecondAreaCode(areaCondition.getBizSecondArea());
            }
            lockMapBerthConditions.add(lockMapBerthCondition);
        }

        //锁定该任务点
        Result result = mapResouceService.lockEmptyStorageByBizTypList(lockMapBerthConditions);
        if (result.getReturnCode() != HttpStatus.OK.value()) {
            logger.warn("子任务{}锁定空储位失败", subTaskCondition.getSubTaskNum());
            return false;
        }
        BaseMapBerth baseMapBerth = (BaseMapBerth)result.getReturnData();
        logger.info("子任务{},已锁定空储位,开始同步子任务单信息", subTaskCondition.getSubTaskNum());
        //更新子任务单中的结束点信息
        subTaskMapper.updateEndCodeBySubTaskCode(subTask.getSubTaskNum(), baseMapBerth);

        return true;
    }

    /**
     * rollbackCondition 工具方法
     * @param subTaskCondition
     * @return
     */
    public boolean rollbackConditionService(SubTaskCondition subTaskCondition) {
        logger.info("子任务{}回滚开始", subTaskCondition.getSubTaskNum());
        //还原地图数据的锁定信息
        String subTaskNum = subTaskCondition.getSubTaskNum();
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskNum);
        LockStorageDto lockStorageDto = new LockStorageDto();
        lockStorageDto.setMapCode(subTask.getMapCode());
        lockStorageDto.setBerCode(subTask.getEndBercode());
        Result result = mapResouceService.unlockMapBerth(lockStorageDto);
        //还原子任务单中的货架号
        subTaskMapper.updateEndCodeBySubTaskCode(subTaskCondition.getSubTaskNum(), new BaseMapBerth());
        if (result.getReturnCode() ==  HttpStatus.OK.value()) {
            logger.info("子任务{}回滚成功", subTaskCondition.getSubTaskNum());
            return true;
        }
        logger.warn("子任务{}回滚失败:{},子任务单信息:{}", subTaskCondition.getSubTaskNum(), result.getReturnMsg(),subTask.toString());
        return false;
    }



}
