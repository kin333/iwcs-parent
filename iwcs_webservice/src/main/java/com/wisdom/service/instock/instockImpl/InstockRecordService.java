package com.wisdom.service.instock.instockImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.instock.InstockRecordMapStruct;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.instock.InstockRecord;
import com.wisdom.iwcs.domain.instock.dto.InstockRecordConditionDto;
import com.wisdom.iwcs.domain.instock.dto.InstockRecordDTO;
import com.wisdom.iwcs.mapper.instock.InstockRecordMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.instock.IInstockRecordService;
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
public class InstockRecordService implements IInstockRecordService {
    private final Logger logger = LoggerFactory.getLogger(InstockRecordService.class);

    private final InstockRecordMapper instockRecordMapper;

    private final InstockRecordMapStruct instockRecordMapStruct;

    @Autowired
    public InstockRecordService(InstockRecordMapStruct instockRecordMapStruct, InstockRecordMapper instockRecordMapper) {
        this.instockRecordMapStruct = instockRecordMapStruct;
        this.instockRecordMapper = instockRecordMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link InstockRecordDTO }
     * @return int
     */
    @Override
    public int insert(InstockRecordDTO record) {
        InstockRecord instockRecord = instockRecordMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        instockRecord.setCreatedTime(new Date());
        instockRecord.setCreatedBy(userId);

        int num = instockRecordMapper.insert(instockRecord);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<InstockRecordDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<InstockRecordDTO> records) {
        List<InstockRecord> recordList = instockRecordMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
        });

        int num = instockRecordMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link InstockRecordDTO }
     */
    @Override
    public InstockRecordDTO selectByPrimaryKey(Integer id) {

        InstockRecord instockRecord = instockRecordMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(instockRecord, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return instockRecordMapStruct.toDto(instockRecord);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link InstockRecordDTO }
     * @return {@link List<InstockRecordDTO> }
     */
    @Override
    public List<InstockRecordDTO> selectSelective(InstockRecordDTO record) {
        InstockRecord instockRecord = instockRecordMapStruct.toEntity(record);

        List<InstockRecord> instockRecordList = instockRecordMapper.select(instockRecord);
        return instockRecordMapStruct.toDto(instockRecordList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link InstockRecordDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(InstockRecordDTO record) {
        InstockRecord instockRecord = instockRecordMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = instockRecordMapper.updateByPrimaryKey(instockRecord);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link InstockRecordDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(InstockRecordDTO record) {
        InstockRecord instockRecord = instockRecordMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = instockRecordMapper.updateByPrimaryKeySelective(instockRecord);
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
        int num = instockRecordMapper.deleteByPrimaryKey(id);
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
        return instockRecordMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<InstockRecordDTO> }
     */
    @Override
    public GridReturnData<InstockRecordDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<InstockRecordDTO> mGridReturnData = new GridReturnData<>();
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

        List<InstockRecord> list = instockRecordMapper.selectPage(map);

        PageInfo<InstockRecord> pageInfo = new PageInfo<>(list);
        PageInfo<InstockRecordDTO> pageInfoFinal = new PageInfo<>(instockRecordMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 获取入库记录
     *
     * @param instockRecordConditionDto
     * @return
     */
    @Override
    public List<InstockRecordDTO> selectInstockRecord(InstockRecordConditionDto instockRecordConditionDto) {

        List<InstockRecord> instockRecordList = instockRecordMapper.selectInstockRecord(instockRecordConditionDto);
        return instockRecordMapStruct.toDto(instockRecordList);
    }
}
