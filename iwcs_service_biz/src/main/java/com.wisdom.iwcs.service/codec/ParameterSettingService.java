package com.wisdom.iwcs.service.codec;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.codec.ParameterSetting;
import com.wisdom.iwcs.domain.codec.dto.ParameterSettingDto;
import com.wisdom.iwcs.mapper.codec.ParameterSettingMapper;
import com.wisdom.iwcs.mapstruct.codec.ParameterSettingMapStruct;
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
public class ParameterSettingService {
    private final Logger logger = LoggerFactory.getLogger(ParameterSettingService.class);

    @Autowired
    ParameterSettingMapper parameterSettingMapper;

    @Autowired
    ParameterSettingMapStruct parameterSettingMapStruct;

    /**
     * 写入记录
     *
     * @param ParameterSettingDto record
     * @return int
     */
    public int insert(ParameterSettingDto record) {
        ParameterSetting parameterSetting = parameterSettingMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        parameterSetting.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        parameterSetting.setCreatedTime(new Date());
        parameterSetting.setCreatedBy(userId);
        parameterSetting.setLastModifiedBy(userId);
        parameterSetting.setLastModifiedTime(new Date());

        int num = parameterSettingMapper.insert(parameterSetting);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param List<ParameterSettingDto> records
     * @return int
     */
    public int insertBatch(List<ParameterSettingDto> records) {
        List<ParameterSetting> recordList = parameterSettingMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = parameterSettingMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param Integer id
     * @return ParameterSettingDto
     */
    public ParameterSettingDto selectByPrimaryKey(Integer id) {

        ParameterSetting parameterSetting = parameterSettingMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(parameterSetting, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return parameterSettingMapStruct.toDto(parameterSetting);
    }

    /**
     * 根据字段选择性查询
     *
     * @param ParameterSettingDto record
     * @return List<ParameterSettingDto>
     */
    public List<ParameterSettingDto> selectSelective(ParameterSettingDto record) {
        ParameterSetting parameterSetting = parameterSettingMapStruct.toEntity(record);

        List<ParameterSetting> parameterSettingList = parameterSettingMapper.select(parameterSetting);
        return parameterSettingMapStruct.toDto(parameterSettingList);
    }

    /**
     * 根据主键更新
     *
     * @param ParameterSettingDto record
     * @return int
     */
    public int updateByPrimaryKey(ParameterSettingDto record) {
        ParameterSetting parameterSetting = parameterSettingMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        parameterSetting.setLastModifiedBy(userId);
        parameterSetting.setLastModifiedTime(new Date());

        int num = parameterSettingMapper.updateByPrimaryKey(parameterSetting);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param ParameterSettingDto record
     * @return int
     */
    public int updateByPrimaryKeySelective(ParameterSettingDto record) {
        ParameterSetting parameterSetting = parameterSettingMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        parameterSetting.setLastModifiedBy(userId);
        parameterSetting.setLastModifiedTime(new Date());

        int num = parameterSettingMapper.updateByPrimaryKeySelective(parameterSetting);
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
        int num = parameterSettingMapper.deleteByPrimaryKey(id);
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
        return parameterSettingMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param List<String> ids
     * @return int
     */
    public int deleteMore(List<String> ids) {
        return parameterSettingMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param List<String> ids
     * @return int
     */
    public int deleteMoreLogic(List<String> ids) {
        return parameterSettingMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param GridPageRequest 　gridPageRequest
     * @return　GridReturnData<ParameterSettingDto>
     */
    public GridReturnData<ParameterSettingDto> selectPage(GridPageRequest gridPageRequest) {
        Integer user_id = SecurityUtils.getCurrentUserId();

        GridReturnData<ParameterSettingDto> mGridReturnData = new GridReturnData<>();
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

        List<ParameterSetting> list = parameterSettingMapper.selectPage(map);

        PageInfo<ParameterSettingDto> pageInfo = new PageInfo<>(parameterSettingMapStruct.toDto(list));
        mGridReturnData.setPageInfo(pageInfo);

        return mGridReturnData;
    }
}
