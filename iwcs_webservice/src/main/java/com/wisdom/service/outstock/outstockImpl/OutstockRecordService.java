package com.wisdom.service.outstock.outstockImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.outstock.OutstockRecordMapStruct;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.outstock.OutstockRecord;
import com.wisdom.iwcs.domain.outstock.dto.OutstockRecordConditionDTO;
import com.wisdom.iwcs.domain.outstock.dto.OutstockRecordDTO;
import com.wisdom.iwcs.mapper.outstock.OutstockRecordMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.outstock.IOutstockRecordService;
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
public class OutstockRecordService implements IOutstockRecordService {
    private final Logger logger = LoggerFactory.getLogger(OutstockRecordService.class);

    private final OutstockRecordMapper outstockRecordMapper;

    private final OutstockRecordMapStruct outstockRecordMapStruct;

    @Autowired
    public OutstockRecordService(OutstockRecordMapStruct outstockRecordMapStruct, OutstockRecordMapper outstockRecordMapper) {
        this.outstockRecordMapStruct = outstockRecordMapStruct;
        this.outstockRecordMapper = outstockRecordMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link OutstockRecordDTO }
     * @return int
     */
    @Override
    public int insert(OutstockRecordDTO record) {
        OutstockRecord outstockRecord = outstockRecordMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        outstockRecord.setCreatedTime(new Date());
        outstockRecord.setLastModifiedTime(new Date());

        int num = outstockRecordMapper.insert(outstockRecord);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<OutstockRecordDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<OutstockRecordDTO> records) {
        List<OutstockRecord> recordList = outstockRecordMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
            record.setLastModifiedTime(new Date());
        });

        int num = outstockRecordMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link OutstockRecordDTO }
     */
    @Override
    public OutstockRecordDTO selectByPrimaryKey(Integer id) {

        OutstockRecord outstockRecord = outstockRecordMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(outstockRecord, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return outstockRecordMapStruct.toDto(outstockRecord);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link OutstockRecordDTO }
     * @return {@link List<OutstockRecordDTO> }
     */
    @Override
    public List<OutstockRecordDTO> selectSelective(OutstockRecordDTO record) {
        OutstockRecord outstockRecord = outstockRecordMapStruct.toEntity(record);

        List<OutstockRecord> outstockRecordList = outstockRecordMapper.select(outstockRecord);
        return outstockRecordMapStruct.toDto(outstockRecordList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link OutstockRecordDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(OutstockRecordDTO record) {
        OutstockRecord outstockRecord = outstockRecordMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        outstockRecord.setLastModifiedTime(new Date());

        int num = outstockRecordMapper.updateByPrimaryKey(outstockRecord);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link OutstockRecordDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(OutstockRecordDTO record) {
        OutstockRecord outstockRecord = outstockRecordMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        outstockRecord.setLastModifiedTime(new Date());

        int num = outstockRecordMapper.updateByPrimaryKeySelective(outstockRecord);
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
        int num = outstockRecordMapper.deleteByPrimaryKey(id);
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
        return outstockRecordMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<OutstockRecordDTO> }
     */
    @Override
    public GridReturnData<OutstockRecordDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<OutstockRecordDTO> mGridReturnData = new GridReturnData<>();
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

        List<OutstockRecord> list = outstockRecordMapper.selectPage(map);

        PageInfo<OutstockRecord> pageInfo = new PageInfo<>(list);
        PageInfo<OutstockRecordDTO> pageInfoFinal = new PageInfo<>(outstockRecordMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 获取出库记录
     *
     * @param outstockRecordConditionDTO
     * @return
     */
    @Override
    public List<OutstockRecordDTO> selectOutStockRecord(OutstockRecordConditionDTO outstockRecordConditionDTO) {
        List<OutstockRecord> outstockRecordList = outstockRecordMapper.selectOutStockRecord(outstockRecordConditionDTO);
        return outstockRecordMapStruct.toDto(outstockRecordList);
    }
}
