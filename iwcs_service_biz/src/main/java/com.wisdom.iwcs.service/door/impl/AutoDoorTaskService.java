package com.wisdom.iwcs.service.door.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.door.AutoDoor;
import com.wisdom.iwcs.domain.door.AutoDoorTask;
import com.wisdom.iwcs.domain.door.dto.AutoDoorTaskDTO;
import com.wisdom.iwcs.mapper.door.AutoDoorMapper;
import com.wisdom.iwcs.mapper.door.AutoDoorTaskMapper;
import com.wisdom.iwcs.mapstruct.door.AutoDoorTaskMapStruct;
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
public class AutoDoorTaskService {
    private final Logger logger = LoggerFactory.getLogger(AutoDoorTaskService.class);

    private final AutoDoorTaskMapper autoDoorTaskMapper;

    private final AutoDoorTaskMapStruct autoDoorTaskMapStruct;

    private final AutoDoorMapper autoDoorMapper;

    @Autowired
    public AutoDoorTaskService(AutoDoorTaskMapStruct autoDoorTaskMapStruct, AutoDoorTaskMapper autoDoorTaskMapper, AutoDoorMapper autoDoorMapper) {
        this.autoDoorTaskMapStruct = autoDoorTaskMapStruct;
        this.autoDoorTaskMapper = autoDoorTaskMapper;
        this.autoDoorMapper = autoDoorMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link AutoDoorTaskDTO }
     *
     * @return int
     */
    public int insert(AutoDoorTaskDTO record) {
        AutoDoorTask autoDoorTask = autoDoorTaskMapStruct.toEntity(record);

        int num = autoDoorTaskMapper.insert(autoDoorTask);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<AutoDoorTaskDTO> }
     *
     * @return int
     */
    public int insertBatch(List<AutoDoorTaskDTO> records) {
        List<AutoDoorTask> recordList = autoDoorTaskMapStruct.toEntity(records);

        int num = autoDoorTaskMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link AutoDoorTaskDTO }
     */
    public AutoDoorTaskDTO selectByPrimaryKey(Integer id) {

        AutoDoorTask autoDoorTask = autoDoorTaskMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(autoDoorTask, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return autoDoorTaskMapStruct.toDto(autoDoorTask);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link AutoDoorTaskDTO }
     *
     * @return {@link List<AutoDoorTaskDTO> }
     */
    public List<AutoDoorTaskDTO> selectSelective(AutoDoorTaskDTO record) {
        AutoDoorTask autoDoorTask = autoDoorTaskMapStruct.toEntity(record);

        List<AutoDoorTask> autoDoorTaskList = autoDoorTaskMapper.select(autoDoorTask);
        return autoDoorTaskMapStruct.toDto(autoDoorTaskList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link AutoDoorTaskDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(AutoDoorTaskDTO record) {
        AutoDoorTask autoDoorTask = autoDoorTaskMapStruct.toEntity(record);

        int num = autoDoorTaskMapper.updateByPrimaryKey(autoDoorTask);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 取消任务
     */

    public int cancalDoorTask(AutoDoorTaskDTO recode) {

        AutoDoor autoDoor = new AutoDoor();
        String doorCode = recode.getDoorCode();
        autoDoor.setDoorCode(doorCode);
        AutoDoor autoDoorList = autoDoorMapper.selectDataByCode(autoDoor);

        if (autoDoorList.getDoorModel().equals("2")) {
            return 500;
        }

        AutoDoorTask autoDoorTask = autoDoorTaskMapStruct.toEntity(recode);

        int num = autoDoorTaskMapper.cancalDoorTask(autoDoorTask);

        return num;
    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link AutoDoorTaskDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(AutoDoorTaskDTO record) {
        AutoDoorTask autoDoorTask = autoDoorTaskMapStruct.toEntity(record);

        int num = autoDoorTaskMapper.updateByPrimaryKeySelective(autoDoorTask);
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
        int num = autoDoorTaskMapper.deleteByPrimaryKey(id);
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
        return autoDoorTaskMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<AutoDoorTaskDTO> }
     */
    public GridReturnData<AutoDoorTaskDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<AutoDoorTaskDTO> mGridReturnData = new GridReturnData<>();
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

        List<AutoDoorTask> list = autoDoorTaskMapper.selectPage(map);

        PageInfo<AutoDoorTask> pageInfo = new PageInfo<>(list);
        PageInfo<AutoDoorTaskDTO> pageInfoFinal = new PageInfo<>(autoDoorTaskMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
