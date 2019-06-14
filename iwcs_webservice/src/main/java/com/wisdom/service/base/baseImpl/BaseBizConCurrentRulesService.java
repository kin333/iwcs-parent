package com.wisdom.service.base.baseImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.base.BaseBizConCurrentRulesMapStruct;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.common.utils.podUtils.PodTaskLockEnum;
import com.wisdom.iwcs.domain.base.BaseBizConCurrentRules;
import com.wisdom.iwcs.domain.base.dto.BaseBizConCurrentRulesDTO;
import com.wisdom.iwcs.domain.base.dto.BizAllowExecutePodsDetailDTO;
import com.wisdom.iwcs.domain.task.WbTaskDetail;
import com.wisdom.iwcs.mapper.base.BaseBizConCurrentRulesMapper;
import com.wisdom.iwcs.mapper.task.WbTaskDetailMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.base.IBaseBizConCurrentRulesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class BaseBizConCurrentRulesService implements IBaseBizConCurrentRulesService {
    private final Logger logger = LoggerFactory.getLogger(BaseBizConCurrentRulesService.class);

    private final BaseBizConCurrentRulesMapper baseBizConCurrentRulesMapper;

    private final BaseBizConCurrentRulesMapStruct baseBizConCurrentRulesMapStruct;

    @Autowired
    private WbTaskDetailMapper wbTaskDetailMapper;

    @Autowired
    public BaseBizConCurrentRulesService(BaseBizConCurrentRulesMapStruct baseBizConCurrentRulesMapStruct, BaseBizConCurrentRulesMapper baseBizConCurrentRulesMapper) {
        this.baseBizConCurrentRulesMapStruct = baseBizConCurrentRulesMapStruct;
        this.baseBizConCurrentRulesMapper = baseBizConCurrentRulesMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link BaseBizConCurrentRulesDTO }
     * @return int
     */
    @Override
    public int insert(BaseBizConCurrentRulesDTO record) {
        BaseBizConCurrentRules baseBizConCurrentRules = baseBizConCurrentRulesMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseBizConCurrentRules.setCreatedTime(new Date());

        int num = baseBizConCurrentRulesMapper.insert(baseBizConCurrentRules);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<BaseBizConCurrentRulesDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<BaseBizConCurrentRulesDTO> records) {
        List<BaseBizConCurrentRules> recordList = baseBizConCurrentRulesMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
        });

        int num = baseBizConCurrentRulesMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link BaseBizConCurrentRulesDTO }
     */
    @Override
    public BaseBizConCurrentRulesDTO selectByPrimaryKey(Integer id) {

        BaseBizConCurrentRules baseBizConCurrentRules = baseBizConCurrentRulesMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(baseBizConCurrentRules, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return baseBizConCurrentRulesMapStruct.toDto(baseBizConCurrentRules);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link BaseBizConCurrentRulesDTO }
     * @return {@link List<BaseBizConCurrentRulesDTO> }
     */
    @Override
    public List<BaseBizConCurrentRulesDTO> selectSelective(BaseBizConCurrentRulesDTO record) {
        BaseBizConCurrentRules baseBizConCurrentRules = baseBizConCurrentRulesMapStruct.toEntity(record);

        List<BaseBizConCurrentRules> baseBizConCurrentRulesList = baseBizConCurrentRulesMapper.select(baseBizConCurrentRules);
        return baseBizConCurrentRulesMapStruct.toDto(baseBizConCurrentRulesList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link BaseBizConCurrentRulesDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(BaseBizConCurrentRulesDTO record) {
        BaseBizConCurrentRules baseBizConCurrentRules = baseBizConCurrentRulesMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();


        int num = baseBizConCurrentRulesMapper.updateByPrimaryKey(baseBizConCurrentRules);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link BaseBizConCurrentRulesDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(BaseBizConCurrentRulesDTO record) {
        BaseBizConCurrentRules baseBizConCurrentRules = baseBizConCurrentRulesMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();


        int num = baseBizConCurrentRulesMapper.updateByPrimaryKeySelective(baseBizConCurrentRules);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     * @param id {@link Integer }
     * @return int
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        int num = baseBizConCurrentRulesMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }


    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return baseBizConCurrentRulesMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<BaseBizConCurrentRulesDTO> }
     */
    @Override
    public GridReturnData<BaseBizConCurrentRulesDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<BaseBizConCurrentRulesDTO> mGridReturnData = new GridReturnData<>();
        List<GridFilterInfo> filterList = gridPageRequest.getFilterList();
        Map<String, Object> map = new HashMap<>(2);
        filterList.forEach(gridFilterInfo -> {
            if (gridFilterInfo.getFilterKey() != null && gridFilterInfo.getFilterValue() != null) {
                map.put(gridFilterInfo.getFilterKey(), gridFilterInfo.getFilterValue());
            }
        });
        map.put("searchKey", gridPageRequest.getSearchKey());
        // 对map中的参数的合法性进行校验

        String sortMyBatisByString = gridPageRequest.getSortMybatisString();
        PageHelper.startPage(gridPageRequest.getPageNum(), gridPageRequest.getPageSize(), sortMyBatisByString);

        List<BaseBizConCurrentRules> list = baseBizConCurrentRulesMapper.selectPage(map);

        PageInfo<BaseBizConCurrentRules> pageInfo = new PageInfo<>(list);
        PageInfo<BaseBizConCurrentRulesDTO> pageInfoFinal = new PageInfo<>(baseBizConCurrentRulesMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 根据任务类型返回不可同时发生的任务类型常量list
     *
     * @param srcBizTypeEnum
     * @return
     */
    @Override
    public List<PodTaskLockEnum> returnUnableConcurrentBizType(PodTaskLockEnum srcBizTypeEnum) {
        List<PodTaskLockEnum> unableConcurrentBizTypesEnumList = new ArrayList<>();
        List<String> unableConcurrentBizTypes = baseBizConCurrentRulesMapper.selectBySrcBizType(srcBizTypeEnum.getType());
        unableConcurrentBizTypes.stream().forEach(unableConcurrentBizType -> {
            PodTaskLockEnum unableConcurrentBizTypeEnum = PodTaskLockEnum.returnEnumByType(unableConcurrentBizType);
            unableConcurrentBizTypesEnumList.add(unableConcurrentBizTypeEnum);
        });
        return unableConcurrentBizTypesEnumList;
    }

    /**
     * 根据任务类型返回不可同时发生的任务类型常量list
     *
     * @param srcBizTypeEnum
     * @return
     */
    @Override
    public Integer returnUnableConcurrentBizTypeValuesSum(PodTaskLockEnum srcBizTypeEnum) {
        List<PodTaskLockEnum> unableConcurrentBizTypesEnumList = new ArrayList<>();
        List<String> unableConcurrentBizTypes = baseBizConCurrentRulesMapper.selectBySrcBizType(srcBizTypeEnum.getType());
        unableConcurrentBizTypes.stream().forEach(unableConcurrentBizType -> {
            PodTaskLockEnum unableConcurrentBizTypeEnum = PodTaskLockEnum.returnEnumByType(unableConcurrentBizType);
            unableConcurrentBizTypesEnumList.add(unableConcurrentBizTypeEnum);
        });
        int bizTypeValuesSum = unableConcurrentBizTypesEnumList.stream().map(e -> e.getTaskValue()).collect(Collectors.toList()).stream().mapToInt(t -> t.intValue()).sum();
        return bizTypeValuesSum;
    }

    /**
     * 根据想要执行的任务pod和任务类型，返回这批pod是否允许执行任务
     *
     * @param podCodes
     * @param srcBizTypeEnum
     * @return
     */
    @Override
    public boolean returnIfAllowExecuteByPodcodesAndSrcBizType(List<String> podCodes, PodTaskLockEnum srcBizTypeEnum) {
        boolean allowExecute = true;
        BizAllowExecutePodsDetailDTO bizAllowExecutePodsDetailDTO = returnIfAllowExecuteDetailByPodcodesAndSrcBizType(podCodes, srcBizTypeEnum);
        if (bizAllowExecutePodsDetailDTO.getNotAllowExecuteTaskPods().size() != 0) {
            allowExecute = false;
            return allowExecute;
        }
        return allowExecute;
    }

    private List<String> returnTaskPodsByTaskTypes(List<String> taskTypes) {
        List<WbTaskDetail> wbTaskDetails = wbTaskDetailMapper.selectUnCompletedTaskDetailByTaskTypes(taskTypes);
        List<String> taskPodCods = wbTaskDetails.stream().map(WbTaskDetail::getPodCode).collect(Collectors.toList());
        return taskPodCods;
    }

    /**
     * 根据任务pod和想要执行的任务类型，返回哪些货架允许执行，哪些货架不允许执行
     *
     * @param podCodes
     * @param srcBizTypeEnum
     * @return
     */
    @Override
    public BizAllowExecutePodsDetailDTO returnIfAllowExecuteDetailByPodcodesAndSrcBizType(List<String> podCodes, PodTaskLockEnum srcBizTypeEnum) {
        BizAllowExecutePodsDetailDTO bizAllowExecutePodsDetailDTO = new BizAllowExecutePodsDetailDTO();
        List<PodTaskLockEnum> unableConcurrentBizTypesEnumList = returnUnableConcurrentBizType(srcBizTypeEnum);
        if (unableConcurrentBizTypesEnumList.size() == 0) {
            bizAllowExecutePodsDetailDTO.setAllowExecuteTaskPods(podCodes);
            return bizAllowExecutePodsDetailDTO;
        }
        List<String> unableConcurrentBizTypes = unableConcurrentBizTypesEnumList.stream().map(PodTaskLockEnum::getType).collect(Collectors.toList());
        List<String> taskPods = returnTaskPodsByTaskTypes(unableConcurrentBizTypes);
        List<String> notAllowExecuteTaskPods = podCodes.stream().filter(podCode -> taskPods.contains(podCode)).collect(Collectors.toList());
        bizAllowExecutePodsDetailDTO.setNotAllowExecuteTaskPods(notAllowExecuteTaskPods);
        List<String> allowExecuteTaskPods = podCodes.stream().filter(podCode -> !taskPods.contains(podCode)).collect(Collectors.toList());
        bizAllowExecutePodsDetailDTO.setAllowExecuteTaskPods(allowExecuteTaskPods);

        return bizAllowExecutePodsDetailDTO;
    }

    /**
     * 根据工作台编号 任务类型 获取在该工作台未完成的任务信息
     */
    public List<WbTaskDetail> selectArrivedTaskByWbCodeAndTaskType(String wbCode, String taskType) {

        return wbTaskDetailMapper.selectArrivedTaskByWbCodeAndTaskType(wbCode, taskType);
    }

    /**
     * 通过bizOrderCode 查看工作台 正在工作的数量
     */
    public int numberOfJobs(String bizOrderCode, String taskType) {
        return wbTaskDetailMapper.numberOfJobs(bizOrderCode, taskType);
    }
}

