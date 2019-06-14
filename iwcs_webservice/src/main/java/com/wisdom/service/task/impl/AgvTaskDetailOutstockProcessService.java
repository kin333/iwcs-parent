package com.wisdom.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.task.AgvTaskDetailOutstockProcessMapStruct;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.AgvTaskDetailOutstockProcess;
import com.wisdom.iwcs.domain.task.dto.AgvTaskDetailOutstockProcessDTO;
import com.wisdom.iwcs.mapper.task.AgvTaskDetailOutstockProcessMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.task.IAgvTaskDetailOutstockProcessService;
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
public class AgvTaskDetailOutstockProcessService implements IAgvTaskDetailOutstockProcessService {
    private final Logger logger = LoggerFactory.getLogger(AgvTaskDetailOutstockProcessService.class);

    private final AgvTaskDetailOutstockProcessMapper agvTaskDetailOutstockProcessMapper;

    private final AgvTaskDetailOutstockProcessMapStruct agvTaskDetailOutstockProcessMapStruct;

    @Autowired
    public AgvTaskDetailOutstockProcessService(AgvTaskDetailOutstockProcessMapStruct agvTaskDetailOutstockProcessMapStruct, AgvTaskDetailOutstockProcessMapper agvTaskDetailOutstockProcessMapper) {
        this.agvTaskDetailOutstockProcessMapStruct = agvTaskDetailOutstockProcessMapStruct;
        this.agvTaskDetailOutstockProcessMapper = agvTaskDetailOutstockProcessMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link AgvTaskDetailOutstockProcessDTO }
     * @return int
     */
    @Override
    public int insert(AgvTaskDetailOutstockProcessDTO record) {
        AgvTaskDetailOutstockProcess agvTaskDetailOutstockProcess = agvTaskDetailOutstockProcessMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        agvTaskDetailOutstockProcess.setCreatedTime(new Date());
        agvTaskDetailOutstockProcess.setLastModifiedTime(new Date());

        int num = agvTaskDetailOutstockProcessMapper.insert(agvTaskDetailOutstockProcess);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<AgvTaskDetailOutstockProcessDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<AgvTaskDetailOutstockProcessDTO> records) {
        List<AgvTaskDetailOutstockProcess> recordList = agvTaskDetailOutstockProcessMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
            record.setLastModifiedTime(new Date());
        });

        int num = agvTaskDetailOutstockProcessMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link AgvTaskDetailOutstockProcessDTO }
     */
    @Override
    public AgvTaskDetailOutstockProcessDTO selectByPrimaryKey(Integer id) {

        AgvTaskDetailOutstockProcess agvTaskDetailOutstockProcess = agvTaskDetailOutstockProcessMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(agvTaskDetailOutstockProcess, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return agvTaskDetailOutstockProcessMapStruct.toDto(agvTaskDetailOutstockProcess);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link AgvTaskDetailOutstockProcessDTO }
     * @return {@link List<AgvTaskDetailOutstockProcessDTO> }
     */
    @Override
    public List<AgvTaskDetailOutstockProcessDTO> selectSelective(AgvTaskDetailOutstockProcessDTO record) {
        AgvTaskDetailOutstockProcess agvTaskDetailOutstockProcess = agvTaskDetailOutstockProcessMapStruct.toEntity(record);

        List<AgvTaskDetailOutstockProcess> agvTaskDetailOutstockProcessList = agvTaskDetailOutstockProcessMapper.select(agvTaskDetailOutstockProcess);
        return agvTaskDetailOutstockProcessMapStruct.toDto(agvTaskDetailOutstockProcessList);
    }

    /**
     * 根据podCode和taskNo查看出库货架库存下架计划及进度（多选）
     *
     * @param agvTaskDetailOutstockProcessDTOList
     * @return
     */
    @Override
    public List<AgvTaskDetailOutstockProcessDTO> selectAgvDetailProcess(List<AgvTaskDetailOutstockProcessDTO> agvTaskDetailOutstockProcessDTOList) {

        Preconditions.checkBusinessError(agvTaskDetailOutstockProcessDTOList == null || agvTaskDetailOutstockProcessDTOList.size() == 0, "请选择任务列表进行查询");

        for (AgvTaskDetailOutstockProcessDTO agvTaskDetailOutstockProcessDTO : agvTaskDetailOutstockProcessDTOList) {
            Preconditions.checkBusinessError(agvTaskDetailOutstockProcessDTO.getPodCode() == null || agvTaskDetailOutstockProcessDTO.getPodCode().isEmpty(), "缺少货架编号");
            Preconditions.checkBusinessError(agvTaskDetailOutstockProcessDTO.getTaskNo() == null || agvTaskDetailOutstockProcessDTO.getTaskNo().isEmpty(), agvTaskDetailOutstockProcessDTO.getPodCode() + "缺少工作台任务编号");
        }

        List<AgvTaskDetailOutstockProcess> agvTaskDetailOutstockProcessList = agvTaskDetailOutstockProcessMapper.selectAgvDetailProcess(agvTaskDetailOutstockProcessDTOList);
        return agvTaskDetailOutstockProcessMapStruct.toDto(agvTaskDetailOutstockProcessList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link AgvTaskDetailOutstockProcessDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(AgvTaskDetailOutstockProcessDTO record) {
        AgvTaskDetailOutstockProcess agvTaskDetailOutstockProcess = agvTaskDetailOutstockProcessMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        agvTaskDetailOutstockProcess.setLastModifiedTime(new Date());

        int num = agvTaskDetailOutstockProcessMapper.updateByPrimaryKey(agvTaskDetailOutstockProcess);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link AgvTaskDetailOutstockProcessDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(AgvTaskDetailOutstockProcessDTO record) {
        AgvTaskDetailOutstockProcess agvTaskDetailOutstockProcess = agvTaskDetailOutstockProcessMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        agvTaskDetailOutstockProcess.setLastModifiedTime(new Date());

        int num = agvTaskDetailOutstockProcessMapper.updateByPrimaryKeySelective(agvTaskDetailOutstockProcess);
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
        int num = agvTaskDetailOutstockProcessMapper.deleteByPrimaryKey(id);
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
        return agvTaskDetailOutstockProcessMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<AgvTaskDetailOutstockProcessDTO> }
     */
    @Override
    public GridReturnData<AgvTaskDetailOutstockProcessDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<AgvTaskDetailOutstockProcessDTO> mGridReturnData = new GridReturnData<>();
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

        List<AgvTaskDetailOutstockProcess> list = agvTaskDetailOutstockProcessMapper.selectPage(map);

        PageInfo<AgvTaskDetailOutstockProcess> pageInfo = new PageInfo<>(list);
        PageInfo<AgvTaskDetailOutstockProcessDTO> pageInfoFinal = new PageInfo<>(agvTaskDetailOutstockProcessMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
