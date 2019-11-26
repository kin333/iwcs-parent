package com.wisdom.iwcs.service.door.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.door.DoorMsgLog;
import com.wisdom.iwcs.domain.door.dto.DoorMsgLogDTO;
import com.wisdom.iwcs.mapper.door.DoorMsgLogMapper;
import com.wisdom.iwcs.mapstruct.door.DoorMsgLogMapStruct;
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
public class DoorMsgLogService {
    private final Logger logger = LoggerFactory.getLogger(DoorMsgLogService.class);

    private final DoorMsgLogMapper doorMsgLogMapper;

    private final DoorMsgLogMapStruct doorMsgLogMapStruct;

    @Autowired
    public DoorMsgLogService(DoorMsgLogMapStruct doorMsgLogMapStruct, DoorMsgLogMapper doorMsgLogMapper) {
        this.doorMsgLogMapStruct = doorMsgLogMapStruct;
        this.doorMsgLogMapper = doorMsgLogMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link DoorMsgLogDTO }
     *
     * @return int
     */
    public int insert(DoorMsgLogDTO record) {
        DoorMsgLog doorMsgLog = doorMsgLogMapStruct.toEntity(record);

        int num = doorMsgLogMapper.insert(doorMsgLog);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<DoorMsgLogDTO> }
     *
     * @return int
     */
    public int insertBatch(List<DoorMsgLogDTO> records) {
        List<DoorMsgLog> recordList = doorMsgLogMapStruct.toEntity(records);

        int num = doorMsgLogMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link DoorMsgLogDTO }
     */
    public DoorMsgLogDTO selectByPrimaryKey(Integer id) {

        DoorMsgLog doorMsgLog = doorMsgLogMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(doorMsgLog, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return doorMsgLogMapStruct.toDto(doorMsgLog);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link DoorMsgLogDTO }
     *
     * @return {@link List<DoorMsgLogDTO> }
     */
    public List<DoorMsgLogDTO> selectSelective(DoorMsgLogDTO record) {
        DoorMsgLog doorMsgLog = doorMsgLogMapStruct.toEntity(record);

        List<DoorMsgLog> doorMsgLogList = doorMsgLogMapper.select(doorMsgLog);
        return doorMsgLogMapStruct.toDto(doorMsgLogList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link DoorMsgLogDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(DoorMsgLogDTO record) {
        DoorMsgLog doorMsgLog = doorMsgLogMapStruct.toEntity(record);

        int num = doorMsgLogMapper.updateByPrimaryKey(doorMsgLog);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link DoorMsgLogDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(DoorMsgLogDTO record) {
        DoorMsgLog doorMsgLog = doorMsgLogMapStruct.toEntity(record);

        int num = doorMsgLogMapper.updateByPrimaryKeySelective(doorMsgLog);
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
        int num = doorMsgLogMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除多条记录
     *
     *
     * @param ids {@link List<String> }
     *
     * @return int
     */
    public int deleteMore(List<String> ids){
        return doorMsgLogMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<DoorMsgLogDTO> }
     */
    public GridReturnData<DoorMsgLogDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<DoorMsgLogDTO> mGridReturnData = new GridReturnData<>();
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

        List<DoorMsgLog> list = doorMsgLogMapper.selectPage(map);

        PageInfo<DoorMsgLog> pageInfo = new PageInfo<>(list);
        PageInfo<DoorMsgLogDTO> pageInfoFinal = new PageInfo<>(doorMsgLogMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
