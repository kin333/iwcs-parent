package com.wisdom.service.log.logImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.log.AgvTaskLogMapStruct;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.log.AgvTaskLog;
import com.wisdom.iwcs.domain.log.dto.AgvTaskLogDTO;
import com.wisdom.iwcs.mapper.log.AgvTaskLogMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.log.IAgvTaskLogService;
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
public class AgvTaskLogService implements IAgvTaskLogService {
    private final Logger logger = LoggerFactory.getLogger(AgvTaskLogService.class);

    private final AgvTaskLogMapper agvTaskLogMapper;

    private final AgvTaskLogMapStruct agvTaskLogMapStruct;

    @Autowired
    public AgvTaskLogService(AgvTaskLogMapStruct agvTaskLogMapStruct, AgvTaskLogMapper agvTaskLogMapper) {
        this.agvTaskLogMapStruct = agvTaskLogMapStruct;
        this.agvTaskLogMapper = agvTaskLogMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link AgvTaskLogDTO }
     * @return int
     */
    @Override
    public int insert(AgvTaskLogDTO record) {
        AgvTaskLog agvTaskLog = agvTaskLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        agvTaskLog.setCreatedTime(new Date());

        int num = agvTaskLogMapper.insert(agvTaskLog);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<AgvTaskLogDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<AgvTaskLogDTO> records) {
        List<AgvTaskLog> recordList = agvTaskLogMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
        });

        int num = agvTaskLogMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link AgvTaskLogDTO }
     */
    @Override
    public AgvTaskLogDTO selectByPrimaryKey(Integer id) {

        AgvTaskLog agvTaskLog = agvTaskLogMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(agvTaskLog, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return agvTaskLogMapStruct.toDto(agvTaskLog);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link AgvTaskLogDTO }
     * @return {@link List<AgvTaskLogDTO> }
     */
    @Override
    public List<AgvTaskLogDTO> selectSelective(AgvTaskLogDTO record) {
        AgvTaskLog agvTaskLog = agvTaskLogMapStruct.toEntity(record);

        List<AgvTaskLog> agvTaskLogList = agvTaskLogMapper.select(agvTaskLog);
        return agvTaskLogMapStruct.toDto(agvTaskLogList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link AgvTaskLogDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(AgvTaskLogDTO record) {
        AgvTaskLog agvTaskLog = agvTaskLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        int num = agvTaskLogMapper.updateByPrimaryKey(agvTaskLog);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link AgvTaskLogDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(AgvTaskLogDTO record) {
        AgvTaskLog agvTaskLog = agvTaskLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = agvTaskLogMapper.updateByPrimaryKeySelective(agvTaskLog);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     * @param id {@link Integer }
     * @return int
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        int num = agvTaskLogMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }


    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return agvTaskLogMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<AgvTaskLogDTO> }
     */
    @Override
    public GridReturnData<AgvTaskLogDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<AgvTaskLogDTO> mGridReturnData = new GridReturnData<>();
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

        List<AgvTaskLog> list = agvTaskLogMapper.selectPage(map);

        PageInfo<AgvTaskLog> pageInfo = new PageInfo<>(list);
        PageInfo<AgvTaskLogDTO> pageInfoFinal = new PageInfo<>(agvTaskLogMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
