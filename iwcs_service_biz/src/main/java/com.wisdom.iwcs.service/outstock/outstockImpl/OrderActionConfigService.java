package com.wisdom.iwcs.service.outstock.outstockImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.outstock.OrderActionConfig;
import com.wisdom.iwcs.domain.outstock.dto.OrderActionConfigDTO;
import com.wisdom.iwcs.mapper.outstock.OrderActionConfigMapper;
import com.wisdom.iwcs.mapstruct.outstock.OrderActionConfigMapStruct;
import com.wisdom.iwcs.service.outstock.IOrderActionConfigService;
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
public class OrderActionConfigService implements IOrderActionConfigService {
    private final Logger logger = LoggerFactory.getLogger(OrderActionConfigService.class);

    private final OrderActionConfigMapper orderActionConfigMapper;

    private final OrderActionConfigMapStruct orderActionConfigMapStruct;

    @Autowired
    public OrderActionConfigService(OrderActionConfigMapStruct orderActionConfigMapStruct, OrderActionConfigMapper orderActionConfigMapper) {
        this.orderActionConfigMapStruct = orderActionConfigMapStruct;
        this.orderActionConfigMapper = orderActionConfigMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link OrderActionConfigDTO }
     * @return int
     */
    @Override
    public int insert(OrderActionConfigDTO record) {
        OrderActionConfig orderActionConfig = orderActionConfigMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        orderActionConfig.setCreatedTime(new Date());
        orderActionConfig.setLastModifiedTime(new Date());

        int num = orderActionConfigMapper.insert(orderActionConfig);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<OrderActionConfigDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<OrderActionConfigDTO> records) {
        List<OrderActionConfig> recordList = orderActionConfigMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
            record.setLastModifiedTime(new Date());
        });

        int num = orderActionConfigMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link OrderActionConfigDTO }
     */
    @Override
    public OrderActionConfigDTO selectByPrimaryKey(Integer id) {

        OrderActionConfig orderActionConfig = orderActionConfigMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(orderActionConfig, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return orderActionConfigMapStruct.toDto(orderActionConfig);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link OrderActionConfigDTO }
     * @return {@link List<OrderActionConfigDTO> }
     */
    @Override
    public List<OrderActionConfigDTO> selectSelective(OrderActionConfigDTO record) {
        OrderActionConfig orderActionConfig = orderActionConfigMapStruct.toEntity(record);

        List<OrderActionConfig> orderActionConfigList = orderActionConfigMapper.select(orderActionConfig);
        return orderActionConfigMapStruct.toDto(orderActionConfigList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link OrderActionConfigDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(OrderActionConfigDTO record) {
        OrderActionConfig orderActionConfig = orderActionConfigMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        orderActionConfig.setLastModifiedTime(new Date());

        int num = orderActionConfigMapper.updateByPrimaryKey(orderActionConfig);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link OrderActionConfigDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(OrderActionConfigDTO record) {
        OrderActionConfig orderActionConfig = orderActionConfigMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        orderActionConfig.setLastModifiedTime(new Date());

        int num = orderActionConfigMapper.updateByPrimaryKeySelective(orderActionConfig);
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
        int num = orderActionConfigMapper.deleteByPrimaryKey(id);
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
        return orderActionConfigMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<OrderActionConfigDTO> }
     */
    @Override
    public GridReturnData<OrderActionConfigDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<OrderActionConfigDTO> mGridReturnData = new GridReturnData<>();
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

        List<OrderActionConfig> list = orderActionConfigMapper.selectPage(map);

        PageInfo<OrderActionConfig> pageInfo = new PageInfo<>(list);
        PageInfo<OrderActionConfigDTO> pageInfoFinal = new PageInfo<>(orderActionConfigMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
