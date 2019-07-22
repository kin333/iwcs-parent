package com.wisdom.iwcs.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.SubConditionEventLog;
import com.wisdom.iwcs.domain.task.dto.SubConditionEventLogDTO;
import com.wisdom.iwcs.mapper.task.SubConditionEventLogMapper;
import com.wisdom.iwcs.mapstruct.task.SubConditionEventLogMapStruct;
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
public class SubConditionEventLogService {
    private final Logger logger = LoggerFactory.getLogger(SubConditionEventLogService.class);

    private final SubConditionEventLogMapper subConditionEventLogMapper;

    private final SubConditionEventLogMapStruct subConditionEventLogMapStruct;

    @Autowired
    public SubConditionEventLogService(SubConditionEventLogMapStruct subConditionEventLogMapStruct, SubConditionEventLogMapper subConditionEventLogMapper) {
        this.subConditionEventLogMapStruct = subConditionEventLogMapStruct;
        this.subConditionEventLogMapper = subConditionEventLogMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link SubConditionEventLogDTO }
     *
     * @return int
     */
    public int insert(SubConditionEventLogDTO record) {
        SubConditionEventLog subConditionEventLog = subConditionEventLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        subConditionEventLog.setCreatedTime(new Date());

        int num = subConditionEventLogMapper.insert(subConditionEventLog);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<SubConditionEventLogDTO> }
     *
     * @return int
     */
    public int insertBatch(List<SubConditionEventLogDTO> records) {
        List<SubConditionEventLog> recordList = subConditionEventLogMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
        });

        int num = subConditionEventLogMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link SubConditionEventLogDTO }
     */
    public SubConditionEventLogDTO selectByPrimaryKey(Integer id) {

        SubConditionEventLog subConditionEventLog = subConditionEventLogMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(subConditionEventLog, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return subConditionEventLogMapStruct.toDto(subConditionEventLog);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link SubConditionEventLogDTO }
     *
     * @return {@link List<SubConditionEventLogDTO> }
     */
    public List<SubConditionEventLogDTO> selectSelective(SubConditionEventLogDTO record) {
        SubConditionEventLog subConditionEventLog = subConditionEventLogMapStruct.toEntity(record);

        List<SubConditionEventLog> subConditionEventLogList = subConditionEventLogMapper.select(subConditionEventLog);
        return subConditionEventLogMapStruct.toDto(subConditionEventLogList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link SubConditionEventLogDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(SubConditionEventLogDTO record) {
        SubConditionEventLog subConditionEventLog = subConditionEventLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = subConditionEventLogMapper.updateByPrimaryKey(subConditionEventLog);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link SubConditionEventLogDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(SubConditionEventLogDTO record) {
        SubConditionEventLog subConditionEventLog = subConditionEventLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = subConditionEventLogMapper.updateByPrimaryKeySelective(subConditionEventLog);
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
        int num = subConditionEventLogMapper.deleteByPrimaryKey(id);
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
//        return subConditionEventLogMapper.deleteLogicByPrimaryKey(id);
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
        return subConditionEventLogMapper.deleteByIds(String.join(",", ids));
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
//        return subConditionEventLogMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<SubConditionEventLogDTO> }
     */
    public GridReturnData<SubConditionEventLogDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<SubConditionEventLogDTO> mGridReturnData = new GridReturnData<>();
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

        List<SubConditionEventLog> list = subConditionEventLogMapper.selectPage(map);

        PageInfo<SubConditionEventLog> pageInfo = new PageInfo<>(list);
        PageInfo<SubConditionEventLogDTO> pageInfoFinal = new PageInfo<>(subConditionEventLogMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
