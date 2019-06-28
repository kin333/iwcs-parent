package com.wisdom.iwcs.service.elevator.Impl;

import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.elevator.CrossFloorTaskStatusLog;
import com.wisdom.iwcs.domain.elevator.dto.CrossFloorTaskStatusLogDTO;
import com.wisdom.iwcs.mapper.elevator.CrossFloorTaskStatusLogMapper;
import com.wisdom.iwcs.mapstruct.elevator.CrossFloorTaskStatusLogMapStruct;
import com.wisdom.iwcs.service.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class CrossFloorTaskStatusLogService implements ICrossFloorTaskStatusLogService {
    private final Logger logger = LoggerFactory.getLogger(CrossFloorTaskStatusLogService.class);

    private final CrossFloorTaskStatusLogMapper crossFloorTaskStatusLogMapper;

    private final CrossFloorTaskStatusLogMapStruct crossFloorTaskStatusLogMapStruct;

    @Autowired
    public CrossFloorTaskStatusLogService(CrossFloorTaskStatusLogMapStruct crossFloorTaskStatusLogMapStruct, CrossFloorTaskStatusLogMapper crossFloorTaskStatusLogMapper) {
        this.crossFloorTaskStatusLogMapStruct = crossFloorTaskStatusLogMapStruct;
        this.crossFloorTaskStatusLogMapper = crossFloorTaskStatusLogMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link CrossFloorTaskStatusLogDTO }
     *
     * @return int
     */
    @Override
    public int insert(CrossFloorTaskStatusLogDTO record) {
        CrossFloorTaskStatusLog crossFloorTaskStatusLog = crossFloorTaskStatusLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        crossFloorTaskStatusLog.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        crossFloorTaskStatusLog.setCreatedTime(new Date());
//        crossFloorTaskStatusLog.setCreatedBy(userId);
//        crossFloorTaskStatusLog.setLastModifiedBy(userId);
//        crossFloorTaskStatusLog.setLastModifiedTime(new Date());

        int num = crossFloorTaskStatusLogMapper.insert(crossFloorTaskStatusLog);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<CrossFloorTaskStatusLogDTO> }
     *
     * @return int
     */
    @Override
    public int insertBatch(List<CrossFloorTaskStatusLogDTO> records) {
        List<CrossFloorTaskStatusLog> recordList = crossFloorTaskStatusLogMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
//            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
//            record.setCreatedBy(userId);
//            record.setLastModifiedBy(userId);
//            record.setLastModifiedTime(new Date());
        });

        int num = crossFloorTaskStatusLogMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link CrossFloorTaskStatusLogDTO }
     */
    @Override
    public CrossFloorTaskStatusLogDTO selectByPrimaryKey(Integer id) {

        CrossFloorTaskStatusLog crossFloorTaskStatusLog = crossFloorTaskStatusLogMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(crossFloorTaskStatusLog, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return crossFloorTaskStatusLogMapStruct.toDto(crossFloorTaskStatusLog);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link CrossFloorTaskStatusLogDTO }
     *
     * @return {@link List<CrossFloorTaskStatusLogDTO> }
     */
    @Override
    public List<CrossFloorTaskStatusLogDTO> selectSelective(CrossFloorTaskStatusLogDTO record) {
        CrossFloorTaskStatusLog crossFloorTaskStatusLog = crossFloorTaskStatusLogMapStruct.toEntity(record);

        List<CrossFloorTaskStatusLog> crossFloorTaskStatusLogList = crossFloorTaskStatusLogMapper.select(crossFloorTaskStatusLog);
        return crossFloorTaskStatusLogMapStruct.toDto(crossFloorTaskStatusLogList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link CrossFloorTaskStatusLogDTO }
     *
     * @return int
     */
    @Override
    public int updateByPrimaryKey(CrossFloorTaskStatusLogDTO record) {
        CrossFloorTaskStatusLog crossFloorTaskStatusLog = crossFloorTaskStatusLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        crossFloorTaskStatusLog.setLastModifiedBy(userId);
//        crossFloorTaskStatusLog.setLastModifiedTime(new Date());

        int num = crossFloorTaskStatusLogMapper.updateByPrimaryKey(crossFloorTaskStatusLog);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link CrossFloorTaskStatusLogDTO }
     *
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(CrossFloorTaskStatusLogDTO record) {
        CrossFloorTaskStatusLog crossFloorTaskStatusLog = crossFloorTaskStatusLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        crossFloorTaskStatusLog.setLastModifiedBy(userId);
//        crossFloorTaskStatusLog.setLastModifiedTime(new Date());

        int num = crossFloorTaskStatusLogMapper.updateByPrimaryKeySelective(crossFloorTaskStatusLog);
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
        int num = crossFloorTaskStatusLogMapper.deleteByPrimaryKey(id);
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
//        return crossFloorTaskStatusLogMapper.deleteLogicByPrimaryKey(id);
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
        return crossFloorTaskStatusLogMapper.deleteByIds(String.join(",", ids));
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
//        return crossFloorTaskStatusLogMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<CrossFloorTaskStatusLogDTO> }
     */
    @Override
    public GridReturnData<CrossFloorTaskStatusLogDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<CrossFloorTaskStatusLogDTO> mGridReturnData = new GridReturnData<>();
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

        List<CrossFloorTaskStatusLog> list = crossFloorTaskStatusLogMapper.selectPage(map);

        PageInfo<CrossFloorTaskStatusLog> pageInfo = new PageInfo<>(list);
        PageInfo<CrossFloorTaskStatusLogDTO> pageInfoFinal = new PageInfo<>(crossFloorTaskStatusLogMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
