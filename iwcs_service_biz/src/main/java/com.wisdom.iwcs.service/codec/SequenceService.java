package com.wisdom.iwcs.service.codec;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.codec.Sequence;
import com.wisdom.iwcs.domain.codec.SequenceNameEnum;
import com.wisdom.iwcs.domain.codec.dto.SequenceDto;
import com.wisdom.iwcs.mapper.codec.SequenceMapper;
import com.wisdom.iwcs.mapstruct.codec.SequenceMapStruct;
import com.wisdom.iwcs.service.security.SecurityUtils;
import com.wisdom.iwcs.service.system.extface.SUserInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class SequenceService {
    private final Logger logger = LoggerFactory.getLogger(SequenceService.class);

    @Autowired
    SequenceMapper sequenceMapper;

    @Autowired
    SUserInterface sUserServiceImpl;

    @Autowired
    SequenceMapStruct sequenceMapStruct;

    /**
     * 写入记录
     *
     * @param SequenceDto record
     * @return int
     */
    public int insert(SequenceDto record) {
        Sequence sequence = sequenceMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        sequence.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        Long currentTimeMillis = System.currentTimeMillis();
        sequence.setCreatedTime(new Date());
        sequence.setCreatedBy(userId);
        sequence.setLastModifiedBy(userId);
        sequence.setLastModifiedTime(new Date());

        int num = sequenceMapper.insert(sequence);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param List<SequenceDto> records
     * @return int
     */
    public int insertBatch(List<SequenceDto> records) {
        List<Sequence> recordList = sequenceMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        Long currentTimeMillis = System.currentTimeMillis();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = sequenceMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param Integer id
     * @return SequenceDto
     */
    public SequenceDto selectByPrimaryKey(Integer id) {

        Sequence sequence = sequenceMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(sequence, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return sequenceMapStruct.toDto(sequence);
    }

    /**
     * 根据字段选择性查询
     *
     * @param SequenceDto record
     * @return List<SequenceDto>
     */
    public List<SequenceDto> selectSelective(SequenceDto record) {
        Sequence sequence = sequenceMapStruct.toEntity(record);

        List<Sequence> sequenceList = sequenceMapper.select(sequence);
        return sequenceMapStruct.toDto(sequenceList);
    }

    /**
     * 根据主键更新
     *
     * @param SequenceDto record
     * @return int
     */
    public int updateByPrimaryKey(SequenceDto record) {
        Sequence sequence = sequenceMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        Long currentTimeMillis = System.currentTimeMillis();
        sequence.setLastModifiedBy(userId);
        sequence.setLastModifiedTime(new Date());

        int num = sequenceMapper.updateByPrimaryKey(sequence);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param SequenceDto record
     * @return int
     */
    public int updateByPrimaryKeySelective(SequenceDto record) {
        Sequence sequence = sequenceMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        Long currentTimeMillis = System.currentTimeMillis();
        sequence.setLastModifiedBy(userId);
        sequence.setLastModifiedTime(new Date());

        int num = sequenceMapper.updateByPrimaryKeySelective(sequence);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     * @param Integer id
     * @return int
     */
    public int deleteByPrimaryKey(Integer id) {
        int num = sequenceMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键逻辑删除记录
     *
     * @param Integer id
     * @return int
     */
    public int deleteLogicByPrimaryKey(Integer id) {
        return sequenceMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param List<String> ids
     * @return int
     */
    public int deleteMore(List<String> ids) {
        return sequenceMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param List<String> ids
     * @return int
     */
    public int deleteMoreLogic(List<String> ids) {
        return sequenceMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param GridPageRequest 　gridPageRequest
     * @return　GridReturnData<SequenceDto>
     */
    public GridReturnData<SequenceDto> selectPage(GridPageRequest gridPageRequest) {
        Integer user_id = SecurityUtils.getCurrentUserId();

        GridReturnData<SequenceDto> mGridReturnData = new GridReturnData<>();
        List<GridFilterInfo> filterList = gridPageRequest.getFilterList();
        Map map = new HashMap();
        filterList.stream().forEach(gridFilterInfo -> {//封装筛选条件
            if (gridFilterInfo.getFilterKey() != null && gridFilterInfo.getFilterValue() != null) {
                map.put(gridFilterInfo.getFilterKey(), gridFilterInfo.getFilterValue());
            }
        });
        map.put("searchKey", gridPageRequest.getSearchKey());
        //对map中的参数的合法性进行校验

        String sortMyBatisByString = gridPageRequest.getSortMybatisString();
        PageHelper.startPage(gridPageRequest.getPageNum(), gridPageRequest.getPageSize(), sortMyBatisByString);

        List<Sequence> list = sequenceMapper.selectPage(map);

        PageInfo<SequenceDto> pageInfo = new PageInfo<>(sequenceMapStruct.toDto(list));
        mGridReturnData.setPageInfo(pageInfo);

        return mGridReturnData;
    }

    //----------------------------------------------------
    public int getSequence(String seqName) {
        return sequenceMapper.getSequence(seqName);
    }

    public String getOrderNo() {
        String departCode = sUserServiceImpl.getCurrentUserDepartmentCode().toUpperCase();
        int mySequence = sequenceMapper.getSequence(SequenceNameEnum.OrderNo.getSequenceName());
        return departCode + mySequence;
    }

    public String getInternalBlNo() {
        int mySequence = sequenceMapper.getSequence(SequenceNameEnum.InternalBlNo.getSequenceName());
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyMM");
        String tmpYearMonth = sDateFormat.format(new Date());
        String tmpSequence = String.format("%03d", mySequence);
        return "TAOKW" + tmpYearMonth + tmpSequence;
    }

    public String getInvoiceNo() {
        int mySequence = sequenceMapper.getSequence(SequenceNameEnum.InvoiceNo.getSequenceName());
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yy");
        String tmpYear = sDateFormat.format(new Date());
        String tmpSequence = String.format("%04d", mySequence);
        return tmpYear + "TAO" + tmpSequence;
    }

    public String getAirBlNo() {
        int mySequence = sequenceMapper.getSequence(SequenceNameEnum.AirBlNo.getSequenceName());
        return "ARBL" + mySequence;
    }

    public String getFOrderNo() {
        int mySequence = sequenceMapper.getSequence(SequenceNameEnum.FOrderNo.getSequenceName());
        return "FOBN" + mySequence;
    }

    public String getFSettlementNo() {
        int mySequence = sequenceMapper.getSequence(SequenceNameEnum.FSettlementNo.getSequenceName());
        return /*"FSBN"*/"" + mySequence;
    }

    public String getFPaymentNo() {
        int mySequence = sequenceMapper.getSequence(SequenceNameEnum.FPaymentNo.getSequenceName());
        return /*"FPBN"*/"" + mySequence;
    }

    public int resetInterBlNo() {
        return sequenceMapper.resetInterBlNo();
    }

    public int resetInvoiceNo() {
        return sequenceMapper.resetInvoiceNo();
    }

}
