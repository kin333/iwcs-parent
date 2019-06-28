package com.wisdom.iwcs.service.elevator.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.elevator.ConnectionPoint;
import com.wisdom.iwcs.domain.elevator.dto.ConnectionPointDTO;
import com.wisdom.iwcs.mapper.elevator.ConnectionPointMapper;
import com.wisdom.iwcs.mapstruct.elevator.ConnectionPointMapStruct;
import com.wisdom.iwcs.service.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class ConnectionPointService implements com.wisdom.iwcs.service.elevator.IConnectionPointService {

    private final Logger logger = LoggerFactory.getLogger(ConnectionPointService.class);

    private final ConnectionPointMapper connectionPointMapper;

    private final ConnectionPointMapStruct connectionPointMapStruct;

    @Autowired
    public ConnectionPointService(ConnectionPointMapStruct connectionPointMapStruct, ConnectionPointMapper connectionPointMapper) {
        this.connectionPointMapStruct = connectionPointMapStruct;
        this.connectionPointMapper = connectionPointMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link ConnectionPointDTO }
     *
     * @return int
     */
    @Override
    public int insert(ConnectionPointDTO record) {
        ConnectionPoint connectionPoint = connectionPointMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        connectionPoint.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
//        connectionPoint.setCreatedTime(new Date());
//        connectionPoint.setCreatedBy(userId);
//        connectionPoint.setLastModifiedBy(userId);
//        connectionPoint.setLastModifiedTime(new Date());

        int num = connectionPointMapper.insert(connectionPoint);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<ConnectionPointDTO> }
     *
     * @return int
     */
    @Override
    public int insertBatch(List<ConnectionPointDTO> records) {
        List<ConnectionPoint> recordList = connectionPointMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
//        recordList.forEach(record -> {
//            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
//            record.setCreatedTime(new Date());
//            record.setCreatedBy(userId);
//            record.setLastModifiedBy(userId);
//            record.setLastModifiedTime(new Date());
//        });

        int num = connectionPointMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link ConnectionPointDTO }
     */
    @Override
    public ConnectionPointDTO selectByPrimaryKey(Integer id) {

        ConnectionPoint connectionPoint = connectionPointMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(connectionPoint, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return connectionPointMapStruct.toDto(connectionPoint);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link ConnectionPointDTO }
     *
     * @return {@link List<ConnectionPointDTO> }
     */
    @Override
    public List<ConnectionPointDTO> selectSelective(ConnectionPointDTO record) {
        ConnectionPoint connectionPoint = connectionPointMapStruct.toEntity(record);

        List<ConnectionPoint> connectionPointList = connectionPointMapper.select(connectionPoint);
        return connectionPointMapStruct.toDto(connectionPointList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link ConnectionPointDTO }
     *
     * @return int
     */
    @Override
    public int updateByPrimaryKey(ConnectionPointDTO record) {
        ConnectionPoint connectionPoint = connectionPointMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        connectionPoint.setLastModifiedBy(userId);
//        connectionPoint.setLastModifiedTime(new Date());

        int num = connectionPointMapper.updateByPrimaryKey(connectionPoint);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link ConnectionPointDTO }
     *
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(ConnectionPointDTO record) {
        ConnectionPoint connectionPoint = connectionPointMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        connectionPoint.setLastModifiedBy(userId);
//        connectionPoint.setLastModifiedTime(new Date());

        int num = connectionPointMapper.updateByPrimaryKeySelective(connectionPoint);
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
        int num = connectionPointMapper.deleteByPrimaryKey(id);
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
//        return connectionPointMapper.deleteLogicByPrimaryKey(id);
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
        return connectionPointMapper.deleteByIds(String.join(",", ids));
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
//        return connectionPointMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<ConnectionPointDTO> }
     */
    @Override
    public GridReturnData<ConnectionPointDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<ConnectionPointDTO> mGridReturnData = new GridReturnData<>();
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

        List<ConnectionPoint> list = connectionPointMapper.selectPage(map);

        PageInfo<ConnectionPoint> pageInfo = new PageInfo<>(list);
        PageInfo<ConnectionPointDTO> pageInfoFinal = new PageInfo<>(connectionPointMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
