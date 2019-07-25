package com.wisdom.iwcs.service.elevator.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.elevator.Elevator;
import com.wisdom.iwcs.domain.elevator.dto.ElevatorDTO;
import com.wisdom.iwcs.mapper.elevator.ElevatorMapper;
import com.wisdom.iwcs.mapstruct.task.ElevatorMapStruct;
import com.wisdom.iwcs.service.security.SecurityUtils;
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
public class ElevatorService {
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
    public int insert(ElevatorDTO record) {
        Elevator elevator = elevatorMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

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
    public int insertBatch(List<ElevatorDTO> records) {
        List<Elevator> recordList = elevatorMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

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
    public int updateByPrimaryKey(ElevatorDTO record) {
        Elevator elevator = elevatorMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

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
    public int updateByPrimaryKeySelective(ElevatorDTO record) {
        Elevator elevator = elevatorMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

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
