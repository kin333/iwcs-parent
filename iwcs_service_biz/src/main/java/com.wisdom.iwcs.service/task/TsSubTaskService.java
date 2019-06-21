package com.wisdom.iwcs.service.task;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.TsSubTask;
import com.wisdom.iwcs.domain.task.dto.TsSubTaskDTO;
import com.wisdom.iwcs.mapper.task.TsSubTaskMapper;
import com.wisdom.iwcs.mapstruct.task.TsSubTaskMapStruct;
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
public class TsSubTaskService {
    private final Logger logger = LoggerFactory.getLogger(TsSubTaskService.class);

    private final TsSubTaskMapper tsSubTaskMapper;

    private final TsSubTaskMapStruct tsSubTaskMapStruct;

    @Autowired
    public TsSubTaskService(TsSubTaskMapStruct tsSubTaskMapStruct, TsSubTaskMapper tsSubTaskMapper) {
        this.tsSubTaskMapStruct = tsSubTaskMapStruct;
        this.tsSubTaskMapper = tsSubTaskMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link TsSubTaskDTO }
     *
     * @return int
     */
    public int insert(TsSubTaskDTO record) {
        TsSubTask tsSubTask = tsSubTaskMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsSubTaskMapper.insert(tsSubTask);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<TsSubTaskDTO> }
     *
     * @return int
     */
    public int insertBatch(List<TsSubTaskDTO> records) {
        List<TsSubTask> recordList = tsSubTaskMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsSubTaskMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link TsSubTaskDTO }
     */
    public TsSubTaskDTO selectByPrimaryKey(Integer id) {

        TsSubTask tsSubTask = tsSubTaskMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(tsSubTask, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return tsSubTaskMapStruct.toDto(tsSubTask);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link TsSubTaskDTO }
     *
     * @return {@link List<TsSubTaskDTO> }
     */
    public List<TsSubTaskDTO> selectSelective(TsSubTaskDTO record) {
        TsSubTask tsSubTask = tsSubTaskMapStruct.toEntity(record);

        List<TsSubTask> tsSubTaskList = tsSubTaskMapper.select(tsSubTask);
        return tsSubTaskMapStruct.toDto(tsSubTaskList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link TsSubTaskDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(TsSubTaskDTO record) {
        TsSubTask tsSubTask = tsSubTaskMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsSubTaskMapper.updateByPrimaryKey(tsSubTask);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link TsSubTaskDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(TsSubTaskDTO record) {
        TsSubTask tsSubTask = tsSubTaskMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsSubTaskMapper.updateByPrimaryKeySelective(tsSubTask);
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
        int num = tsSubTaskMapper.deleteByPrimaryKey(id);
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
//        return tsSubTaskMapper.deleteLogicByPrimaryKey(id);
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
        return tsSubTaskMapper.deleteByIds(String.join(",", ids));
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
//        return tsSubTaskMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<TsSubTaskDTO> }
     */
    public GridReturnData<TsSubTaskDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<TsSubTaskDTO> mGridReturnData = new GridReturnData<>();
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

        List<TsSubTask> list = tsSubTaskMapper.selectPage(map);

        PageInfo<TsSubTask> pageInfo = new PageInfo<>(list);
        PageInfo<TsSubTaskDTO> pageInfoFinal = new PageInfo<>(tsSubTaskMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
