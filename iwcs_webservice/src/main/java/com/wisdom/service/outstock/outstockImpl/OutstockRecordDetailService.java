package com.wisdom.service.outstock.outstockImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.outstock.OutstockRecordDetailMapStruct;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.outstock.OutstockRecordDetail;
import com.wisdom.iwcs.domain.outstock.dto.OutstockRecordDetailDTO;
import com.wisdom.iwcs.mapper.outstock.OutstockRecordDetailMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.outstock.IOutstockRecordDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class OutstockRecordDetailService implements IOutstockRecordDetailService {
    private final Logger logger = LoggerFactory.getLogger(OutstockRecordDetailService.class);

    private final OutstockRecordDetailMapper outstockRecordDetailMapper;

    private final OutstockRecordDetailMapStruct outstockRecordDetailMapStruct;

    @Autowired
    public OutstockRecordDetailService(OutstockRecordDetailMapStruct outstockRecordDetailMapStruct, OutstockRecordDetailMapper outstockRecordDetailMapper) {
        this.outstockRecordDetailMapStruct = outstockRecordDetailMapStruct;
        this.outstockRecordDetailMapper = outstockRecordDetailMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link OutstockRecordDetailDTO }
     * @return int
     */
    @Override
    public int insert(OutstockRecordDetailDTO record) {
        OutstockRecordDetail outstockRecordDetail = outstockRecordDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = outstockRecordDetailMapper.insert(outstockRecordDetail);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<OutstockRecordDetailDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<OutstockRecordDetailDTO> records) {
        List<OutstockRecordDetail> recordList = outstockRecordDetailMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        int num = outstockRecordDetailMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link OutstockRecordDetailDTO }
     */
    @Override
    public OutstockRecordDetailDTO selectByPrimaryKey(Integer id) {

        OutstockRecordDetail outstockRecordDetail = outstockRecordDetailMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(outstockRecordDetail, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return outstockRecordDetailMapStruct.toDto(outstockRecordDetail);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link OutstockRecordDetailDTO }
     * @return {@link List<OutstockRecordDetailDTO> }
     */
    @Override
    public List<OutstockRecordDetailDTO> selectSelective(OutstockRecordDetailDTO record) {
        OutstockRecordDetail outstockRecordDetail = outstockRecordDetailMapStruct.toEntity(record);

        List<OutstockRecordDetail> outstockRecordDetailList = outstockRecordDetailMapper.select(outstockRecordDetail);
        return outstockRecordDetailMapStruct.toDto(outstockRecordDetailList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link OutstockRecordDetailDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(OutstockRecordDetailDTO record) {
        OutstockRecordDetail outstockRecordDetail = outstockRecordDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        int num = outstockRecordDetailMapper.updateByPrimaryKey(outstockRecordDetail);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link OutstockRecordDetailDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(OutstockRecordDetailDTO record) {
        OutstockRecordDetail outstockRecordDetail = outstockRecordDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = outstockRecordDetailMapper.updateByPrimaryKeySelective(outstockRecordDetail);
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
        int num = outstockRecordDetailMapper.deleteByPrimaryKey(id);
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
        return outstockRecordDetailMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<OutstockRecordDetailDTO> }
     */
    @Override
    public GridReturnData<OutstockRecordDetailDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<OutstockRecordDetailDTO> mGridReturnData = new GridReturnData<>();
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

        List<OutstockRecordDetail> list = outstockRecordDetailMapper.selectPage(map);

        PageInfo<OutstockRecordDetail> pageInfo = new PageInfo<>(list);
        PageInfo<OutstockRecordDetailDTO> pageInfoFinal = new PageInfo<>(outstockRecordDetailMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 根据出库记录id获取出库详情
     *
     * @param outstockRecordId
     * @return
     */
    @Override
    public List<OutstockRecordDetailDTO> selectOutstockRecordDetail(Integer outstockRecordId) {
        Preconditions.checkBusinessError(outstockRecordId == null || outstockRecordId <= 0, "缺少出库记录ID");
        List<OutstockRecordDetail> outstockRecordDetailList = outstockRecordDetailMapper.selectOutstockRecordDetailById(outstockRecordId);
        return outstockRecordDetailMapStruct.toDto(outstockRecordDetailList);
    }
}
