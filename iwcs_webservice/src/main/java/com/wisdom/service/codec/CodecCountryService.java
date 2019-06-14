package com.wisdom.service.codec;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.codec.CodecCountryMapStruct;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridCountryPageRequest;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.codec.CodecCountry;
import com.wisdom.iwcs.domain.codec.dto.CodecCountryDto;
import com.wisdom.iwcs.mapper.codec.CodecCountryMapper;
import com.wisdom.security.SecurityUtils;
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
public class CodecCountryService {
    private final Logger logger = LoggerFactory.getLogger(CodecCountryService.class);

    @Autowired
    CodecCountryMapper codecCountryMapper;

    @Autowired
    CodecCountryMapStruct codecCountryMapStruct;

    /**
     * 写入记录
     *
     * @param CodecCountryDto record
     * @return int
     */
    public int insert(CodecCountryDto record) {
        CodecCountry codecCountry = codecCountryMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        codecCountry.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        Date currentDate = new Date();
        codecCountry.setCreatedTime(currentDate);
        codecCountry.setCreatedBy(userId);
        codecCountry.setLastModifiedBy(userId);
        codecCountry.setLastModifiedTime(currentDate);

        int num = codecCountryMapper.insert(codecCountry);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param List<CodecCountryDto> records
     * @return int
     */
    public int insertBatch(List<CodecCountryDto> records) {
        List<CodecCountry> recordList = codecCountryMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        Date currentDate = new Date();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(currentDate);
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(currentDate);
        });

        int num = codecCountryMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param Integer id
     * @return CodecCountryDto
     */
    public CodecCountryDto selectByPrimaryKey(Integer id) {

        CodecCountry codecCountry = codecCountryMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(codecCountry, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return codecCountryMapStruct.toDto(codecCountry);
    }

    /**
     * 根据字段选择性查询
     *
     * @param CodecCountryDto record
     * @return List<CodecCountryDto>
     */
    public List<CodecCountryDto> selectSelective(CodecCountryDto record) {
        CodecCountry codecCountry = codecCountryMapStruct.toEntity(record);

        List<CodecCountry> codecCountryList = codecCountryMapper.select(codecCountry);
        return codecCountryMapStruct.toDto(codecCountryList);
    }

    /**
     * 根据主键更新
     *
     * @param CodecCountryDto record
     * @return int
     */
    public int updateByPrimaryKey(CodecCountryDto record) {
        CodecCountry codecCountry = codecCountryMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        Date currentDate = new Date();
        codecCountry.setLastModifiedBy(userId);
        codecCountry.setLastModifiedTime(currentDate);

        int num = codecCountryMapper.updateByPrimaryKey(codecCountry);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param CodecCountryDto record
     * @return int
     */
    public int updateByPrimaryKeySelective(CodecCountryDto record) {
        CodecCountry codecCountry = codecCountryMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        Date currentDate = new Date();
        codecCountry.setLastModifiedBy(userId);
        codecCountry.setLastModifiedTime(currentDate);

        int num = codecCountryMapper.updateByPrimaryKeySelective(codecCountry);
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
        int num = codecCountryMapper.deleteByPrimaryKey(id);
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
        return codecCountryMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param List<String> ids
     * @return int
     */
    public int deleteMore(List<String> ids) {
        return codecCountryMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param List<String> ids
     * @return int
     */
    public int deleteMoreLogic(List<String> ids) {
        return codecCountryMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param GridPageRequest 　gridPageRequest
     * @return　GridReturnData<CodecCountryDto>
     */
    public GridReturnData<CodecCountryDto> selectPage(GridCountryPageRequest gridCountryPageRequest) {
        Integer user_id = SecurityUtils.getCurrentUserId();

        GridReturnData<CodecCountryDto> mGridReturnData = new GridReturnData<>();
        List<GridFilterInfo> filterList = gridCountryPageRequest.getFilterList();
        Map map = new HashMap();
        filterList.stream().forEach(gridFilterInfo -> {//封装筛选条件
            if (gridFilterInfo.getFilterKey() != null && gridFilterInfo.getFilterValue() != null) {
                map.put(gridFilterInfo.getFilterKey(), gridFilterInfo.getFilterValue());
            }
        });
        map.put("searchKey", gridCountryPageRequest.getSearchKey());
        //对map中的参数的合法性进行校验
        map.put("identifier", gridCountryPageRequest.getIdentifier());

        String sortMyBatisByString = gridCountryPageRequest.getSortMybatisString();
        PageHelper.startPage(gridCountryPageRequest.getPageNum(), gridCountryPageRequest.getPageSize(), sortMyBatisByString);

        List<CodecCountryDto> list = codecCountryMapper.selectPage(map);

        PageInfo<CodecCountryDto> pageInfo = new PageInfo<>(list);
        mGridReturnData.setPageInfo(pageInfo);

        return mGridReturnData;
    }
}
