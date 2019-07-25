package com.wisdom.iwcs.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.EleMsgLog;
import com.wisdom.iwcs.domain.task.dto.EleMsgLogDTO;
import com.wisdom.iwcs.mapper.task.EleMsgLogMapper;
import com.wisdom.iwcs.mapstruct.task.EleMsgLogMapStruct;
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
public class EleMsgLogService {
    private final Logger logger = LoggerFactory.getLogger(EleMsgLogService.class);

    private final EleMsgLogMapper eleMsgLogMapper;

    private final EleMsgLogMapStruct eleMsgLogMapStruct;

    @Autowired
    public EleMsgLogService(EleMsgLogMapStruct eleMsgLogMapStruct, EleMsgLogMapper eleMsgLogMapper) {
        this.eleMsgLogMapStruct = eleMsgLogMapStruct;
        this.eleMsgLogMapper = eleMsgLogMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link EleMsgLogDTO }
     *
     * @return int
     */
    public int insert(EleMsgLogDTO record) {
        EleMsgLog eleMsgLog = eleMsgLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        eleMsgLog.setCreatedTime(new Date());

        int num = eleMsgLogMapper.insert(eleMsgLog);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<EleMsgLogDTO> }
     *
     * @return int
     */
    public int insertBatch(List<EleMsgLogDTO> records) {
        List<EleMsgLog> recordList = eleMsgLogMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
        });

        int num = eleMsgLogMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link EleMsgLogDTO }
     */
    public EleMsgLogDTO selectByPrimaryKey(Integer id) {

        EleMsgLog eleMsgLog = eleMsgLogMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(eleMsgLog, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return eleMsgLogMapStruct.toDto(eleMsgLog);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link EleMsgLogDTO }
     *
     * @return {@link List<EleMsgLogDTO> }
     */
    public List<EleMsgLogDTO> selectSelective(EleMsgLogDTO record) {
        EleMsgLog eleMsgLog = eleMsgLogMapStruct.toEntity(record);

        List<EleMsgLog> eleMsgLogList = eleMsgLogMapper.select(eleMsgLog);
        return eleMsgLogMapStruct.toDto(eleMsgLogList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link EleMsgLogDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(EleMsgLogDTO record) {
        EleMsgLog eleMsgLog = eleMsgLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = eleMsgLogMapper.updateByPrimaryKey(eleMsgLog);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link EleMsgLogDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(EleMsgLogDTO record) {
        EleMsgLog eleMsgLog = eleMsgLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = eleMsgLogMapper.updateByPrimaryKeySelective(eleMsgLog);
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
        int num = eleMsgLogMapper.deleteByPrimaryKey(id);
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
//        return eleMsgLogMapper.deleteLogicByPrimaryKey(id);
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
        return eleMsgLogMapper.deleteByIds(String.join(",", ids));
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
//        return eleMsgLogMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<EleMsgLogDTO> }
     */
    public GridReturnData<EleMsgLogDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<EleMsgLogDTO> mGridReturnData = new GridReturnData<>();
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

        List<EleMsgLog> list = eleMsgLogMapper.selectPage(map);

        PageInfo<EleMsgLog> pageInfo = new PageInfo<>(list);
        PageInfo<EleMsgLogDTO> pageInfoFinal = new PageInfo<>(eleMsgLogMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
