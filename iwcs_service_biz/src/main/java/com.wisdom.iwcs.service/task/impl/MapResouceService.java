package com.wisdom.iwcs.service.task.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.wisdom.iwcs.common.utils.CompanyFinancialStatusEnum;
import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.YZConstants;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.*;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.task.intf.IMapResouceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizTypeConstants.QUAINSPCACHEAREA;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizTypeConstants.QUAINSPWORKAREA;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.OperateAreaCodeConstants.QUAINSPAREA;

@Service
@Transactional(rollbackFor = Exception.class)
public class MapResouceService implements IMapResouceService {
    private Logger logger = LoggerFactory.getLogger(MapResouceService.class);

    @Autowired
    private BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    BasePodDetailMapper basePodDetailMapper;
    @Autowired
    SubTaskMapper subTaskMapper;

    /**
     * 获取检验区工作区点位或缓存区 ,并锁住
     * @param lockMapBerthCondition
     * @return
     */
    @Override
    public BaseMapBerth caculateInspectionWorkAreaEmptyPoint(LockMapBerthCondition lockMapBerthCondition) {
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(lockMapBerthCondition.getMapCode()), "缺少地图编码");

        BaseMapBerth emptyPoit = new BaseMapBerth();
        //获取检验点空位置
        lockMapBerthCondition.setOperateAreaCode(QUAINSPAREA);
        List<BaseMapBerth> baseMapBerthList = baseMapBerthMapper.selectEmptyStorageOfInspectionArea(lockMapBerthCondition);
        if(baseMapBerthList.size() <= 0) {
            return null;
        }
        if (QUAINSPWORKAREA.equals(lockMapBerthCondition.getBizType())){
            //根据获取空位置计算最优位置
            emptyPoit=calculatingOptimalLocation(baseMapBerthList);
        }else if (QUAINSPCACHEAREA.equals(lockMapBerthCondition.getBizType())){
            emptyPoit = baseMapBerthList.get(0);
        }else{
            Preconditions.checkBusinessError(Strings.isNullOrEmpty(lockMapBerthCondition.getBizType()), "缺少作业区域类型");
        }

        return emptyPoit;
    }

    @Override
    public Result selectQuaEmptyStorage(LockMapBerthCondition lockMapBerthCondition) {
        if(Strings.isNullOrEmpty(lockMapBerthCondition.getMapCode())) {
            return new Result(400,"缺少地图编码");
        }
        BaseMapBerth emptyPoit = new BaseMapBerth();
        //获取检验点空位置
        lockMapBerthCondition.setBizType(QUAINSPWORKAREA);
        lockMapBerthCondition.setOperateAreaCode(QUAINSPAREA);
        List<BaseMapBerth> baseMapBerthList = baseMapBerthMapper.selectEmptyStorageOfInspectionArea(lockMapBerthCondition);
        if(Strings.isNullOrEmpty(lockMapBerthCondition.getMapCode())) {
            return new Result(400,"检验点工作区暂无空位置");
        }
        if(baseMapBerthList == null || baseMapBerthList.size() <= 0) {
            return new Result(400, "检验点工作区暂无空位置");
        }
        //计算空闲点位
        emptyPoit=calculatingOptimalLocation(baseMapBerthList);

        //锁住空闲点位
        LockStorageDto lockStorageDto = new LockStorageDto();
        lockStorageDto.setMapCode(emptyPoit.getMapCode());
        lockStorageDto.setBerCode(emptyPoit.getBerCode());
        lockStorageDto.setPodCode(lockMapBerthCondition.getPodCode());
        lockStorageDto.setLockSource(lockMapBerthCondition.getLockSource());
        Result result = lockMapBerth(lockStorageDto);
        Preconditions.checkBusinessError(result.getReturnCode() != 200,result.getReturnMsg());
        return new Result();
    }

    /**
     * 计算最优的点位(优先选择一组全空的点位，然后再比较距离)
     * @param baseMapBerthList
     * @return
     */
    public BaseMapBerth calculatingOptimalLocation(List<BaseMapBerth> baseMapBerthList) {
        //将空点位进行分组，比较并且筛选出组包含最多点位的点位列表
        List<BaseMapBerth> berGroupList = calculateMaxAndEmptyGroupBer(baseMapBerthList);
        //根据点位坐标计算出最优点位
        BaseMapBerth baseMapBerth = distanceRule(berGroupList);
        return baseMapBerth;
    }


    /**
     * 计算组包含最多点位
     * @param baseMapBerthList
     * @return
     */
    public List<BaseMapBerth> calculateMaxAndEmptyGroupBer(List<BaseMapBerth> baseMapBerthList){
        //进行点位分组
        Map<String,List<BaseMapBerth>> berGroupMap = baseMapBerthList.stream().collect(Collectors.groupingBy(BaseMapBerth::getBerGroup));

        List<BaseMapBerth> berGroupList = new ArrayList<>();

        //计算分组里面最多的点位数
        int max = 0;
        for ( List<BaseMapBerth> mapValue:berGroupMap.values()) {
            if(mapValue.size() > max) {
                max = mapValue.size();
            }
        }
        for ( List<BaseMapBerth> mapValue:berGroupMap.values()) {
            if(mapValue.size() == max) {
                berGroupList.add(mapValue.get(0));
            }
        }
        return berGroupList;
    }


    /**
     * 计算x值最小的位置
     * @param baseMapBerthList
     * @return
     */
    public BaseMapBerth distanceRule(List<BaseMapBerth> baseMapBerthList) {
        Optional<BaseMapBerth> minMapBerth = baseMapBerthList.stream().max((a,b) -> a.getCoox().compareTo(b.getCoox()));
        return minMapBerth.get();
    }

    /**
     * 计算y值
     * @param baseMapBerthList
     * @return
     */
    public BaseMapBerth distanceRuleDesc(List<BaseMapBerth> baseMapBerthList) {
        Optional<BaseMapBerth> minMapBerth = baseMapBerthList.stream().max((a,b) -> a.getCooy().compareTo(b.getCooy()));
        return minMapBerth.get();
    }
    /**
     * 锁住选中的点位并更新子任务单
     * @param lockStorageDto
     * @return
     */
    @Override
    public Result lockMapBerth(LockStorageDto lockStorageDto) {
        Result validateResult = validateLockParamss(lockStorageDto);
        if (validateResult.getReturnCode() != 200){
            return validateResult;
        }
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(lockStorageDto.getBerCode());
        if (YZConstants.LOCK.equals(baseMapBerth.getInLock())) {
            return new Result(400, baseMapBerth.getBerCode() + "该储位已被锁定");
        }
        if(!Strings.isNullOrEmpty(baseMapBerth.getPodCode())) {
            return new Result(400,baseMapBerth.getBerCode() + "该储位存在货架:"+baseMapBerth.getPodCode()+"，请稍后执行");
        }
        //锁住选中的点位
        lockStorageDto.setVersion(baseMapBerth.getVersion());
        int count = baseMapBerthMapper.lockMapBerthByBercode(lockStorageDto);
        if(count < 1) {
            return new Result(400,"该储位在进行其他操作中，请稍后执行");
        }
        logger.info("点位锁定成功:{} 锁定源为:{} 乐观锁版本号:{}", baseMapBerth.getBerCode(),
                                        lockStorageDto.getLockSource(), baseMapBerth.getVersion());
        //更新子任务终点坐标
        String subTaskNum = lockStorageDto.getLockSource();
        BaseMapBerth lockMapBerth = new BaseMapBerth();
        lockMapBerth.setCoox(baseMapBerth.getCoox());
        lockMapBerth.setCooy(baseMapBerth.getCooy());
        lockMapBerth.setBerCode(baseMapBerth.getBerCode());
        subTaskMapper.updateEndCodeBySubTaskCode(subTaskNum,lockMapBerth);
        return new Result();
    }



    /**
     * 解锁选中的点位并更新子任务单
     * @param lockStorageDto
     * @return
     */
    @Override
    public Result unlockMapBerth(LockStorageDto lockStorageDto) {

        if(Strings.isNullOrEmpty(lockStorageDto.getMapCode())) {
            return new Result(400,"缺少地图编码");
        }
        if(Strings.isNullOrEmpty(lockStorageDto.getBerCode())) {
            return new Result(400,"缺少点位代码");
        }
        //乐观锁版本检测
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectBerData(lockStorageDto);
        lockStorageDto.setVersion(baseMapBerth.getVersion());
        //解锁选中的点位
        int count = baseMapBerthMapper.unlockMapBerth(lockStorageDto);
        if(count < 1) {
            return new Result(400, baseMapBerth.getBerCode() + "该储位在进行其他操作中，请稍后执行解锁");
        }
        logger.info("点位解锁成功:{} 锁定源为:{} 乐观锁版本号:{}", baseMapBerth.getBerCode(),
                baseMapBerth.getLockSource(), baseMapBerth.getVersion());
        //更新子任务终点坐标
        String subTaskNum = lockStorageDto.getLockSource();
        BaseMapBerth lockMapBerth = new BaseMapBerth();
        lockMapBerth.setCoox(BigDecimal.ZERO);
        lockMapBerth.setCooy(BigDecimal.ZERO);
        lockMapBerth.setBerCode("");
        subTaskMapper.updateEndCodeBySubTaskCode(subTaskNum,lockMapBerth);
        return new Result();
    }

    /**
     * 锁定选中货架
     */
    @Override
    public boolean lockPod(BasePodDetail basePodDetail) {
        if (Strings.isNullOrEmpty(basePodDetail.getPodCode())) {
            throw new BusinessException("货架不能为空");
        }
        if (Strings.isNullOrEmpty(basePodDetail.getLockSource())) {
            throw new BusinessException("锁定源不能为空");
        }
        //乐观锁检查
        BasePodDetail updateBasePodDetail = basePodDetailMapper.selectByPodCode(basePodDetail.getPodCode());
        if (YZConstants.LOCK.equals(updateBasePodDetail.getInLock())) {
            logger.error("货架{}正在进行其他操作,已被锁定,请稍后执行", basePodDetail.getPodCode());
            throw new BusinessException("该货架在进行其他操作中，请稍后执行");
        }
        basePodDetail.setVersion(updateBasePodDetail.getVersion());
        //锁定货架
        int changeRow = basePodDetailMapper.lockPod(basePodDetail);
        if(changeRow < 1) {
            logger.error("货架{}正在进行其他操作,请稍后执行", basePodDetail.getPodCode());
            throw new BusinessException("该货架在进行其他操作中，请稍后执行");
        }
        logger.info("货架{}锁定成功,锁定源为{},版本号{}", basePodDetail.getPodCode(), basePodDetail.getLockSource(), updateBasePodDetail.getVersion());
        return true;
    }

    /**
     * 根据货架号解锁货架
     * @param
     */
    public boolean unlockPodByCode(String podCode) {
        int changeRow = basePodDetailMapper.unlockPodByCode(podCode);
        if (changeRow > 0) {
            return true;
        }
        return false;
    }

    /**
     * 根据子任务编号解锁货架
     * @param subTaskName
     */
    public boolean unlockPod(String subTaskName) {
        int changeRow = basePodDetailMapper.unlockPod(subTaskName);
        if (changeRow > 0) {
            logger.info("子任务{}解锁货架成功", subTaskName);
            return true;
        }
        return false;
    }


    /**
     * 获取区域的有货或无货的货架并锁定
     * @param lockPodConditions 获取条件
     * @return
     */
    public Result lockPodByCondition(List<LockPodCondition> lockPodConditions) {
        BasePodDetail needLockPod = null;
        LockPodCondition tmpLockPodCondition = null;
        for (LockPodCondition lockPodCondition : lockPodConditions) {
            //校验参数是否缺失
            Result result = this.checkBaseLockCondition(lockPodCondition);
            if (result.getReturnCode() != HttpStatus.OK.value()) {
                return result;
            }
            if(Strings.isNullOrEmpty(lockPodCondition.getInStock())) {
                return new Result(400,"缺少货架是否有货的条件");
            }
            //查找符合条件的货架
            List<BasePodDetail> basePodDetails = basePodDetailMapper.selectByLockPodConfigtion(lockPodCondition);
            if (basePodDetails != null && basePodDetails.size() > 0) {
                //当前无选择策略,当查到多个符合条件的货架后,直接获取第一个
                needLockPod = basePodDetails.get(0);
                tmpLockPodCondition = lockPodCondition;
                break;
            }
        }
        if (needLockPod == null) {
            logger.error("锁定源为{},锁定的条件{}", lockPodConditions.get(0).getLockSource(), JSON.toJSONString(lockPodConditions));
            throw new BusinessException("找不到符合要求的货架");
        }
        logger.debug("开始锁定货架{},锁定源为{}", needLockPod.getPodCode(), tmpLockPodCondition.getLockSource());
        needLockPod.setLockSource(tmpLockPodCondition.getLockSource());
        //锁定货架操作
        lockPod(needLockPod);
        //返回被锁定的货架信息
        needLockPod.setLockSource(tmpLockPodCondition.getLockSource());
        needLockPod.setInLock(Integer.valueOf(CompanyFinancialStatusEnum.LOCK.getCode()));
        return new Result(needLockPod);
    }

    /**
     * 获取区域的有货或无货的货架并锁定（超越按x轴顺序）
     * @param lockPodConditions 获取条件
     * @return
     */
    public Result lockPodByConditions(List<LockPodCondition> lockPodConditions) {
        BasePodDetail needLockPod = null;
        LockPodCondition tmpLockPodCondition = null;
        for (LockPodCondition lockPodCondition : lockPodConditions) {
            //校验参数是否缺失
            Result result = this.checkBaseLockCondition(lockPodCondition);
            if (result.getReturnCode() != HttpStatus.OK.value()) {
                return result;
            }
            if(Strings.isNullOrEmpty(lockPodCondition.getInStock())) {
                return new Result(400,"缺少货架是否有货的条件");
            }
            //查找符合条件的货架
            List<BasePodDetail> basePodDetails = basePodDetailMapper.selectByLockPodConfigtion(lockPodCondition);
            if (basePodDetails != null && basePodDetails.size() > 0) {
                //按照x轴顺序取
                needLockPod = distanceRules(basePodDetails);
                tmpLockPodCondition = lockPodCondition;
                break;
            }
        }
        if (needLockPod == null) {
            logger.error("锁定源为{},锁定的条件{}", lockPodConditions.get(0).getLockSource(), JSON.toJSONString(lockPodConditions));
            throw new BusinessException("找不到符合要求的货架");
        }
        logger.debug("开始锁定货架{},锁定源为{}", needLockPod.getPodCode(), tmpLockPodCondition.getLockSource());
        needLockPod.setLockSource(tmpLockPodCondition.getLockSource());
        //锁定货架操作
        lockPod(needLockPod);
        //返回被锁定的货架信息
        needLockPod.setLockSource(tmpLockPodCondition.getLockSource());
        needLockPod.setInLock(Integer.valueOf(CompanyFinancialStatusEnum.LOCK.getCode()));
        return new Result(needLockPod);
    }


    /**
     * 计算x值最小的位置
     * @param basePodDetailList
     * @return
     */
    public BasePodDetail distanceRules(List<BasePodDetail> basePodDetailList) {
        Optional<BasePodDetail> minMapBerth = basePodDetailList.stream().max((a,b) -> a.getCoox().compareTo(b.getCoox()));
        return minMapBerth.get();
    }

    /**
     * 计算x值最小的位置并按ber_group升序
     * @param baseMapBerthList
     * @return .sorted(Comparator.comparing(BaseMapBerth::getBerGroup))
     *
     */
    public BaseMapBerth distanceRuleByGroup(List<BaseMapBerth> baseMapBerthList) {
        Optional<BaseMapBerth> minMapBerth = baseMapBerthList.stream().max((a, b) -> a.getBerGroup().compareTo(b.getBerGroup()) & a.getCoox().compareTo(b.getCoox()));
        return minMapBerth.get();
    }

    public Result checkBaseLockCondition(BaseLockCondition baseLockCondition) {
        if(Strings.isNullOrEmpty(baseLockCondition.getMapCode())) {
            return new Result(400,"缺少地图编码");
        }
        if(Strings.isNullOrEmpty(baseLockCondition.getLockSource())) {
            return new Result(400,"缺少锁定源");
        }
        return new Result();
    }

    /**
     * 获取区域的空闲储位并锁定
     * @param baseMapBerthList
     * @return
     */
    @Override
    public Result lockEmptyStorageByBizTypeList(List<LockMapBerthCondition> baseMapBerthList) {

        LockMapBerthCondition selectLockMapBerthCondition = new LockMapBerthCondition();
        BaseMapBerth selectBaseMapBerth = null;

        for (LockMapBerthCondition lockMapBerthCondition:baseMapBerthList) {
            //校验参数是否缺失
            Result result = this.checkBaseLockCondition(lockMapBerthCondition);
            if (result.getReturnCode() != HttpStatus.OK.value()) {
                return result;
            }
            if(Strings.isNullOrEmpty(lockMapBerthCondition.getBizType())) {
                return new Result(400,"缺少berthTypeValue");
            }
            if (Strings.isNullOrEmpty(lockMapBerthCondition.getMapCode())) {
                return new Result(400, "缺少地图编码");
            }

            //根据传入的条件找到符合储位
            List<BaseMapBerth> selectBaseMapBerths = baseMapBerthMapper.selectEmptyStorage(lockMapBerthCondition);
            if(selectBaseMapBerths.size() > 0) {
                selectLockMapBerthCondition = lockMapBerthCondition;
                selectBaseMapBerth = selectBaseMapBerths.get(0);
                break;
            }
        }
        if (selectBaseMapBerth == null) {
            return new Result(400, "找不到空闲储位!");
        }
        //锁住选中的储位
        LockStorageDto lockStorageDto = new LockStorageDto();
        lockStorageDto.setMapCode(selectBaseMapBerth.getMapCode());
        lockStorageDto.setBerCode(selectBaseMapBerth.getBerCode());
        lockStorageDto.setPodCode(selectLockMapBerthCondition.getPodCode());
        lockStorageDto.setLockSource(selectLockMapBerthCondition.getLockSource());
        Result lockResult = lockMapBerth(lockStorageDto);
        Preconditions.checkBusinessError(lockResult.getReturnCode() != 200,lockResult.getReturnMsg());
        return new Result(selectBaseMapBerth);
    }

    /**
     *超越  获取区域的空闲储位并锁定（按照x轴顺序）
     * @param baseMapBerthList
     * @return
     */
    @Override
    public Result lockEmptyStorageByBizTypList(List<LockMapBerthCondition> baseMapBerthList) {

        LockMapBerthCondition selectLockMapBerthCondition = new LockMapBerthCondition();
        BaseMapBerth selectBaseMapBerth = null;

        for (LockMapBerthCondition lockMapBerthCondition:baseMapBerthList) {
            //校验参数是否缺失
            Result result = this.checkBaseLockCondition(lockMapBerthCondition);
            if (result.getReturnCode() != HttpStatus.OK.value()) {
                return result;
            }
            if (Strings.isNullOrEmpty(lockMapBerthCondition.getMapCode())) {
                return new Result(400, "缺少地图编码");
            }

            //根据传入的条件找到符合储位
            List<BaseMapBerth> selectBaseMapBerths = baseMapBerthMapper.selectEmptyStorage(lockMapBerthCondition);
            if(selectBaseMapBerths.size() > 0) {
                selectLockMapBerthCondition = lockMapBerthCondition;
                if (lockMapBerthCondition.getBizType().equals(InspurBizConstants.BizTypeConstants.AGINGCACHEAREA)) {
                    selectBaseMapBerth = distanceRuleDesc(selectBaseMapBerths);
                } else {
                    selectBaseMapBerth = distanceRule(selectBaseMapBerths);
                }
                break;
            }
        }
        if (selectBaseMapBerth == null) {
            return new Result(400, "找不到空闲储位!");
        }
        //锁住选中的储位
        LockStorageDto lockStorageDto = new LockStorageDto();
        lockStorageDto.setMapCode(selectBaseMapBerth.getMapCode());
        lockStorageDto.setBerCode(selectBaseMapBerth.getBerCode());
        lockStorageDto.setPodCode(selectLockMapBerthCondition.getPodCode());
        lockStorageDto.setLockSource(selectLockMapBerthCondition.getLockSource());
        Result lockResult = lockMapBerth(lockStorageDto);
        Preconditions.checkBusinessError(lockResult.getReturnCode() != 200,lockResult.getReturnMsg());
        return new Result(selectBaseMapBerth);
    }


    /**
     *  超越  获取区域的空闲储位并锁定
     * @param baseMapBerthList
     * @return
     */
    @Override
    public Result lockEmptyStorageByOperateAreaList(List<LockMapBerthCondition> baseMapBerthList) {
        LockMapBerthCondition selectLockMapBerthCondition = new LockMapBerthCondition();
        BaseMapBerth selectBaseMapBerth = null;
        for (LockMapBerthCondition lockMapBerthCondition:baseMapBerthList) {
            //根据传入的条件找到符合储位
            List<BaseMapBerth> selectBaseMapBerths = baseMapBerthMapper.selectEmptyStorage(lockMapBerthCondition);
            if(selectBaseMapBerths.size() > 0) {
                selectLockMapBerthCondition = lockMapBerthCondition;
                selectBaseMapBerth = distanceRule(selectBaseMapBerths);
                break;
            }
        }
        if (selectBaseMapBerth == null) {
            return new Result(400, "找不到空闲储位!");
        }
        //锁住选中的储位
        LockStorageDto lockStorageDto = new LockStorageDto();
        lockStorageDto.setMapCode(selectBaseMapBerth.getMapCode());
        lockStorageDto.setBerCode(selectBaseMapBerth.getBerCode());
        lockStorageDto.setPodCode(selectLockMapBerthCondition.getPodCode());
       // lockStorageDto.setLockSource(selectLockMapBerthCondition.getLockSource());
        Result lockResult = lockMapBerth(lockStorageDto);
        Preconditions.checkBusinessError(lockResult.getReturnCode() != 200,lockResult.getReturnMsg());
        return new Result(selectBaseMapBerth);
    }

    /**
     *  超越  获取区域的空闲储位并锁定(人工插线到老化区)
     * @param baseMapBerthList
     * @return
     */
    @Override
    public Result lockEmptyStorageAgingByOperateAreaList(List<LockMapBerthCondition> baseMapBerthList) {
        LockMapBerthCondition selectLockMapBerthCondition = new LockMapBerthCondition();
        BaseMapBerth selectBaseMapBerth = null;
        for (LockMapBerthCondition lockMapBerthCondition:baseMapBerthList) {
            //根据传入的条件找到符合储位
            List<BaseMapBerth> selectBaseMapBerths = baseMapBerthMapper.selectEmptyStorageAging(lockMapBerthCondition);
            if(selectBaseMapBerths.size() > 0) {
                selectLockMapBerthCondition = lockMapBerthCondition;
                selectBaseMapBerth = distanceRule(selectBaseMapBerths);
                break;
            }
        }
        if (selectBaseMapBerth == null) {
            return new Result(400, "找不到空闲储位!");
        }
        //锁住选中的储位
        LockStorageDto lockStorageDto = new LockStorageDto();
        lockStorageDto.setMapCode(selectBaseMapBerth.getMapCode());
        lockStorageDto.setBerCode(selectBaseMapBerth.getBerCode());
        lockStorageDto.setPodCode(selectLockMapBerthCondition.getPodCode());
        // lockStorageDto.setLockSource(selectLockMapBerthCondition.getLockSource());
        Result lockResult = lockMapBerth(lockStorageDto);
        Preconditions.checkBusinessError(lockResult.getReturnCode() != 200,lockResult.getReturnMsg());
        return new Result(selectBaseMapBerth);
    }

    /**
     * 参数校验
     * @param baseMapBerth
     * @return
     */
    public Result validateParams(BaseMapBerthDTO baseMapBerth) {
        return getResult(baseMapBerth.getMapCode(), baseMapBerth.getBerCode(), baseMapBerth.getLockSource());
    }

    /**
     * 锁定储位的参数校验
     * @param lockStorageDto
     * @return
     */
    private Result validateLockParams(LockStorageDto lockStorageDto) {
        return getResult(lockStorageDto.getMapCode(), lockStorageDto.getBerCode(), lockStorageDto.getLockSource());
    }


    private Result getResult(String mapCode, String berCode, String lockSource) {
        if(Strings.isNullOrEmpty(mapCode)) {
            return new Result(400,"缺少地图编码");
        }
        if(Strings.isNullOrEmpty(berCode)) {
            return new Result(400,"缺少点位代码");
        }
        if(Strings.isNullOrEmpty(lockSource)) {
            return new Result(400,"缺少锁定源");
        }
        return new Result();
    }


    /**
     * 超越 锁定储位的参数校验
     * @param lockStorageDto
     * @return
     */
    private Result validateLockParamss(LockStorageDto lockStorageDto) {
        return getResult(lockStorageDto.getMapCode(), lockStorageDto.getBerCode());
    }


    private Result getResult(String mapCode, String berCode) {
        if(Strings.isNullOrEmpty(mapCode)) {
            return new Result(400,"缺少地图编码");
        }
        if(Strings.isNullOrEmpty(berCode)) {
            return new Result(400,"缺少点位代码");
        }
        return new Result();
    }

}
