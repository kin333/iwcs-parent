package com.wisdom.iwcs.service.log.logImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.log.TaskOperationLog;
import com.wisdom.iwcs.domain.log.dto.TaskOperationLogDTO;
import com.wisdom.iwcs.mapper.log.TaskOperationLogMapper;
import com.wisdom.iwcs.mapstruct.log.TaskOperationLogMapStruct;
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
public class TaskOperationLogService {
    private final Logger logger = LoggerFactory.getLogger(TaskOperationLogService.class);

    private final TaskOperationLogMapper taskOperationLogMapper;

    private final TaskOperationLogMapStruct taskOperationLogMapStruct;

    @Autowired
    public TaskOperationLogService(TaskOperationLogMapStruct taskOperationLogMapStruct, TaskOperationLogMapper taskOperationLogMapper) {
        this.taskOperationLogMapStruct = taskOperationLogMapStruct;
        this.taskOperationLogMapper = taskOperationLogMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link TaskOperationLogDTO }
     *
     * @return int
     */
    public int insert(TaskOperationLogDTO record) {
        TaskOperationLog taskOperationLog = taskOperationLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        taskOperationLog.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        taskOperationLog.setCreatedTime(new Date());
        taskOperationLog.setCreatedBy(userId);
        taskOperationLog.setLastModifiedBy(userId);
        taskOperationLog.setLastModifiedTime(new Date());

        int num = taskOperationLogMapper.insert(taskOperationLog);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<TaskOperationLogDTO> }
     *
     * @return int
     */
    public int insertBatch(List<TaskOperationLogDTO> records) {
        List<TaskOperationLog> recordList = taskOperationLogMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = taskOperationLogMapper.insertList(recordList);
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
    public TaskOperationLogDTO selectByPrimaryKey(Integer id) {

        TaskOperationLog taskOperationLog = taskOperationLogMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(taskOperationLog, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return taskOperationLogMapStruct.toDto(taskOperationLog);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link TaskOperationLogDTO }
     *
     * @return {@link List<TaskOperationLogDTO> }
     */
    public List<TaskOperationLogDTO> selectSelective(TaskOperationLogDTO record) {
        TaskOperationLog taskOperationLog = taskOperationLogMapStruct.toEntity(record);

        List<TaskOperationLog> taskOperationLogList = taskOperationLogMapper.select(taskOperationLog);
        return taskOperationLogMapStruct.toDto(taskOperationLogList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link TaskOperationLogDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(TaskOperationLogDTO record) {
        TaskOperationLog taskOperationLog = taskOperationLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        taskOperationLog.setLastModifiedBy(userId);
        taskOperationLog.setLastModifiedTime(new Date());

        int num = taskOperationLogMapper.updateByPrimaryKey(taskOperationLog);
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
    public int updateByPrimaryKeySelective(TaskOperationLogDTO record) {
        TaskOperationLog taskOperationLog = taskOperationLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        taskOperationLog.setLastModifiedBy(userId);
        taskOperationLog.setLastModifiedTime(new Date());

        int num = taskOperationLogMapper.updateByPrimaryKeySelective(taskOperationLog);
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
        int num = taskOperationLogMapper.deleteByPrimaryKey(id);
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
    public int deleteLogicByPrimaryKey(Integer id) {
        return taskOperationLogMapper.deleteLogicByPrimaryKey(id);
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
        return taskOperationLogMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     *
     * @param ids {@link List<String> }
     *
     * @return int
     */
    public int deleteMoreLogic(List<String> ids){
        return taskOperationLogMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<TaskOperationLogDTO> }
     */
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

        List<TaskOperationLog> list = taskOperationLogMapper.selectPage(map);

        PageInfo<TaskOperationLog> pageInfo = new PageInfo<>(list);
        PageInfo<TaskOperationLogDTO> pageInfoFinal = new PageInfo<>(taskOperationLogMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
