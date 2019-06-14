package com.wisdom.service.outstock.outstockImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.outstock.OutstockOrderSnMapStruct;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.outstock.OutstockOrderSn;
import com.wisdom.iwcs.domain.outstock.dto.OutstockOrderSnDTO;
import com.wisdom.iwcs.mapper.outstock.OutstockOrderSnMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.outstock.IOutstockOrderSnService;
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
public class OutstockOrderSnService implements IOutstockOrderSnService {
    private final Logger logger = LoggerFactory.getLogger(OutstockOrderSnService.class);

    private final OutstockOrderSnMapper outstockOrderSnMapper;

    private final OutstockOrderSnMapStruct outstockOrderSnMapStruct;

    @Autowired
    public OutstockOrderSnService(OutstockOrderSnMapStruct outstockOrderSnMapStruct, OutstockOrderSnMapper outstockOrderSnMapper) {
        this.outstockOrderSnMapStruct = outstockOrderSnMapStruct;
        this.outstockOrderSnMapper = outstockOrderSnMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link OutstockOrderSnDTO }
     * @return int
     */
    @Override
    public int insert(OutstockOrderSnDTO record) {
        OutstockOrderSn outstockOrderSn = outstockOrderSnMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        outstockOrderSn.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        outstockOrderSn.setCreatedTime(new Date());
        outstockOrderSn.setLastModifiedTime(new Date());

        int num = outstockOrderSnMapper.insert(outstockOrderSn);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<OutstockOrderSnDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<OutstockOrderSnDTO> records) {
        List<OutstockOrderSn> recordList = outstockOrderSnMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setLastModifiedTime(new Date());
        });

        int num = outstockOrderSnMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link OutstockOrderSnDTO }
     */
    @Override
    public OutstockOrderSnDTO selectByPrimaryKey(Integer id) {

        OutstockOrderSn outstockOrderSn = outstockOrderSnMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(outstockOrderSn, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return outstockOrderSnMapStruct.toDto(outstockOrderSn);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link OutstockOrderSnDTO }
     * @return {@link List<OutstockOrderSnDTO> }
     */
    @Override
    public List<OutstockOrderSnDTO> selectSelective(OutstockOrderSnDTO record) {
        OutstockOrderSn outstockOrderSn = outstockOrderSnMapStruct.toEntity(record);

        List<OutstockOrderSn> outstockOrderSnList = outstockOrderSnMapper.select(outstockOrderSn);
        return outstockOrderSnMapStruct.toDto(outstockOrderSnList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link OutstockOrderSnDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(OutstockOrderSnDTO record) {
        OutstockOrderSn outstockOrderSn = outstockOrderSnMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        outstockOrderSn.setLastModifiedTime(new Date());

        int num = outstockOrderSnMapper.updateByPrimaryKey(outstockOrderSn);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link OutstockOrderSnDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(OutstockOrderSnDTO record) {
        OutstockOrderSn outstockOrderSn = outstockOrderSnMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        outstockOrderSn.setLastModifiedTime(new Date());

        int num = outstockOrderSnMapper.updateByPrimaryKeySelective(outstockOrderSn);
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
        int num = outstockOrderSnMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键逻辑删除记录
     *
     * @param id {@link Integer }
     * @return int
     */
    @Override
    public int deleteLogicByPrimaryKey(Integer id) {
        return outstockOrderSnMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return outstockOrderSnMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMoreLogic(List<String> ids) {
        return outstockOrderSnMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<OutstockOrderSnDTO> }
     */
    @Override
    public GridReturnData<OutstockOrderSnDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<OutstockOrderSnDTO> mGridReturnData = new GridReturnData<>();
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

        List<OutstockOrderSn> list = outstockOrderSnMapper.selectPage(map);

        PageInfo<OutstockOrderSn> pageInfo = new PageInfo<>(list);
        PageInfo<OutstockOrderSnDTO> pageInfoFinal = new PageInfo<>(outstockOrderSnMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 根据出库材料号获取出库单明细
     *
     * @param orderMatGenCode
     * @return
     */
    @Override
    public List<OutstockOrderSnDTO> selectOutStockOrderDetailByGenCode(String orderMatGenCode) {
        if (orderMatGenCode == null || orderMatGenCode.isEmpty()) {
            throw new BusinessException("缺少出库材料号");
        }
        return outstockOrderSnMapStruct.toDto(outstockOrderSnMapper.selectOutStockOrderDetailByGenCode(orderMatGenCode));
    }
}
