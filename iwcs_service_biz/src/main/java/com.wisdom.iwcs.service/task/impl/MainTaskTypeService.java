package com.wisdom.iwcs.service.task.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.dto.MainTaskTypeAndAreaCode;
import com.wisdom.iwcs.domain.task.MainTaskType;
import com.wisdom.iwcs.domain.task.TaskModal;
import com.wisdom.iwcs.domain.task.TaskRel;
import com.wisdom.iwcs.domain.task.dto.MainTaskTypeDTO;
import com.wisdom.iwcs.mapper.task.MainTaskTypeMapper;
import com.wisdom.iwcs.mapper.task.TaskRelMapper;
import com.wisdom.iwcs.mapstruct.task.MainTaskTypeMapStruct;
import com.wisdom.iwcs.service.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class MainTaskTypeService {
    private final Logger logger = LoggerFactory.getLogger(MainTaskTypeService.class);

    private final MainTaskTypeMapper mainTaskTypeMapper;

    private final MainTaskTypeMapStruct mainTaskTypeMapStruct;

    private final com.wisdom.iwcs.mapper.task.TaskRelMapper TaskRelMapper;

    private final com.wisdom.iwcs.mapper.task.TaskRelConditionMapper TaskRelConditionMapper;

    @Autowired
    public MainTaskTypeService(MainTaskTypeMapStruct mainTaskTypeMapStruct, MainTaskTypeMapper mainTaskTypeMapper,
                               com.wisdom.iwcs.mapper.task.TaskRelMapper taskRelMapper, com.wisdom.iwcs.mapper.task.TaskRelConditionMapper taskRelConditionMapper) {
        this.mainTaskTypeMapStruct = mainTaskTypeMapStruct;
        this.mainTaskTypeMapper = mainTaskTypeMapper;
        TaskRelMapper = taskRelMapper;
        TaskRelConditionMapper = taskRelConditionMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link MainTaskTypeDTO }
     *
     * @return int
     */
    public int insert(MainTaskTypeDTO record) {

        MainTaskType mainTaskType = mainTaskTypeMapStruct.toEntity(record);

        MainTaskType mainTaskTypeList = mainTaskTypeMapper.selectMainTypeByMainCode(mainTaskType);

        if (mainTaskTypeList != null) {
            return 400;
        }

        Integer userId = SecurityUtils.getCurrentUserId();
        mainTaskType.setCreateDate(new Date());
        int num = mainTaskTypeMapper.insert(mainTaskType);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<MainTaskTypeDTO> }
     *
     * @return int
     */
    public int insertBatch(List<MainTaskTypeDTO> records) {
        List<MainTaskType> recordList = mainTaskTypeMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = mainTaskTypeMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link MainTaskTypeDTO }
     */
    public MainTaskTypeDTO selectByPrimaryKey(Integer id) {

        MainTaskType mainTaskType = mainTaskTypeMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(mainTaskType, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return mainTaskTypeMapStruct.toDto(mainTaskType);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link MainTaskTypeDTO }
     *
     * @return {@link List<MainTaskTypeDTO> }
     */
    public List<MainTaskTypeDTO> selectSelective(MainTaskTypeDTO record) {
        MainTaskType mainTaskType = mainTaskTypeMapStruct.toEntity(record);

        List<MainTaskType> mainTaskTypeList = mainTaskTypeMapper.select(mainTaskType);
        return mainTaskTypeMapStruct.toDto(mainTaskTypeList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link MainTaskTypeDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(MainTaskTypeDTO record) {
        MainTaskType mainTaskType = mainTaskTypeMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = mainTaskTypeMapper.updateByPrimaryKey(mainTaskType);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link MainTaskTypeDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(MainTaskTypeDTO record) {
        MainTaskType mainTaskType = mainTaskTypeMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = mainTaskTypeMapper.updateByPrimaryKeySelective(mainTaskType);
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
        int num = mainTaskTypeMapper.deleteByPrimaryKey(id);
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
//        return mainTaskTypeMapper.deleteLogicByPrimaryKey(id);
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
        return mainTaskTypeMapper.deleteByIds(String.join(",", ids));
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
//        return mainTaskTypeMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<MainTaskTypeDTO> }
     */
    public GridReturnData<MainTaskTypeDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<MainTaskTypeDTO> mGridReturnData = new GridReturnData<>();
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

        List<MainTaskType> list = mainTaskTypeMapper.selectPage(map);

        PageInfo<MainTaskType> pageInfo = new PageInfo<>(list);
        PageInfo<MainTaskTypeDTO> pageInfoFinal = new PageInfo<>(mainTaskTypeMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 查询所有任务类型
     * @param
     * @return
     */
    public List<MainTaskTypeAndAreaCode> selectAllTaskType(String areaCode){
        List<MainTaskTypeAndAreaCode> mainTaskType = mainTaskTypeMapper.selectTaskTypeWithAreaCode(areaCode);
        return mainTaskType;
    }

    /**
     * 根据主任务code删除
     */
    public int deleteMainTaskType(TaskModal taskModal) {

        List<TaskRel> taskRelList = TaskRelMapper.selectByMainTaskType(taskModal.getSearchMainCode());
        List<String> templCodeList = new ArrayList<>();
        int rellNum = TaskRelMapper.deleteByMainCode(taskModal.getSearchMainCode());

        taskRelList.forEach(item -> {
            templCodeList.add(item.getTemplCode());
        });

        int conNum = TaskRelConditionMapper.deleteByTemplCodes(templCodeList);
        // 主任务表删除
        int num = mainTaskTypeMapper.deleteMainTaskType(taskModal);

        return num;
    }


    public MainTaskType selectMainTypeByMainCode(MainTaskType mainCode){

        MainTaskType mainTaskTypeDTO = mainTaskTypeMapper.selectMainTypeByMainCode(mainCode);
        return mainTaskTypeDTO;
    }

    public List<MainTaskType> selectAll() {

        List<MainTaskType> mainTaskTypeList = mainTaskTypeMapper.selectAll();

        return mainTaskTypeList;
    }

}
