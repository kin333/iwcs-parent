package com.wisdom.iwcs.service.linebody.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.linebody.LineMsgLog;
import com.wisdom.iwcs.domain.linebody.dto.LineMsgLogDTO;
import com.wisdom.iwcs.mapper.linebody.LineMsgLogMapper;
import com.wisdom.iwcs.mapstruct.task.LineMsgLogMapStruct;
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
public class LineMsgLogService {
    private final Logger logger = LoggerFactory.getLogger(LineMsgLogService.class);

    private final LineMsgLogMapper lineMsgLogMapper;

    private final LineMsgLogMapStruct lineMsgLogMapStruct;

    @Autowired
    public LineMsgLogService(LineMsgLogMapStruct lineMsgLogMapStruct, LineMsgLogMapper lineMsgLogMapper) {
        this.lineMsgLogMapStruct = lineMsgLogMapStruct;
        this.lineMsgLogMapper = lineMsgLogMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link LineMsgLogDTO }
     *
     * @return int
     */
    public int insert(LineMsgLogDTO record) {
        LineMsgLog lineMsgLog = lineMsgLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        lineMsgLog.setCreatedTime(new Date());

        int num = lineMsgLogMapper.insert(lineMsgLog);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<LineMsgLogDTO> }
     *
     * @return int
     */
    public int insertBatch(List<LineMsgLogDTO> records) {
        List<LineMsgLog> recordList = lineMsgLogMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
        });

        int num = lineMsgLogMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link LineMsgLogDTO }
     */
    public LineMsgLogDTO selectByPrimaryKey(Integer id) {

        LineMsgLog lineMsgLog = lineMsgLogMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(lineMsgLog, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return lineMsgLogMapStruct.toDto(lineMsgLog);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link LineMsgLogDTO }
     *
     * @return {@link List<LineMsgLogDTO> }
     */
    public List<LineMsgLogDTO> selectSelective(LineMsgLogDTO record) {
        LineMsgLog lineMsgLog = lineMsgLogMapStruct.toEntity(record);

        List<LineMsgLog> lineMsgLogList = lineMsgLogMapper.select(lineMsgLog);
        return lineMsgLogMapStruct.toDto(lineMsgLogList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link LineMsgLogDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(LineMsgLogDTO record) {
        LineMsgLog lineMsgLog = lineMsgLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = lineMsgLogMapper.updateByPrimaryKey(lineMsgLog);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link LineMsgLogDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(LineMsgLogDTO record) {
        LineMsgLog lineMsgLog = lineMsgLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = lineMsgLogMapper.updateByPrimaryKeySelective(lineMsgLog);
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
        int num = lineMsgLogMapper.deleteByPrimaryKey(id);
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
//        return lineMsgLogMapper.deleteLogicByPrimaryKey(id);
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
        return lineMsgLogMapper.deleteByIds(String.join(",", ids));
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
//        return lineMsgLogMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<LineMsgLogDTO> }
     */
    public GridReturnData<LineMsgLogDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<LineMsgLogDTO> mGridReturnData = new GridReturnData<>();
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

        List<LineMsgLog> list = lineMsgLogMapper.selectPage(map);

        PageInfo<LineMsgLog> pageInfo = new PageInfo<>(list);
        PageInfo<LineMsgLogDTO> pageInfoFinal = new PageInfo<>(lineMsgLogMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
