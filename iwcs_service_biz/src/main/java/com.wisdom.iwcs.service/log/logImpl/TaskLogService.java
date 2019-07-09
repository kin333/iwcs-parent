package com.wisdom.iwcs.service.log.logImpl;

import com.wisdom.iwcs.domain.log.TaskOperationLog;
import com.wisdom.iwcs.domain.log.dto.TaskOperationLogDTO;
import com.wisdom.iwcs.mapper.log.TaskLogMapper;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.mapstruct.log.TaskLogMapStruct;
import com.wisdom.iwcs.service.log.ITaskLogService;
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
public class TaskLogService implements ITaskLogService {
    private final Logger logger = LoggerFactory.getLogger(TaskLogService.class);

    private final TaskLogMapper taskLogMapper;

    private final TaskLogMapStruct taskLogMapStruct;

    @Autowired
    public TaskLogService(TaskLogMapStruct taskLogMapStruct, TaskLogMapper taskLogMapper) {
        this.taskLogMapStruct = taskLogMapStruct;
        this.taskLogMapper = taskLogMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link TaskOperationLogDTO }
     *
     * @return int
     */
    @Override
    public int insert(TaskOperationLogDTO record) {
        TaskOperationLog taskLog = taskLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        taskLog.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        taskLog.setCreatedTime(new Date());
        taskLog.setCreatedBy(userId);
        taskLog.setLastModifiedBy(userId);
        taskLog.setLastModifiedTime(new Date());

        int num = taskLogMapper.insert(taskLog);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List< TaskOperationLogDTO > }
     *
     * @return int
     */
    @Override
    public int insertBatch(List<TaskOperationLogDTO> records) {
        List<TaskOperationLog> recordList = taskLogMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = taskLogMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link TaskOperationLogDTO }
     */
    @Override
    public TaskOperationLogDTO selectByPrimaryKey(Integer id) {

        TaskOperationLog taskLog = taskLogMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(taskLog, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return taskLogMapStruct.toDto(taskLog);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link TaskOperationLogDTO }
     *
     * @return {@link List< TaskOperationLogDTO > }
     */
    @Override
    public List<TaskOperationLogDTO> selectSelective(TaskOperationLogDTO record) {
        TaskOperationLog taskLog = taskLogMapStruct.toEntity(record);

        List<TaskOperationLog> taskLogList = taskLogMapper.select(taskLog);
        return taskLogMapStruct.toDto(taskLogList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link TaskOperationLogDTO }
     *
     * @return int
     */
    @Override
    public int updateByPrimaryKey(TaskOperationLogDTO record) {
        TaskOperationLog taskLog = taskLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        taskLog.setLastModifiedBy(userId);
        taskLog.setLastModifiedTime(new Date());

        int num = taskLogMapper.updateByPrimaryKey(taskLog);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link TaskOperationLogDTO }
     *
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(TaskOperationLogDTO record) {
        TaskOperationLog taskLog = taskLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        taskLog.setLastModifiedBy(userId);
        taskLog.setLastModifiedTime(new Date());

        int num = taskLogMapper.updateByPrimaryKeySelective(taskLog);
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
        int num = taskLogMapper.deleteByPrimaryKey(id);
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
    @Override
    public int deleteLogicByPrimaryKey(Integer id) {
        return taskLogMapper.deleteLogicByPrimaryKey(id);
    }

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
        return taskLogMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     *
     * @param ids {@link List<String> }
     *
     * @return int
     */
    @Override
    public int deleteMoreLogic(List<String> ids){
        return taskLogMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData< TaskOperationLogDTO > }
     */
    @Override
    public GridReturnData<TaskOperationLogDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<TaskOperationLogDTO> mGridReturnData = new GridReturnData<>();
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

        List<TaskOperationLog> list = taskLogMapper.selectPage(map);

        PageInfo<TaskOperationLog> pageInfo = new PageInfo<>(list);
        PageInfo<TaskOperationLogDTO> pageInfoFinal = new PageInfo<>(taskLogMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
