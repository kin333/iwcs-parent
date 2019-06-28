package com.wisdom.iwcs.service.elevator.Impl;

import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.elevator.Elevator;
import com.wisdom.iwcs.domain.elevator.dto.ElevatorDTO;
import com.wisdom.iwcs.mapper.elevator.ElevatorMapper;
import com.wisdom.iwcs.mapstruct.elevator.ElevatorMapStruct;
import com.wisdom.iwcs.service.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class ElevatorService implements com.wisdom.iwcs.service.elevator.IElevatorService {
    private final Logger logger = LoggerFactory.getLogger(ElevatorService.class);

    private final ElevatorMapper elevatorMapper;

    private final ElevatorMapStruct elevatorMapStruct;

    @Autowired
    public ElevatorService(ElevatorMapStruct elevatorMapStruct, ElevatorMapper elevatorMapper) {
        this.elevatorMapStruct = elevatorMapStruct;
        this.elevatorMapper = elevatorMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link ElevatorDTO }
     *
     * @return int
     */
    @Override
    public int insert(ElevatorDTO record) {
        Elevator elevator = elevatorMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        elevator.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
//        elevator.setCreatedTime(new Date());
//        elevator.setCreatedBy(userId);
//        elevator.setLastModifiedBy(userId);
//        elevator.setLastModifiedTime(new Date());

        int num = elevatorMapper.insert(elevator);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<ElevatorDTO> }
     *
     * @return int
     */
    @Override
    public int insertBatch(List<ElevatorDTO> records) {
        List<Elevator> recordList = elevatorMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
//            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
//            record.setCreatedTime(new Date());
//            record.setCreatedBy(userId);
//            record.setLastModifiedBy(userId);
//            record.setLastModifiedTime(new Date());
        });

        int num = elevatorMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link ElevatorDTO }
     */
    @Override
    public ElevatorDTO selectByPrimaryKey(Integer id) {

        Elevator elevator = elevatorMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(elevator, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return elevatorMapStruct.toDto(elevator);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link ElevatorDTO }
     *
     * @return {@link List<ElevatorDTO> }
     */
    @Override
    public List<ElevatorDTO> selectSelective(ElevatorDTO record) {
        Elevator elevator = elevatorMapStruct.toEntity(record);

        List<Elevator> elevatorList = elevatorMapper.select(elevator);
        return elevatorMapStruct.toDto(elevatorList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link ElevatorDTO }
     *
     * @return int
     */
    @Override
    public int updateByPrimaryKey(ElevatorDTO record) {
        Elevator elevator = elevatorMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        elevator.setLastModifiedBy(userId);
//        elevator.setLastModifiedTime(new Date());

        int num = elevatorMapper.updateByPrimaryKey(elevator);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link ElevatorDTO }
     *
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(ElevatorDTO record) {
        Elevator elevator = elevatorMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        elevator.setLastModifiedBy(userId);
//        elevator.setLastModifiedTime(new Date());

        int num = elevatorMapper.updateByPrimaryKeySelective(elevator);
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
        int num = elevatorMapper.deleteByPrimaryKey(id);
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
//        return elevatorMapper.deleteLogicByPrimaryKey(id);
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
        return elevatorMapper.deleteByIds(String.join(",", ids));
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
//        return elevatorMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<ElevatorDTO> }
     */
    @Override
    public GridReturnData<ElevatorDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<ElevatorDTO> mGridReturnData = new GridReturnData<>();
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

        List<Elevator> list = elevatorMapper.selectPage(map);

        PageInfo<Elevator> pageInfo = new PageInfo<>(list);
        PageInfo<ElevatorDTO> pageInfoFinal = new PageInfo<>(elevatorMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
