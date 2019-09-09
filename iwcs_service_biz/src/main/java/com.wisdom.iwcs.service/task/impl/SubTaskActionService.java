package com.wisdom.iwcs.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.domain.task.SubTaskAction;
import com.wisdom.iwcs.domain.task.dto.SubTaskActionDTO;
import com.wisdom.iwcs.mapper.task.SubTaskActionMapper;
import com.wisdom.iwcs.mapstruct.task.SubTaskActionMapStruct;
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

import com.wisdom.iwcs.common.utils.exception.Preconditions;

@Service
@Transactional(rollbackFor = Exception.class)
public class SubTaskActionService {
    private final Logger logger = LoggerFactory.getLogger(SubTaskActionService.class);

    private final SubTaskActionMapper subTaskActionMapper;

    private final SubTaskActionMapStruct subTaskActionMapStruct;

    @Autowired
    public SubTaskActionService(SubTaskActionMapStruct subTaskActionMapStruct, SubTaskActionMapper subTaskActionMapper) {
        this.subTaskActionMapStruct = subTaskActionMapStruct;
        this.subTaskActionMapper = subTaskActionMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link SubTaskActionDTO }
     *
     * @return int
     */
    public int insert(SubTaskActionDTO record) {
        SubTaskAction subTaskAction = subTaskActionMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        subTaskAction.setCreateTime(new Date());
        subTaskAction.setLastModifiedTime(new Date());

        int num = subTaskActionMapper.insert(subTaskAction);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<SubTaskActionDTO> }
     *
     * @return int
     */
    public int insertBatch(List<SubTaskActionDTO> records) {
        List<SubTaskAction> recordList = subTaskActionMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreateTime(new Date());
            record.setLastModifiedTime(new Date());
        });

        int num = subTaskActionMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link SubTaskActionDTO }
     */
    public SubTaskActionDTO selectByPrimaryKey(Integer id) {

        SubTaskAction subTaskAction = subTaskActionMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(subTaskAction, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return subTaskActionMapStruct.toDto(subTaskAction);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link SubTaskActionDTO }
     *
     * @return {@link List<SubTaskActionDTO> }
     */
    public List<SubTaskActionDTO> selectSelective(SubTaskActionDTO record) {
        SubTaskAction subTaskAction = subTaskActionMapStruct.toEntity(record);

        List<SubTaskAction> subTaskActionList = subTaskActionMapper.select(subTaskAction);
        return subTaskActionMapStruct.toDto(subTaskActionList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link SubTaskActionDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(SubTaskActionDTO record) {
        SubTaskAction subTaskAction = subTaskActionMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        subTaskAction.setLastModifiedTime(new Date());

        int num = subTaskActionMapper.updateByPrimaryKey(subTaskAction);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link SubTaskActionDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(SubTaskActionDTO record) {
        SubTaskAction subTaskAction = subTaskActionMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        subTaskAction.setLastModifiedTime(new Date());

        int num = subTaskActionMapper.updateByPrimaryKeySelective(subTaskAction);
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
        int num = subTaskActionMapper.deleteByPrimaryKey(id);
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
//        return subTaskActionMapper.deleteLogicByPrimaryKey(id);
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
        return subTaskActionMapper.deleteByIds(String.join(",", ids));
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
//        return subTaskActionMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<SubTaskActionDTO> }
     */
    public GridReturnData<SubTaskActionDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<SubTaskActionDTO> mGridReturnData = new GridReturnData<>();
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

        List<SubTaskAction> list = subTaskActionMapper.selectPage(map);

        PageInfo<SubTaskAction> pageInfo = new PageInfo<>(list);
        PageInfo<SubTaskActionDTO> pageInfoFinal = new PageInfo<>(subTaskActionMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
