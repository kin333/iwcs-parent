package com.wisdom.iwcs.service.task;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.TsTaskGroup;
import com.wisdom.iwcs.domain.task.dto.TsTaskGroupDTO;
import com.wisdom.iwcs.mapper.task.TsTaskGroupMapper;
import com.wisdom.iwcs.mapstruct.task.TsTaskGroupMapStruct;
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
public class TsTaskGroupService {
    private final Logger logger = LoggerFactory.getLogger(TsTaskGroupService.class);

    private final TsTaskGroupMapper tsTaskGroupMapper;

    private final TsTaskGroupMapStruct tsTaskGroupMapStruct;

    @Autowired
    public TsTaskGroupService(TsTaskGroupMapStruct tsTaskGroupMapStruct, TsTaskGroupMapper tsTaskGroupMapper) {
        this.tsTaskGroupMapStruct = tsTaskGroupMapStruct;
        this.tsTaskGroupMapper = tsTaskGroupMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link TsTaskGroupDTO }
     *
     * @return int
     */
    public int insert(TsTaskGroupDTO record) {
        TsTaskGroup tsTaskGroup = tsTaskGroupMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsTaskGroupMapper.insert(tsTaskGroup);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<TsTaskGroupDTO> }
     *
     * @return int
     */
    public int insertBatch(List<TsTaskGroupDTO> records) {
        List<TsTaskGroup> recordList = tsTaskGroupMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsTaskGroupMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link TsTaskGroupDTO }
     */
    public TsTaskGroupDTO selectByPrimaryKey(Integer id) {

        TsTaskGroup tsTaskGroup = tsTaskGroupMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(tsTaskGroup, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return tsTaskGroupMapStruct.toDto(tsTaskGroup);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link TsTaskGroupDTO }
     *
     * @return {@link List<TsTaskGroupDTO> }
     */
    public List<TsTaskGroupDTO> selectSelective(TsTaskGroupDTO record) {
        TsTaskGroup tsTaskGroup = tsTaskGroupMapStruct.toEntity(record);

        List<TsTaskGroup> tsTaskGroupList = tsTaskGroupMapper.select(tsTaskGroup);
        return tsTaskGroupMapStruct.toDto(tsTaskGroupList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link TsTaskGroupDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(TsTaskGroupDTO record) {
        TsTaskGroup tsTaskGroup = tsTaskGroupMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsTaskGroupMapper.updateByPrimaryKey(tsTaskGroup);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link TsTaskGroupDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(TsTaskGroupDTO record) {
        TsTaskGroup tsTaskGroup = tsTaskGroupMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsTaskGroupMapper.updateByPrimaryKeySelective(tsTaskGroup);
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
        int num = tsTaskGroupMapper.deleteByPrimaryKey(id);
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
//        return tsTaskGroupMapper.deleteLogicByPrimaryKey(id);
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
        return tsTaskGroupMapper.deleteByIds(String.join(",", ids));
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
//        return tsTaskGroupMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<TsTaskGroupDTO> }
     */
    public GridReturnData<TsTaskGroupDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<TsTaskGroupDTO> mGridReturnData = new GridReturnData<>();
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

        List<TsTaskGroup> list = tsTaskGroupMapper.selectPage(map);

        PageInfo<TsTaskGroup> pageInfo = new PageInfo<>(list);
        PageInfo<TsTaskGroupDTO> pageInfoFinal = new PageInfo<>(tsTaskGroupMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
