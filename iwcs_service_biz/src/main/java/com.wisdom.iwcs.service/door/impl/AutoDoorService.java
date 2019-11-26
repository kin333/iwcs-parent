package com.wisdom.iwcs.service.door.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.door.AutoDoor;
import com.wisdom.iwcs.domain.door.dto.AutoDoorDTO;
import com.wisdom.iwcs.mapper.door.AutoDoorMapper;
import com.wisdom.iwcs.mapstruct.door.AutoDoorMapStruct;
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
public class AutoDoorService {
    private final Logger logger = LoggerFactory.getLogger(AutoDoorService.class);

    private final AutoDoorMapper autoDoorMapper;

    private final AutoDoorMapStruct autoDoorMapStruct;

    @Autowired
    public AutoDoorService(AutoDoorMapStruct autoDoorMapStruct, AutoDoorMapper autoDoorMapper) {
        this.autoDoorMapStruct = autoDoorMapStruct;
        this.autoDoorMapper = autoDoorMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link AutoDoorDTO }
     *
     * @return int
     */
    public int insert(AutoDoorDTO record) {
        AutoDoor autoDoor = autoDoorMapStruct.toEntity(record);

        int num = autoDoorMapper.insert(autoDoor);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<AutoDoorDTO> }
     *
     * @return int
     */
    public int insertBatch(List<AutoDoorDTO> records) {
        List<AutoDoor> recordList = autoDoorMapStruct.toEntity(records);

        int num = autoDoorMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link AutoDoorDTO }
     */
    public AutoDoorDTO selectByPrimaryKey(Integer id) {

        AutoDoor autoDoor = autoDoorMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(autoDoor, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return autoDoorMapStruct.toDto(autoDoor);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link AutoDoorDTO }
     *
     * @return {@link List<AutoDoorDTO> }
     */
    public List<AutoDoorDTO> selectSelective(AutoDoorDTO record) {
        AutoDoor autoDoor = autoDoorMapStruct.toEntity(record);

        List<AutoDoor> autoDoorList = autoDoorMapper.select(autoDoor);
        return autoDoorMapStruct.toDto(autoDoorList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link AutoDoorDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(AutoDoorDTO record) {
        AutoDoor autoDoor = autoDoorMapStruct.toEntity(record);

        int num = autoDoorMapper.updateByPrimaryKey(autoDoor);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link AutoDoorDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(AutoDoorDTO record) {
        AutoDoor autoDoor = autoDoorMapStruct.toEntity(record);

        int num = autoDoorMapper.updateByPrimaryKeySelective(autoDoor);
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
        int num = autoDoorMapper.deleteByPrimaryKey(id);
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
        return autoDoorMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<AutoDoorDTO> }
     */
    public GridReturnData<AutoDoorDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<AutoDoorDTO> mGridReturnData = new GridReturnData<>();
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

        List<AutoDoor> list = autoDoorMapper.selectPage(map);

        PageInfo<AutoDoor> pageInfo = new PageInfo<>(list);
        PageInfo<AutoDoorDTO> pageInfoFinal = new PageInfo<>(autoDoorMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
