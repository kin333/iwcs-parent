package com.wisdom.iwcs.service.task;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.TsSubTaskLog;
import com.wisdom.iwcs.domain.task.dto.TsSubTaskLogDTO;
import com.wisdom.iwcs.mapper.task.TsSubTaskLogMapper;
import com.wisdom.iwcs.mapstruct.task.TsSubTaskLogMapStruct;
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
public class TsSubTaskLogService {
    private final Logger logger = LoggerFactory.getLogger(TsSubTaskLogService.class);

    private final TsSubTaskLogMapper tsSubTaskLogMapper;

    private final TsSubTaskLogMapStruct tsSubTaskLogMapStruct;

    @Autowired
    public TsSubTaskLogService(TsSubTaskLogMapStruct tsSubTaskLogMapStruct, TsSubTaskLogMapper tsSubTaskLogMapper) {
        this.tsSubTaskLogMapStruct = tsSubTaskLogMapStruct;
        this.tsSubTaskLogMapper = tsSubTaskLogMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link TsSubTaskLogDTO }
     *
     * @return int
     */
    public int insert(TsSubTaskLogDTO record) {
        TsSubTaskLog tsSubTaskLog = tsSubTaskLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsSubTaskLogMapper.insert(tsSubTaskLog);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<TsSubTaskLogDTO> }
     *
     * @return int
     */
    public int insertBatch(List<TsSubTaskLogDTO> records) {
        List<TsSubTaskLog> recordList = tsSubTaskLogMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsSubTaskLogMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link TsSubTaskLogDTO }
     */
    public TsSubTaskLogDTO selectByPrimaryKey(Integer id) {

        TsSubTaskLog tsSubTaskLog = tsSubTaskLogMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(tsSubTaskLog, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return tsSubTaskLogMapStruct.toDto(tsSubTaskLog);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link TsSubTaskLogDTO }
     *
     * @return {@link List<TsSubTaskLogDTO> }
     */
    public List<TsSubTaskLogDTO> selectSelective(TsSubTaskLogDTO record) {
        TsSubTaskLog tsSubTaskLog = tsSubTaskLogMapStruct.toEntity(record);

        List<TsSubTaskLog> tsSubTaskLogList = tsSubTaskLogMapper.select(tsSubTaskLog);
        return tsSubTaskLogMapStruct.toDto(tsSubTaskLogList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link TsSubTaskLogDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(TsSubTaskLogDTO record) {
        TsSubTaskLog tsSubTaskLog = tsSubTaskLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsSubTaskLogMapper.updateByPrimaryKey(tsSubTaskLog);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link TsSubTaskLogDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(TsSubTaskLogDTO record) {
        TsSubTaskLog tsSubTaskLog = tsSubTaskLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsSubTaskLogMapper.updateByPrimaryKeySelective(tsSubTaskLog);
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
        int num = tsSubTaskLogMapper.deleteByPrimaryKey(id);
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
//        return tsSubTaskLogMapper.deleteLogicByPrimaryKey(id);
//    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     *
     * @return int
     */
    public int deleteMore(List<String> ids){
        return tsSubTaskLogMapper.deleteByIds(String.join(",", ids));
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
//        return tsSubTaskLogMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<TsSubTaskLogDTO> }
     */
    public GridReturnData<TsSubTaskLogDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<TsSubTaskLogDTO> mGridReturnData = new GridReturnData<>();
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

        List<TsSubTaskLog> list = tsSubTaskLogMapper.selectPage(map);

        PageInfo<TsSubTaskLog> pageInfo = new PageInfo<>(list);
        PageInfo<TsSubTaskLogDTO> pageInfoFinal = new PageInfo<>(tsSubTaskLogMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
