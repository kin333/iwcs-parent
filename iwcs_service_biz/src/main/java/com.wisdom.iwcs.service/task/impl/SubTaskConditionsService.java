package com.wisdom.iwcs.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.SubTaskConditions;
import com.wisdom.iwcs.domain.task.dto.SubTaskConditionsDTO;
import com.wisdom.iwcs.mapper.task.SubTaskConditionsMapper;
import com.wisdom.iwcs.mapstruct.task.SubTaskConditionsMapStruct;
import com.wisdom.iwcs.service.security.SecurityUtils;
import com.wisdom.iwcs.service.task.ISubTaskConditionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class SubTaskConditionsService implements ISubTaskConditionsService {

    private final Logger logger = LoggerFactory.getLogger(SubTaskConditionsService.class);

    private final SubTaskConditionsMapper subTaskConditionsMapper;

    private final SubTaskConditionsMapStruct subTaskConditionsMapStruct;

    @Autowired
    public SubTaskConditionsService(SubTaskConditionsMapStruct subTaskConditionsMapStruct, SubTaskConditionsMapper subTaskConditionsMapper) {
        this.subTaskConditionsMapper = subTaskConditionsMapper;
        this.subTaskConditionsMapStruct = subTaskConditionsMapStruct;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link SubTaskConditionsDTO }
     *
     * @return int
     */
    @Override
    public int insert(SubTaskConditionsDTO record) {
        SubTaskConditions tsSubTaskConditions = subTaskConditionsMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        tsSubTaskConditions.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
//        tsSubTaskConditions.setCreatedTime(new Date());
//        tsSubTaskConditions.setCreatedBy(userId);
//        tsSubTaskConditions.setLastModifiedBy(userId);
//        tsSubTaskConditions.setLastModifiedTime(new Date());

        int num = subTaskConditionsMapper.insert(tsSubTaskConditions);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<SubTaskConditionsDTO> }
     *
     * @return int
     */
    @Override
    public int insertBatch(List<SubTaskConditionsDTO> records) {
        List<SubTaskConditions> recordList = subTaskConditionsMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
//        recordList.forEach(record -> {
//            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
//            record.setCreatedTime(new Date());
//            record.setCreatedBy(userId);
//            record.setLastModifiedBy(userId);
//            record.setLastModifiedTime(new Date());
//        });

        int num = subTaskConditionsMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link SubTaskConditionsDTO }
     */
    @Override
    public SubTaskConditionsDTO selectByPrimaryKey(Integer id) {

        SubTaskConditions tsSubTaskConditions = subTaskConditionsMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(tsSubTaskConditions, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return subTaskConditionsMapStruct.toDto(tsSubTaskConditions);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link SubTaskConditionsDTO }
     *
     * @return {@link List<SubTaskConditionsDTO> }
     */
    @Override
    public List<SubTaskConditionsDTO> selectSelective(SubTaskConditionsDTO record) {
        SubTaskConditions tsSubTaskConditions = subTaskConditionsMapStruct.toEntity(record);

        List<SubTaskConditions> tsSubTaskConditionsList = subTaskConditionsMapper.select(tsSubTaskConditions);
        return subTaskConditionsMapStruct.toDto(tsSubTaskConditionsList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link SubTaskConditionsDTO }
     *
     * @return int
     */
    @Override
    public int updateByPrimaryKey(SubTaskConditionsDTO record) {
        SubTaskConditions tsSubTaskConditions = subTaskConditionsMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        tsSubTaskConditions.setLastModifiedBy(userId);
//        tsSubTaskConditions.setLastModifiedTime(new Date());

        int num = subTaskConditionsMapper.updateByPrimaryKey(tsSubTaskConditions);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link SubTaskConditionsDTO }
     *
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(SubTaskConditionsDTO record) {
        SubTaskConditions tsSubTaskConditions = subTaskConditionsMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        tsSubTaskConditions.setLastModifiedBy(userId);
//        tsSubTaskConditions.setLastModifiedTime(new Date());

        int num = subTaskConditionsMapper.updateByPrimaryKeySelective(tsSubTaskConditions);
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
    @Override
    public int deleteByPrimaryKey(Integer id) {
        int num = subTaskConditionsMapper.deleteByPrimaryKey(id);
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
//        return subTaskConditionsMapper.deleteLogicByPrimaryKey(id);
//    }

    /**
     * 根据主键删除多条记录
     *
     *
     * @param ids {@link List<String> }
     *
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids){
        return subTaskConditionsMapper.deleteByIds(String.join(",", ids));
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
//        return subTaskConditionsMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<SubTaskConditionsDTO> }
     */
    @Override
    public GridReturnData<SubTaskConditionsDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<SubTaskConditionsDTO> mGridReturnData = new GridReturnData<>();
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

        List<SubTaskConditions> list = subTaskConditionsMapper.selectPage(map);

        PageInfo<SubTaskConditions> pageInfo = new PageInfo<>(list);
        PageInfo<SubTaskConditionsDTO> pageInfoFinal = new PageInfo<>(subTaskConditionsMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
