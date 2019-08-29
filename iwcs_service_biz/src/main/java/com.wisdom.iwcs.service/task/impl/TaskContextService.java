package com.wisdom.iwcs.service.task.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.TaskContext;
import com.wisdom.iwcs.domain.task.dto.PublicContextDTO;
import com.wisdom.iwcs.domain.task.dto.TaskContextDTO;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapper.task.TaskContextMapper;
import com.wisdom.iwcs.mapstruct.task.TaskContextMapStruct;
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

import com.wisdom.iwcs.common.utils.exception.Preconditions;

@Service
@Transactional(rollbackFor = Exception.class)
public class TaskContextService {
    private final Logger logger = LoggerFactory.getLogger(TaskContextService.class);

    private final TaskContextMapper taskContextMapper;

    private final TaskContextMapStruct taskContextMapStruct;

    @Autowired
    SubTaskMapper subTaskMapper;

    @Autowired
    public TaskContextService(TaskContextMapStruct taskContextMapStruct, TaskContextMapper taskContextMapper) {
        this.taskContextMapStruct = taskContextMapStruct;
        this.taskContextMapper = taskContextMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link TaskContextDTO }
     *
     * @return int
     */
    public int insert(TaskContextDTO record) {
        TaskContext taskContext = taskContextMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        taskContext.setLastModifiedTime(new Date());

        int num = taskContextMapper.insert(taskContext);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<TaskContextDTO> }
     *
     * @return int
     */
    public int insertBatch(List<TaskContextDTO> records) {
        List<TaskContext> recordList = taskContextMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setLastModifiedTime(new Date());
        });

        int num = taskContextMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link TaskContextDTO }
     */
    public TaskContextDTO selectByPrimaryKey(Integer id) {

        TaskContext taskContext = taskContextMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(taskContext, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return taskContextMapStruct.toDto(taskContext);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link TaskContextDTO }
     *
     * @return {@link List<TaskContextDTO> }
     */
    public List<TaskContextDTO> selectSelective(TaskContextDTO record) {
        TaskContext taskContext = taskContextMapStruct.toEntity(record);

        List<TaskContext> taskContextList = taskContextMapper.select(taskContext);
        return taskContextMapStruct.toDto(taskContextList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link TaskContextDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(TaskContextDTO record) {
        TaskContext taskContext = taskContextMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        taskContext.setLastModifiedTime(new Date());

        int num = taskContextMapper.updateByPrimaryKey(taskContext);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link TaskContextDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(TaskContextDTO record) {
        TaskContext taskContext = taskContextMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        taskContext.setLastModifiedTime(new Date());

        int num = taskContextMapper.updateByPrimaryKeySelective(taskContext);
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
        int num = taskContextMapper.deleteByPrimaryKey(id);
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
//        return taskContextMapper.deleteLogicByPrimaryKey(id);
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
        return taskContextMapper.deleteByIds(String.join(",", ids));
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
//        return taskContextMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<TaskContextDTO> }
     */
    public GridReturnData<TaskContextDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<TaskContextDTO> mGridReturnData = new GridReturnData<>();
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

        List<TaskContext> list = taskContextMapper.selectPage(map);

        PageInfo<TaskContext> pageInfo = new PageInfo<>(list);
        PageInfo<TaskContextDTO> pageInfoFinal = new PageInfo<>(taskContextMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 获取context对象
     * @param subTaskNum
     * @return
     */
    public PublicContextDTO getPublicContext(String subTaskNum) {
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskNum);
        TaskContext taskContext = taskContextMapper.selectByMainTaskNum(subTask.getMainTaskNum());
        String context = taskContext.getContext();
        return JSONObject.parseObject(context, PublicContextDTO.class);
    }
}
