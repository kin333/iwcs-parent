package com.wisdom.iwcs.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.SubConditionRouteKey;
import com.wisdom.iwcs.domain.task.dto.SubConditionRouteKeyDTO;
import com.wisdom.iwcs.mapper.task.SubConditionRouteKeyMapper;
import com.wisdom.iwcs.mapstruct.task.SubConditionRouteKeyMapStruct;
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
public class SubConditionRouteKeyService {
    private final Logger logger = LoggerFactory.getLogger(SubConditionRouteKeyService.class);

    private final SubConditionRouteKeyMapper subConditionRouteKeyMapper;

    private final SubConditionRouteKeyMapStruct subConditionRouteKeyMapStruct;

    @Autowired
    public SubConditionRouteKeyService(SubConditionRouteKeyMapStruct subConditionRouteKeyMapStruct, SubConditionRouteKeyMapper subConditionRouteKeyMapper) {
        this.subConditionRouteKeyMapStruct = subConditionRouteKeyMapStruct;
        this.subConditionRouteKeyMapper = subConditionRouteKeyMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link SubConditionRouteKeyDTO }
     *
     * @return int
     */
    public int insert(SubConditionRouteKeyDTO record) {
        SubConditionRouteKey subConditionRouteKey = subConditionRouteKeyMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = subConditionRouteKeyMapper.insert(subConditionRouteKey);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<SubConditionRouteKeyDTO> }
     *
     * @return int
     */
    public int insertBatch(List<SubConditionRouteKeyDTO> records) {
        List<SubConditionRouteKey> recordList = subConditionRouteKeyMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = subConditionRouteKeyMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link SubConditionRouteKeyDTO }
     */
    public SubConditionRouteKeyDTO selectByPrimaryKey(Integer id) {

        SubConditionRouteKey subConditionRouteKey = subConditionRouteKeyMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(subConditionRouteKey, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return subConditionRouteKeyMapStruct.toDto(subConditionRouteKey);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link SubConditionRouteKeyDTO }
     *
     * @return {@link List<SubConditionRouteKeyDTO> }
     */
    public List<SubConditionRouteKeyDTO> selectSelective(SubConditionRouteKeyDTO record) {
        SubConditionRouteKey subConditionRouteKey = subConditionRouteKeyMapStruct.toEntity(record);

        List<SubConditionRouteKey> subConditionRouteKeyList = subConditionRouteKeyMapper.select(subConditionRouteKey);
        return subConditionRouteKeyMapStruct.toDto(subConditionRouteKeyList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link SubConditionRouteKeyDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(SubConditionRouteKeyDTO record) {
        SubConditionRouteKey subConditionRouteKey = subConditionRouteKeyMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = subConditionRouteKeyMapper.updateByPrimaryKey(subConditionRouteKey);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link SubConditionRouteKeyDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(SubConditionRouteKeyDTO record) {
        SubConditionRouteKey subConditionRouteKey = subConditionRouteKeyMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = subConditionRouteKeyMapper.updateByPrimaryKeySelective(subConditionRouteKey);
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
        int num = subConditionRouteKeyMapper.deleteByPrimaryKey(id);
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
//        return subConditionRouteKeyMapper.deleteLogicByPrimaryKey(id);
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
        return subConditionRouteKeyMapper.deleteByIds(String.join(",", ids));
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
//        return subConditionRouteKeyMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<SubConditionRouteKeyDTO> }
     */
    public GridReturnData<SubConditionRouteKeyDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<SubConditionRouteKeyDTO> mGridReturnData = new GridReturnData<>();
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

        List<SubConditionRouteKey> list = subConditionRouteKeyMapper.selectPage(map);

        PageInfo<SubConditionRouteKey> pageInfo = new PageInfo<>(list);
        PageInfo<SubConditionRouteKeyDTO> pageInfoFinal = new PageInfo<>(subConditionRouteKeyMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
