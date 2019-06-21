package com.wisdom.iwcs.service.task;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.SubTaskLog;
import com.wisdom.iwcs.domain.task.dto.SubTaskLogDTO;
import com.wisdom.iwcs.mapper.task.SubTaskLogMapper;
import com.wisdom.iwcs.mapstruct.task.SubTaskLogMapStruct;
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
public class SubTaskLogService {
    private final Logger logger = LoggerFactory.getLogger(SubTaskLogService.class);

    private final SubTaskLogMapper SubTaskLogMapper;

    private final SubTaskLogMapStruct SubTaskLogMapStruct;

    @Autowired
    public SubTaskLogService(SubTaskLogMapStruct SubTaskLogMapStruct, SubTaskLogMapper SubTaskLogMapper) {
        this.SubTaskLogMapStruct = SubTaskLogMapStruct;
        this.SubTaskLogMapper = SubTaskLogMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link SubTaskLogDTO }
     *
     * @return int
     */
    public int insert(SubTaskLogDTO record) {
        SubTaskLog SubTaskLog = SubTaskLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = SubTaskLogMapper.insert(SubTaskLog);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<SubTaskLogDTO> }
     *
     * @return int
     */
    public int insertBatch(List<SubTaskLogDTO> records) {
        List<SubTaskLog> recordList = SubTaskLogMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = SubTaskLogMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link SubTaskLogDTO }
     */
    public SubTaskLogDTO selectByPrimaryKey(Integer id) {

        SubTaskLog SubTaskLog = SubTaskLogMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(SubTaskLog, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return SubTaskLogMapStruct.toDto(SubTaskLog);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link SubTaskLogDTO }
     *
     * @return {@link List<SubTaskLogDTO> }
     */
    public List<SubTaskLogDTO> selectSelective(SubTaskLogDTO record) {
        SubTaskLog SubTaskLog = SubTaskLogMapStruct.toEntity(record);

        List<SubTaskLog> SubTaskLogList = SubTaskLogMapper.select(SubTaskLog);
        return SubTaskLogMapStruct.toDto(SubTaskLogList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link SubTaskLogDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(SubTaskLogDTO record) {
        SubTaskLog SubTaskLog = SubTaskLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = SubTaskLogMapper.updateByPrimaryKey(SubTaskLog);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link SubTaskLogDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(SubTaskLogDTO record) {
        SubTaskLog SubTaskLog = SubTaskLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = SubTaskLogMapper.updateByPrimaryKeySelective(SubTaskLog);
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
        int num = SubTaskLogMapper.deleteByPrimaryKey(id);
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
//        return SubTaskLogMapper.deleteLogicByPrimaryKey(id);
//    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     *
     * @return int
     */
    public int deleteMore(List<String> ids){
        return SubTaskLogMapper.deleteByIds(String.join(",", ids));
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
//        return SubTaskLogMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<SubTaskLogDTO> }
     */
    public GridReturnData<SubTaskLogDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<SubTaskLogDTO> mGridReturnData = new GridReturnData<>();
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

        List<SubTaskLog> list = SubTaskLogMapper.selectPage(map);

        PageInfo<SubTaskLog> pageInfo = new PageInfo<>(list);
        PageInfo<SubTaskLogDTO> pageInfoFinal = new PageInfo<>(SubTaskLogMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
