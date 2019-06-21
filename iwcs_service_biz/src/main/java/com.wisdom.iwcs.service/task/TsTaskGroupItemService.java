package com.wisdom.iwcs.service.task;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.TsTaskGroupItem;
import com.wisdom.iwcs.domain.task.dto.TsTaskGroupItemDTO;
import com.wisdom.iwcs.mapper.task.TsTaskGroupItemMapper;
import com.wisdom.iwcs.mapstruct.task.TsTaskGroupItemMapStruct;
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
public class TsTaskGroupItemService {
    private final Logger logger = LoggerFactory.getLogger(TsTaskGroupItemService.class);

    private final TsTaskGroupItemMapper tsTaskGroupItemMapper;

    private final TsTaskGroupItemMapStruct tsTaskGroupItemMapStruct;

    @Autowired
    public TsTaskGroupItemService(TsTaskGroupItemMapStruct tsTaskGroupItemMapStruct, TsTaskGroupItemMapper tsTaskGroupItemMapper) {
        this.tsTaskGroupItemMapStruct = tsTaskGroupItemMapStruct;
        this.tsTaskGroupItemMapper = tsTaskGroupItemMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link TsTaskGroupItemDTO }
     *
     * @return int
     */
    public int insert(TsTaskGroupItemDTO record) {
        TsTaskGroupItem tsTaskGroupItem = tsTaskGroupItemMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsTaskGroupItemMapper.insert(tsTaskGroupItem);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<TsTaskGroupItemDTO> }
     *
     * @return int
     */
    public int insertBatch(List<TsTaskGroupItemDTO> records) {
        List<TsTaskGroupItem> recordList = tsTaskGroupItemMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsTaskGroupItemMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link TsTaskGroupItemDTO }
     */
    public TsTaskGroupItemDTO selectByPrimaryKey(Integer id) {

        TsTaskGroupItem tsTaskGroupItem = tsTaskGroupItemMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(tsTaskGroupItem, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return tsTaskGroupItemMapStruct.toDto(tsTaskGroupItem);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link TsTaskGroupItemDTO }
     *
     * @return {@link List<TsTaskGroupItemDTO> }
     */
    public List<TsTaskGroupItemDTO> selectSelective(TsTaskGroupItemDTO record) {
        TsTaskGroupItem tsTaskGroupItem = tsTaskGroupItemMapStruct.toEntity(record);

        List<TsTaskGroupItem> tsTaskGroupItemList = tsTaskGroupItemMapper.select(tsTaskGroupItem);
        return tsTaskGroupItemMapStruct.toDto(tsTaskGroupItemList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link TsTaskGroupItemDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(TsTaskGroupItemDTO record) {
        TsTaskGroupItem tsTaskGroupItem = tsTaskGroupItemMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsTaskGroupItemMapper.updateByPrimaryKey(tsTaskGroupItem);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link TsTaskGroupItemDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(TsTaskGroupItemDTO record) {
        TsTaskGroupItem tsTaskGroupItem = tsTaskGroupItemMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsTaskGroupItemMapper.updateByPrimaryKeySelective(tsTaskGroupItem);
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
        int num = tsTaskGroupItemMapper.deleteByPrimaryKey(id);
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
//        return tsTaskGroupItemMapper.deleteLogicByPrimaryKey(id);
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
        return tsTaskGroupItemMapper.deleteByIds(String.join(",", ids));
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
//        return tsTaskGroupItemMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<TsTaskGroupItemDTO> }
     */
    public GridReturnData<TsTaskGroupItemDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<TsTaskGroupItemDTO> mGridReturnData = new GridReturnData<>();
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

        List<TsTaskGroupItem> list = tsTaskGroupItemMapper.selectPage(map);

        PageInfo<TsTaskGroupItem> pageInfo = new PageInfo<>(list);
        PageInfo<TsTaskGroupItemDTO> pageInfoFinal = new PageInfo<>(tsTaskGroupItemMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
