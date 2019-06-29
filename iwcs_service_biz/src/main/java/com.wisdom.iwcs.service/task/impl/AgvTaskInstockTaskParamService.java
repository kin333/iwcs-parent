package com.wisdom.iwcs.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.AgvTaskInstockTaskParam;
import com.wisdom.iwcs.domain.task.dto.AgvTaskInstockTaskParamDTO;
import com.wisdom.iwcs.mapper.task.AgvTaskInstockTaskParamMapper;
import com.wisdom.iwcs.mapstruct.task.AgvTaskInstockTaskParamMapStruct;
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
public class AgvTaskInstockTaskParamService {
    private final Logger logger = LoggerFactory.getLogger(AgvTaskInstockTaskParamService.class);

    private final AgvTaskInstockTaskParamMapper agvTaskInstockTaskParamMapper;

    private final AgvTaskInstockTaskParamMapStruct agvTaskInstockTaskParamMapStruct;

    @Autowired
    public AgvTaskInstockTaskParamService(AgvTaskInstockTaskParamMapStruct agvTaskInstockTaskParamMapStruct, AgvTaskInstockTaskParamMapper agvTaskInstockTaskParamMapper) {
        this.agvTaskInstockTaskParamMapStruct = agvTaskInstockTaskParamMapStruct;
        this.agvTaskInstockTaskParamMapper = agvTaskInstockTaskParamMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link AgvTaskInstockTaskParamDTO }
     * @return int
     */
    public int insert(AgvTaskInstockTaskParamDTO record) {
        AgvTaskInstockTaskParam agvTaskInstockTaskParam = agvTaskInstockTaskParamMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        agvTaskInstockTaskParam.setCreatedTime(new Date());

        int num = agvTaskInstockTaskParamMapper.insert(agvTaskInstockTaskParam);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<AgvTaskInstockTaskParamDTO> }
     * @return int
     */
    public int insertBatch(List<AgvTaskInstockTaskParamDTO> records) {
        List<AgvTaskInstockTaskParam> recordList = agvTaskInstockTaskParamMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
        });

        int num = agvTaskInstockTaskParamMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link AgvTaskInstockTaskParamDTO }
     */
    public AgvTaskInstockTaskParamDTO selectByPrimaryKey(Integer id) {

        AgvTaskInstockTaskParam agvTaskInstockTaskParam = agvTaskInstockTaskParamMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(agvTaskInstockTaskParam, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return agvTaskInstockTaskParamMapStruct.toDto(agvTaskInstockTaskParam);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link AgvTaskInstockTaskParamDTO }
     * @return {@link List<AgvTaskInstockTaskParamDTO> }
     */
    public List<AgvTaskInstockTaskParamDTO> selectSelective(AgvTaskInstockTaskParamDTO record) {
        AgvTaskInstockTaskParam agvTaskInstockTaskParam = agvTaskInstockTaskParamMapStruct.toEntity(record);

        List<AgvTaskInstockTaskParam> agvTaskInstockTaskParamList = agvTaskInstockTaskParamMapper.select(agvTaskInstockTaskParam);
        return agvTaskInstockTaskParamMapStruct.toDto(agvTaskInstockTaskParamList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link AgvTaskInstockTaskParamDTO }
     * @return int
     */
    public int updateByPrimaryKey(AgvTaskInstockTaskParamDTO record) {
        AgvTaskInstockTaskParam agvTaskInstockTaskParam = agvTaskInstockTaskParamMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = agvTaskInstockTaskParamMapper.updateByPrimaryKey(agvTaskInstockTaskParam);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link AgvTaskInstockTaskParamDTO }
     * @return int
     */
    public int updateByPrimaryKeySelective(AgvTaskInstockTaskParamDTO record) {
        AgvTaskInstockTaskParam agvTaskInstockTaskParam = agvTaskInstockTaskParamMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = agvTaskInstockTaskParamMapper.updateByPrimaryKeySelective(agvTaskInstockTaskParam);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     * @param id {@link Integer }
     * @return int
     */
    public int deleteByPrimaryKey(Integer id) {
        int num = agvTaskInstockTaskParamMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }


    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    public int deleteMore(List<String> ids) {
        return agvTaskInstockTaskParamMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<AgvTaskInstockTaskParamDTO> }
     */
    public GridReturnData<AgvTaskInstockTaskParamDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<AgvTaskInstockTaskParamDTO> mGridReturnData = new GridReturnData<>();
        List<GridFilterInfo> filterList = gridPageRequest.getFilterList();
        Map<String, Object> map = new HashMap<>(2);
        filterList.forEach(gridFilterInfo -> {
            if (gridFilterInfo.getFilterKey() != null && gridFilterInfo.getFilterValue() != null) {
                map.put(gridFilterInfo.getFilterKey(), gridFilterInfo.getFilterValue());
            }
        });
        map.put("searchKey", gridPageRequest.getSearchKey());
        // 对map中的参数的合法性进行校验

        String sortMyBatisByString = gridPageRequest.getSortMybatisString();
        PageHelper.startPage(gridPageRequest.getPageNum(), gridPageRequest.getPageSize(), sortMyBatisByString);

        List<AgvTaskInstockTaskParam> list = agvTaskInstockTaskParamMapper.selectPage(map);

        PageInfo<AgvTaskInstockTaskParam> pageInfo = new PageInfo<>(list);
        PageInfo<AgvTaskInstockTaskParamDTO> pageInfoFinal = new PageInfo<>(agvTaskInstockTaskParamMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
