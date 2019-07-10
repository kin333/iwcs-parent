package com.wisdom.iwcs.service.task.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.dto.MainTaskDTO;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import com.wisdom.iwcs.mapstruct.task.MainTaskMapStruct;
import com.wisdom.iwcs.service.security.SecurityUtils;
import com.wisdom.iwcs.service.task.intf.IMainTaskService;
import org.apache.commons.lang3.StringUtils;
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
public class MainTaskService implements IMainTaskService {
    private final Logger logger = LoggerFactory.getLogger(MainTaskService.class);

    private final MainTaskMapper mainTaskMapper;

    private final MainTaskMapStruct mainTaskMapStruct;

    @Autowired
    public MainTaskService(MainTaskMapStruct mainTaskMapStruct, MainTaskMapper mainTaskMapper) {
        this.mainTaskMapStruct = mainTaskMapStruct;
        this.mainTaskMapper = mainTaskMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link MainTaskDTO }
     *
     * @return int
     */
    @Override
    public int insert(MainTaskDTO record) {
        MainTask mainTask = mainTaskMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = mainTaskMapper.insert(mainTask);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<MainTaskDTO> }
     *
     * @return int
     */
    @Override
    public int insertBatch(List<MainTaskDTO> records) {
        List<MainTask> recordList = mainTaskMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = mainTaskMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link MainTaskDTO }
     */
    @Override
    public MainTaskDTO selectByPrimaryKey(Integer id) {

        MainTask mainTask = mainTaskMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(mainTask, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return mainTaskMapStruct.toDto(mainTask);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link MainTaskDTO }
     *
     * @return {@link List<MainTaskDTO> }
     */
    @Override
    public List<MainTaskDTO> selectSelective(MainTaskDTO record) {
        MainTask mainTask = mainTaskMapStruct.toEntity(record);

        List<MainTask> mainTaskList = mainTaskMapper.select(mainTask);
        return mainTaskMapStruct.toDto(mainTaskList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link MainTaskDTO }
     *
     * @return int
     */
    @Override
    public int updateByPrimaryKey(MainTaskDTO record) {
        MainTask mainTask = mainTaskMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = mainTaskMapper.updateByPrimaryKey(mainTask);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link MainTaskDTO }
     *
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(MainTaskDTO record) {
        MainTask mainTask = mainTaskMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = mainTaskMapper.updateByPrimaryKeySelective(mainTask);
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
        int num = mainTaskMapper.deleteByPrimaryKey(id);
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
//        return mainTaskMapper.deleteLogicByPrimaryKey(id);
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
        return mainTaskMapper.deleteByIds(String.join(",", ids));
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
//        return mainTaskMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<MainTaskDTO> }
     */
    @Override
    public GridReturnData<MainTaskDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<MainTaskDTO> mGridReturnData = new GridReturnData<>();
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

        List<MainTask> list = mainTaskMapper.selectPage(map);

        PageInfo<MainTask> pageInfo = new PageInfo<>(list);
        PageInfo<MainTaskDTO> pageInfoFinal = new PageInfo<>(mainTaskMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    @Override
    public List<MainTask> getAllUnDispatchedTask() {
        List<MainTask> mainTasks = mainTaskMapper.selectByTaskStatus(TaskConstants.mainTaskStatus.MAIN_NOT_ISSUED);
        return mainTasks;
    }

    @Override
    public boolean subtaskPreConditionMetCheck(SubTask firstSubTask) {
        return false;
    }

    @Transactional
    @Override
    public boolean endMainTask(String mainTaskNum) {
        MainTask mainTask = mainTaskMapper.selectByMainTaskNum(mainTaskNum);
        logger.info("开始结束主任务{}", mainTask);
        if (mainTask == null) {
            logger.error("无效的主任务编号：{}", mainTaskNum);
            return false;
        } else {
            String taskStatus = mainTask.getTaskStatus();
            logger.debug("尝试结束主任务{}，当前主任务执行状态为{}", mainTask.getMainTaskNum(), taskStatus);
            if ((TaskConstants.mainTaskStatus.MAIN_FINISHED.equals(taskStatus))) {
                logger.warn("主任务状态异常{}，尝试结束前发现其状态为已结束", mainTask.getMainTaskNum(), taskStatus);
                return true;
            } else if (TaskConstants.mainTaskStatus.MAIN_ISSUED.equals(taskStatus) || TaskConstants.mainTaskStatus.MAIN_NOT_ISSUED.equals(taskStatus)) {
                MainTask mainTaskTmp = new MainTask();
                mainTaskTmp.setId(mainTask.getId());
                mainTaskTmp.setTaskStatus(TaskConstants.mainTaskStatus.MAIN_FINISHED);
                mainTaskMapper.updateByPrimaryKeySelective(mainTaskTmp);
                logger.info("修改主任务状态为9：{}", mainTaskNum);
                return true;
            } else {
                logger.error("结束主任务时，任务状态异常，处于不可结束状态{}", mainTask.getTaskStatus());
            }

        }
        logger.error("结束主任务失败，无效的主任务号{}", mainTaskNum);
        return false;
    }


    public Result setPriority(MainTaskDTO mainTask) {
        String mainTaskNum = mainTask.getMainTaskNum();
        Integer priority = mainTask.getPriority();
        if (StringUtils.isEmpty(mainTaskNum) || priority == null || priority < 0 ) {
            return new Result(400, "缺少参数(主任务单号或优先级)");
        }
        int changeRow = mainTaskMapper.updatePriority(mainTaskNum, priority);
        if (changeRow <= 0) {
            return new Result(400, "优先级未修改");
        }
        return new Result();
    }
}
