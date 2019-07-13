package com.wisdom.iwcs.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.LineTask;
import com.wisdom.iwcs.domain.task.dto.LineTaskDTO;
import com.wisdom.iwcs.mapper.task.LineTaskMapper;
import com.wisdom.iwcs.mapstruct.task.LineTaskMapStruct;
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
public class LineTaskService {
    private final Logger logger = LoggerFactory.getLogger(LineTaskService.class);

    private final LineTaskMapper lineTaskMapper;

    private final LineTaskMapStruct lineTaskMapStruct;

    @Autowired
    public LineTaskService(LineTaskMapStruct lineTaskMapStruct, LineTaskMapper lineTaskMapper) {
        this.lineTaskMapStruct = lineTaskMapStruct;
        this.lineTaskMapper = lineTaskMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link LineTaskDTO }
     *
     * @return int
     */
    public int insert(LineTaskDTO record) {
        LineTask lineTask = lineTaskMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        lineTask.setCreatedTime(new Date());

        int num = lineTaskMapper.insert(lineTask);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<LineTaskDTO> }
     *
     * @return int
     */
    public int insertBatch(List<LineTaskDTO> records) {
        List<LineTask> recordList = lineTaskMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
        });

        int num = lineTaskMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link LineTaskDTO }
     */
    public LineTaskDTO selectByPrimaryKey(Integer id) {

        LineTask lineTask = lineTaskMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(lineTask, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return lineTaskMapStruct.toDto(lineTask);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link LineTaskDTO }
     *
     * @return {@link List<LineTaskDTO> }
     */
    public List<LineTaskDTO> selectSelective(LineTaskDTO record) {
        LineTask lineTask = lineTaskMapStruct.toEntity(record);

        List<LineTask> lineTaskList = lineTaskMapper.select(lineTask);
        return lineTaskMapStruct.toDto(lineTaskList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link LineTaskDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(LineTaskDTO record) {
        LineTask lineTask = lineTaskMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = lineTaskMapper.updateByPrimaryKey(lineTask);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link LineTaskDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(LineTaskDTO record) {
        LineTask lineTask = lineTaskMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = lineTaskMapper.updateByPrimaryKeySelective(lineTask);
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
        int num = lineTaskMapper.deleteByPrimaryKey(id);
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
//        return lineTaskMapper.deleteLogicByPrimaryKey(id);
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
        return lineTaskMapper.deleteByIds(String.join(",", ids));
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
//        return lineTaskMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<LineTaskDTO> }
     */
    public GridReturnData<LineTaskDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<LineTaskDTO> mGridReturnData = new GridReturnData<>();
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

        List<LineTask> list = lineTaskMapper.selectPage(map);

        PageInfo<LineTask> pageInfo = new PageInfo<>(list);
        PageInfo<LineTaskDTO> pageInfoFinal = new PageInfo<>(lineTaskMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
