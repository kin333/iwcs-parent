package com.wisdom.iwcs.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.AgvCallback;
import com.wisdom.iwcs.domain.task.dto.AgvCallbackDTO;
import com.wisdom.iwcs.mapper.task.AgvCallbackMapper;
import com.wisdom.iwcs.mapstruct.task.AgvCallbackMapStruct;
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
public class AgvCallbackService {
    private final Logger logger = LoggerFactory.getLogger(AgvCallbackService.class);

    private final AgvCallbackMapper agvCallbackMapper;

    private final AgvCallbackMapStruct agvCallbackMapStruct;

    @Autowired
    public AgvCallbackService(AgvCallbackMapStruct agvCallbackMapStruct, AgvCallbackMapper agvCallbackMapper) {
        this.agvCallbackMapStruct = agvCallbackMapStruct;
        this.agvCallbackMapper = agvCallbackMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link AgvCallbackDTO }
     *
     * @return int
     */
    public int insert(AgvCallbackDTO record) {
        AgvCallback agvCallback = agvCallbackMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = agvCallbackMapper.insert(agvCallback);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<AgvCallbackDTO> }
     *
     * @return int
     */
    public int insertBatch(List<AgvCallbackDTO> records) {
        List<AgvCallback> recordList = agvCallbackMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = agvCallbackMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link AgvCallbackDTO }
     */
    public AgvCallbackDTO selectByPrimaryKey(Integer id) {

        AgvCallback agvCallback = agvCallbackMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(agvCallback, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return agvCallbackMapStruct.toDto(agvCallback);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link AgvCallbackDTO }
     *
     * @return {@link List<AgvCallbackDTO> }
     */
    public List<AgvCallbackDTO> selectSelective(AgvCallbackDTO record) {
        AgvCallback agvCallback = agvCallbackMapStruct.toEntity(record);

        List<AgvCallback> agvCallbackList = agvCallbackMapper.select(agvCallback);
        return agvCallbackMapStruct.toDto(agvCallbackList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link AgvCallbackDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(AgvCallbackDTO record) {
        AgvCallback agvCallback = agvCallbackMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = agvCallbackMapper.updateByPrimaryKey(agvCallback);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link AgvCallbackDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(AgvCallbackDTO record) {
        AgvCallback agvCallback = agvCallbackMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = agvCallbackMapper.updateByPrimaryKeySelective(agvCallback);
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
        int num = agvCallbackMapper.deleteByPrimaryKey(id);
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
//        return agvCallbackMapper.deleteLogicByPrimaryKey(id);
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
        return agvCallbackMapper.deleteByIds(String.join(",", ids));
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
//        return agvCallbackMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<AgvCallbackDTO> }
     */
    public GridReturnData<AgvCallbackDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<AgvCallbackDTO> mGridReturnData = new GridReturnData<>();
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

        List<AgvCallback> list = agvCallbackMapper.selectPage(map);

        PageInfo<AgvCallback> pageInfo = new PageInfo<>(list);
        PageInfo<AgvCallbackDTO> pageInfoFinal = new PageInfo<>(agvCallbackMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
