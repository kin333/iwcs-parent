package com.wisdom.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.task.WbAgvTaskMapStruct;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.WbAgvTask;
import com.wisdom.iwcs.domain.task.dto.WbAgvTaskDTO;
import com.wisdom.iwcs.mapper.task.WbAgvTaskMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.task.IWbAgvTaskService;
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
public class WbAgvTaskService implements IWbAgvTaskService {
    private final Logger logger = LoggerFactory.getLogger(WbAgvTaskService.class);

    private final WbAgvTaskMapper wbAgvTaskMapper;

    private final WbAgvTaskMapStruct wbAgvTaskMapStruct;

    @Autowired
    public WbAgvTaskService(WbAgvTaskMapStruct wbAgvTaskMapStruct, WbAgvTaskMapper wbAgvTaskMapper) {
        this.wbAgvTaskMapStruct = wbAgvTaskMapStruct;
        this.wbAgvTaskMapper = wbAgvTaskMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link WbAgvTaskDTO }
     * @return int
     */
    @Override
    public int insert(WbAgvTaskDTO record) {
        WbAgvTask wbAgvTask = wbAgvTaskMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        wbAgvTask.setCreatedTime(new Date());
        wbAgvTask.setCreatedBy(userId);
        wbAgvTask.setLastModifiedBy(userId);
        wbAgvTask.setLastModifiedTime(new Date());

        int num = wbAgvTaskMapper.insert(wbAgvTask);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<WbAgvTaskDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<WbAgvTaskDTO> records) {
        List<WbAgvTask> recordList = wbAgvTaskMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = wbAgvTaskMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link WbAgvTaskDTO }
     */
    @Override
    public WbAgvTaskDTO selectByPrimaryKey(Integer id) {

        WbAgvTask wbAgvTask = wbAgvTaskMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(wbAgvTask, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return wbAgvTaskMapStruct.toDto(wbAgvTask);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link WbAgvTaskDTO }
     * @return {@link List<WbAgvTaskDTO> }
     */
    @Override
    public List<WbAgvTaskDTO> selectSelective(WbAgvTaskDTO record) {
        WbAgvTask wbAgvTask = wbAgvTaskMapStruct.toEntity(record);

        List<WbAgvTask> wbAgvTaskList = wbAgvTaskMapper.select(wbAgvTask);
        return wbAgvTaskMapStruct.toDto(wbAgvTaskList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link WbAgvTaskDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(WbAgvTaskDTO record) {
        WbAgvTask wbAgvTask = wbAgvTaskMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        wbAgvTask.setLastModifiedBy(userId);
        wbAgvTask.setLastModifiedTime(new Date());

        int num = wbAgvTaskMapper.updateByPrimaryKey(wbAgvTask);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link WbAgvTaskDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(WbAgvTaskDTO record) {
        WbAgvTask wbAgvTask = wbAgvTaskMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        wbAgvTask.setLastModifiedBy(userId);
        wbAgvTask.setLastModifiedTime(new Date());

        int num = wbAgvTaskMapper.updateByPrimaryKeySelective(wbAgvTask);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     * @param id {@link Integer }
     * @return int
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        int num = wbAgvTaskMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }


    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return wbAgvTaskMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<WbAgvTaskDTO> }
     */
    @Override
    public GridReturnData<WbAgvTaskDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<WbAgvTaskDTO> mGridReturnData = new GridReturnData<>();
        List<GridFilterInfo> filterList = gridPageRequest.getFilterList();
        Map<String, Object> map = new HashMap<>(2);
        filterList.forEach(gridFilterInfo -> {
            if (gridFilterInfo.getFilterKey() != null && gridFilterInfo.getFilterValue() != null) {
                map.put(gridFilterInfo.getFilterKey(), gridFilterInfo.getFilterValue());
            }
        });
        map.put("searchKey", gridPageRequest.getSearchKey());
        // 对map中的参数的合法性进行校验

        String sortMyBatisByString = gridPageRequest.getSortMybatisString();
        PageHelper.startPage(gridPageRequest.getPageNum(), gridPageRequest.getPageSize(), sortMyBatisByString);

        List<WbAgvTask> list = wbAgvTaskMapper.selectPage(map);

        PageInfo<WbAgvTask> pageInfo = new PageInfo<>(list);
        PageInfo<WbAgvTaskDTO> pageInfoFinal = new PageInfo<>(wbAgvTaskMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 根据任务类型和状态获取当前agv任务(入库，出库，盘点，等等)
     *
     * @param record
     * @return
     */
    @Override
    public List<WbAgvTaskDTO> selectWbAgvTaskInfo(WbAgvTaskDTO record) {

        //判断任务类型是否存在
        Preconditions.checkBusinessError(record.getTaskType() == null || record.getTaskType().isEmpty(), "缺少任务类型");

        List<WbAgvTask> wbAgvTaskDTOS = wbAgvTaskMapper.selectWbAgvTaskInfo(record);
        return wbAgvTaskMapStruct.toDto(wbAgvTaskDTOS);
    }
}
