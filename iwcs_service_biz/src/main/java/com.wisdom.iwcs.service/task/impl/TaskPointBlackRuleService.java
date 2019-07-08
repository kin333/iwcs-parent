package com.wisdom.iwcs.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.TaskPointBlackRule;
import com.wisdom.iwcs.domain.task.dto.TaskPointBlackRuleDTO;
import com.wisdom.iwcs.mapper.task.TaskPointBlackRuleMapper;
import com.wisdom.iwcs.mapstruct.task.TaskPointBlackRuleMapStruct;
import com.wisdom.iwcs.service.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class TaskPointBlackRuleService {
    private final Logger logger = LoggerFactory.getLogger(TaskPointBlackRuleService.class);

    private final TaskPointBlackRuleMapper taskPointBlackRuleMapper;

    private final TaskPointBlackRuleMapStruct taskPointBlackRuleMapStruct;

    @Autowired
    public TaskPointBlackRuleService(TaskPointBlackRuleMapStruct taskPointBlackRuleMapStruct, TaskPointBlackRuleMapper taskPointBlackRuleMapper) {
        this.taskPointBlackRuleMapStruct = taskPointBlackRuleMapStruct;
        this.taskPointBlackRuleMapper = taskPointBlackRuleMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link TaskPointBlackRuleDTO }
     *
     * @return int
     */
    public int insert(TaskPointBlackRuleDTO record) {
        TaskPointBlackRule taskPointBlackRule = taskPointBlackRuleMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        taskPointBlackRule.setCreatedTime(new Date());
        taskPointBlackRule.setCreatedBy(userId);

        int num = taskPointBlackRuleMapper.insert(taskPointBlackRule);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<TaskPointBlackRuleDTO> }
     *
     * @return int
     */
    public int insertBatch(List<TaskPointBlackRuleDTO> records) {
        List<TaskPointBlackRule> recordList = taskPointBlackRuleMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
        });

        int num = taskPointBlackRuleMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link TaskPointBlackRuleDTO }
     */
    public TaskPointBlackRuleDTO selectByPrimaryKey(Integer id) {

        TaskPointBlackRule taskPointBlackRule = taskPointBlackRuleMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(taskPointBlackRule, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return taskPointBlackRuleMapStruct.toDto(taskPointBlackRule);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link TaskPointBlackRuleDTO }
     *
     * @return {@link List<TaskPointBlackRuleDTO> }
     */
    public List<TaskPointBlackRuleDTO> selectSelective(TaskPointBlackRuleDTO record) {
        TaskPointBlackRule taskPointBlackRule = taskPointBlackRuleMapStruct.toEntity(record);

        List<TaskPointBlackRule> taskPointBlackRuleList = taskPointBlackRuleMapper.select(taskPointBlackRule);
        return taskPointBlackRuleMapStruct.toDto(taskPointBlackRuleList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link TaskPointBlackRuleDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(TaskPointBlackRuleDTO record) {
        TaskPointBlackRule taskPointBlackRule = taskPointBlackRuleMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = taskPointBlackRuleMapper.updateByPrimaryKey(taskPointBlackRule);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link TaskPointBlackRuleDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(TaskPointBlackRuleDTO record) {
        TaskPointBlackRule taskPointBlackRule = taskPointBlackRuleMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = taskPointBlackRuleMapper.updateByPrimaryKeySelective(taskPointBlackRule);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     *
     * @param id {@link Integer }
     *
     * @return int
     */
    public int deleteByPrimaryKey(Integer id) {
        int num = taskPointBlackRuleMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键逻辑删除记录
     *
     *
     * @param id {@link Integer }
     *
     * @return int
     */
//    public int deleteLogicByPrimaryKey(Integer id) {
//        return taskPointBlackRuleMapper.deleteLogicByPrimaryKey(id);
//    }

    /**
     * 根据主键删除多条记录
     *
     *
     * @param ids {@link List<String> }
     *
     * @return int
     */
    public int deleteMore(List<String> ids){
        return taskPointBlackRuleMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     *
     * @param ids {@link List<String> }
     *
     * @return int
     */
//    public int deleteMoreLogic(List<String> ids){
//        return taskPointBlackRuleMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<TaskPointBlackRuleDTO> }
     */
    public GridReturnData<TaskPointBlackRuleDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<TaskPointBlackRuleDTO> mGridReturnData = new GridReturnData<>();
        List<GridFilterInfo> filterList = gridPageRequest.getFilterList();
        Map<String, Object> map = new HashMap<>(2);
        filterList.forEach(gridFilterInfo -> {
            if(gridFilterInfo.getFilterKey() != null && gridFilterInfo.getFilterValue() != null){
                map.put(gridFilterInfo.getFilterKey(), gridFilterInfo.getFilterValue());
            }
        });
        map.put("searchKey", gridPageRequest.getSearchKey());
        // 对map中的参数的合法性进行校验

        String sortMyBatisByString = gridPageRequest.getSortMybatisString();
        PageHelper.startPage(gridPageRequest.getPageNum(), gridPageRequest.getPageSize(), sortMyBatisByString);

        List<TaskPointBlackRule> list = taskPointBlackRuleMapper.selectPage(map);

        PageInfo<TaskPointBlackRule> pageInfo = new PageInfo<>(list);
        PageInfo<TaskPointBlackRuleDTO> pageInfoFinal = new PageInfo<>(taskPointBlackRuleMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
