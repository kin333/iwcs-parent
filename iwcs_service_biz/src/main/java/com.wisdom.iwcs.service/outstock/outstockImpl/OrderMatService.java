package com.wisdom.iwcs.service.outstock.outstockImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.outstock.OrderMat;
import com.wisdom.iwcs.domain.outstock.dto.OrderMatDTO;
import com.wisdom.iwcs.domain.outstock.dto.OutStockOrderDto;
import com.wisdom.iwcs.mapper.outstock.OrderMatMapper;
import com.wisdom.iwcs.mapstruct.outstock.OrderMatMapStruct;
import com.wisdom.iwcs.service.outstock.IOrderMatService;
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
public class OrderMatService implements IOrderMatService {
    private final Logger logger = LoggerFactory.getLogger(OrderMatService.class);

    private final OrderMatMapper orderMatMapper;

    private final OrderMatMapStruct orderMatMapStruct;

    @Autowired
    public OrderMatService(OrderMatMapStruct orderMatMapStruct, OrderMatMapper orderMatMapper) {
        this.orderMatMapStruct = orderMatMapStruct;
        this.orderMatMapper = orderMatMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link OrderMatDTO }
     * @return int
     */
    @Override
    public int insert(OrderMatDTO record) {
        OrderMat orderMat = orderMatMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        orderMat.setCreatedTime(new Date());

        int num = orderMatMapper.insert(orderMat);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<OrderMatDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<OrderMatDTO> records) {
        List<OrderMat> recordList = orderMatMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
        });

        int num = orderMatMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link OrderMatDTO }
     */
    @Override
    public OrderMatDTO selectByPrimaryKey(Integer id) {

        OrderMat orderMat = orderMatMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(orderMat, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return orderMatMapStruct.toDto(orderMat);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link OrderMatDTO }
     * @return {@link List<OrderMatDTO> }
     */
    @Override
    public List<OrderMatDTO> selectSelective(OrderMatDTO record) {
        OrderMat orderMat = orderMatMapStruct.toEntity(record);

        List<OrderMat> orderMatList = orderMatMapper.select(orderMat);
        return orderMatMapStruct.toDto(orderMatList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link OrderMatDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(OrderMatDTO record) {
        OrderMat orderMat = orderMatMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = orderMatMapper.updateByPrimaryKey(orderMat);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link OrderMatDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(OrderMatDTO record) {
        OrderMat orderMat = orderMatMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        int num = orderMatMapper.updateByPrimaryKeySelective(orderMat);
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
        int num = orderMatMapper.deleteByPrimaryKey(id);
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
        return orderMatMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<OrderMatDTO> }
     */
    @Override
    public GridReturnData<OrderMatDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<OrderMatDTO> mGridReturnData = new GridReturnData<>();
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

        List<OrderMat> list = orderMatMapper.selectPage(map);

        PageInfo<OrderMat> pageInfo = new PageInfo<>(list);
        PageInfo<OrderMatDTO> pageInfoFinal = new PageInfo<>(orderMatMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 获取出库单
     *
     * @param outStockOrderDto
     * @return
     */
    @Override
    public List<OutStockOrderDto> selectOutStockOrder(OutStockOrderDto outStockOrderDto) {
        return orderMatMapper.selectOutStockOrder(outStockOrderDto);
    }
}
