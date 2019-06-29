package com.wisdom.iwcs.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.AgvTaskOutstockStock;
import com.wisdom.iwcs.domain.task.dto.AgvTaskOutstockStockDTO;
import com.wisdom.iwcs.mapper.task.AgvTaskOutstockStockMapper;
import com.wisdom.iwcs.mapstruct.task.AgvTaskOutstockStockMapStruct;
import com.wisdom.iwcs.service.security.SecurityUtils;
import com.wisdom.iwcs.service.task.intf.IAgvTaskOutstockStockService;
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
public class AgvTaskOutstockStockService implements IAgvTaskOutstockStockService {
    private final Logger logger = LoggerFactory.getLogger(AgvTaskOutstockStockService.class);

    private final AgvTaskOutstockStockMapper agvTaskOutstockStockMapper;

    private final AgvTaskOutstockStockMapStruct agvTaskOutstockStockMapStruct;

    @Autowired
    public AgvTaskOutstockStockService(AgvTaskOutstockStockMapStruct agvTaskOutstockStockMapStruct, AgvTaskOutstockStockMapper agvTaskOutstockStockMapper) {
        this.agvTaskOutstockStockMapStruct = agvTaskOutstockStockMapStruct;
        this.agvTaskOutstockStockMapper = agvTaskOutstockStockMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link AgvTaskOutstockStockDTO }
     * @return int
     */
    @Override
    public int insert(AgvTaskOutstockStockDTO record) {
        AgvTaskOutstockStock agvTaskOutstockStock = agvTaskOutstockStockMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        agvTaskOutstockStock.setCreatedTime(new Date());
        agvTaskOutstockStock.setLastModifiedTime(new Date());

        int num = agvTaskOutstockStockMapper.insert(agvTaskOutstockStock);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<AgvTaskOutstockStockDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<AgvTaskOutstockStockDTO> records) {
        List<AgvTaskOutstockStock> recordList = agvTaskOutstockStockMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
            record.setLastModifiedTime(new Date());
        });

        int num = agvTaskOutstockStockMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link AgvTaskOutstockStockDTO }
     */
    @Override
    public AgvTaskOutstockStockDTO selectByPrimaryKey(Integer id) {

        AgvTaskOutstockStock agvTaskOutstockStock = agvTaskOutstockStockMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(agvTaskOutstockStock, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return agvTaskOutstockStockMapStruct.toDto(agvTaskOutstockStock);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link AgvTaskOutstockStockDTO }
     * @return {@link List<AgvTaskOutstockStockDTO> }
     */
    @Override
    public List<AgvTaskOutstockStockDTO> selectSelective(AgvTaskOutstockStockDTO record) {
        AgvTaskOutstockStock agvTaskOutstockStock = agvTaskOutstockStockMapStruct.toEntity(record);

        List<AgvTaskOutstockStock> agvTaskOutstockStockList = agvTaskOutstockStockMapper.select(agvTaskOutstockStock);
        return agvTaskOutstockStockMapStruct.toDto(agvTaskOutstockStockList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link AgvTaskOutstockStockDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(AgvTaskOutstockStockDTO record) {
        AgvTaskOutstockStock agvTaskOutstockStock = agvTaskOutstockStockMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        agvTaskOutstockStock.setLastModifiedTime(new Date());

        int num = agvTaskOutstockStockMapper.updateByPrimaryKey(agvTaskOutstockStock);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link AgvTaskOutstockStockDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(AgvTaskOutstockStockDTO record) {
        AgvTaskOutstockStock agvTaskOutstockStock = agvTaskOutstockStockMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        agvTaskOutstockStock.setLastModifiedTime(new Date());

        int num = agvTaskOutstockStockMapper.updateByPrimaryKeySelective(agvTaskOutstockStock);
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
        int num = agvTaskOutstockStockMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<AgvTaskOutstockStockDTO> }
     */
    @Override
    public GridReturnData<AgvTaskOutstockStockDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<AgvTaskOutstockStockDTO> mGridReturnData = new GridReturnData<>();
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

        List<AgvTaskOutstockStock> list = agvTaskOutstockStockMapper.selectPage(map);

        PageInfo<AgvTaskOutstockStock> pageInfo = new PageInfo<>(list);
        PageInfo<AgvTaskOutstockStockDTO> pageInfoFinal = new PageInfo<>(agvTaskOutstockStockMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 根据taskNo获取出库任务库存呼叫明细
     *
     * @param taskNo
     * @return
     */
    @Override
    public List<AgvTaskOutstockStockDTO> selectAgvTaskOutStockByTaskNo(String taskNo) {

        if (taskNo == null || taskNo.isEmpty()) {
            throw new BusinessException("缺少工作台任务编号");
        }

        List<AgvTaskOutstockStock> agvTaskOutstockStockList = agvTaskOutstockStockMapper.selectAgvTaskOutStockByTaskNo(taskNo);
        return agvTaskOutstockStockMapStruct.toDto(agvTaskOutstockStockList);
    }
}
