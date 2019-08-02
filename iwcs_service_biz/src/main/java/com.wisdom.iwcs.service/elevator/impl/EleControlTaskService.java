package com.wisdom.iwcs.service.elevator.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.EleControlTask;
import com.wisdom.iwcs.domain.elevator.dto.EleControlTaskDTO;
import com.wisdom.iwcs.mapper.elevator.EleControlTaskMapper;
import com.wisdom.iwcs.mapstruct.task.EleControlTaskMapStruct;
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
public class EleControlTaskService {
    private final Logger logger = LoggerFactory.getLogger(EleControlTaskService.class);

    private final EleControlTaskMapper eleControlTaskMapper;

    private final EleControlTaskMapStruct eleControlTaskMapStruct;

    @Autowired
    public EleControlTaskService(EleControlTaskMapStruct eleControlTaskMapStruct, EleControlTaskMapper eleControlTaskMapper) {
        this.eleControlTaskMapStruct = eleControlTaskMapStruct;
        this.eleControlTaskMapper = eleControlTaskMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link EleControlTaskDTO }
     *
     * @return int
     */
    public int insert(EleControlTaskDTO record) {
        EleControlTask eleControlTask = eleControlTaskMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        eleControlTask.setCreatedTime(new Date());

        int num = eleControlTaskMapper.insert(eleControlTask);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<EleControlTaskDTO> }
     *
     * @return int
     */
    public int insertBatch(List<EleControlTaskDTO> records) {
        List<EleControlTask> recordList = eleControlTaskMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
        });

        int num = eleControlTaskMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link EleControlTaskDTO }
     */
    public EleControlTaskDTO selectByPrimaryKey(Integer id) {

        EleControlTask eleControlTask = eleControlTaskMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(eleControlTask, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return eleControlTaskMapStruct.toDto(eleControlTask);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link EleControlTaskDTO }
     *
     * @return {@link List<EleControlTaskDTO> }
     */
    public List<EleControlTaskDTO> selectSelective(EleControlTaskDTO record) {
        EleControlTask eleControlTask = eleControlTaskMapStruct.toEntity(record);

        List<EleControlTask> eleControlTaskList = eleControlTaskMapper.select(eleControlTask);
        return eleControlTaskMapStruct.toDto(eleControlTaskList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link EleControlTaskDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(EleControlTaskDTO record) {
        EleControlTask eleControlTask = eleControlTaskMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = eleControlTaskMapper.updateByPrimaryKey(eleControlTask);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link EleControlTaskDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(EleControlTaskDTO record) {
        EleControlTask eleControlTask = eleControlTaskMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = eleControlTaskMapper.updateByPrimaryKeySelective(eleControlTask);
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
        int num = eleControlTaskMapper.deleteByPrimaryKey(id);
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
//        return eleControlTaskMapper.deleteLogicByPrimaryKey(id);
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
        return eleControlTaskMapper.deleteByIds(String.join(",", ids));
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
//        return eleControlTaskMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<EleControlTaskDTO> }
     */
    public GridReturnData<EleControlTaskDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<EleControlTaskDTO> mGridReturnData = new GridReturnData<>();
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

        List<EleControlTask> list = eleControlTaskMapper.selectPage(map);

        PageInfo<EleControlTask> pageInfo = new PageInfo<>(list);
        PageInfo<EleControlTaskDTO> pageInfoFinal = new PageInfo<>(eleControlTaskMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

}
