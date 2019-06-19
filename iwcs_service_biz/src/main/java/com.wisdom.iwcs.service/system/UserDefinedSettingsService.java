package com.wisdom.iwcs.service.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.system.UserDefinedSettings;
import com.wisdom.iwcs.domain.system.dto.UserDefinedSettingsDto;
import com.wisdom.iwcs.mapper.system.UserDefinedSettingsMapper;
import com.wisdom.iwcs.mapstruct.system.UserDefinedSettingsMapStruct;
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
public class UserDefinedSettingsService {
    private final Logger logger = LoggerFactory.getLogger(UserDefinedSettingsService.class);

    @Autowired
    UserDefinedSettingsMapper userDefinedSettingsMapper;

    @Autowired
    UserDefinedSettingsMapStruct userDefinedSettingsMapStruct;

    /**
     * 写入记录
     *
     * @param UserDefinedSettingsDto record
     * @return int
     */
    public int insert(UserDefinedSettingsDto record) {
        UserDefinedSettings userDefinedSettings = userDefinedSettingsMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        userDefinedSettings.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        userDefinedSettings.setCreatedTime(new Date());
        userDefinedSettings.setCreatedBy(userId);
        userDefinedSettings.setLastModifiedBy(userId);
        userDefinedSettings.setLastModifiedTime(new Date());

        int num = userDefinedSettingsMapper.insert(userDefinedSettings);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param List<UserDefinedSettingsDto> records
     * @return int
     */
    public int insertBatch(List<UserDefinedSettingsDto> records) {
        List<UserDefinedSettings> recordList = userDefinedSettingsMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = userDefinedSettingsMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param Integer id
     * @return UserDefinedSettingsDto
     */
    public UserDefinedSettingsDto selectByPrimaryKey(Integer id) {

        UserDefinedSettings userDefinedSettings = userDefinedSettingsMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(userDefinedSettings, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return userDefinedSettingsMapStruct.toDto(userDefinedSettings);
    }

    /**
     * 根据字段选择性查询
     *
     * @param UserDefinedSettingsDto record
     * @return List<UserDefinedSettingsDto>
     */
    public List<UserDefinedSettingsDto> selectSelective(UserDefinedSettingsDto record) {
        UserDefinedSettings userDefinedSettings = userDefinedSettingsMapStruct.toEntity(record);

        List<UserDefinedSettings> userDefinedSettingsList = userDefinedSettingsMapper.select(userDefinedSettings);
        return userDefinedSettingsMapStruct.toDto(userDefinedSettingsList);
    }

    /**
     * 根据主键更新
     *
     * @param UserDefinedSettingsDto record
     * @return int
     */
    public int updateByPrimaryKey(UserDefinedSettingsDto record) {
        UserDefinedSettings userDefinedSettings = userDefinedSettingsMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        userDefinedSettings.setCreatedTime(new Date());
        userDefinedSettings.setCreatedBy(userId);
        userDefinedSettings.setLastModifiedBy(userId);
        userDefinedSettings.setLastModifiedTime(new Date());

        int num = userDefinedSettingsMapper.updateByPrimaryKey(userDefinedSettings);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param UserDefinedSettingsDto record
     * @return int
     */
    public int updateByPrimaryKeySelective(UserDefinedSettingsDto record) {
        UserDefinedSettings userDefinedSettings = userDefinedSettingsMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        userDefinedSettings.setCreatedTime(new Date());
        userDefinedSettings.setCreatedBy(userId);
        userDefinedSettings.setLastModifiedBy(userId);
        userDefinedSettings.setLastModifiedTime(new Date());

        int num = userDefinedSettingsMapper.updateByPrimaryKeySelective(userDefinedSettings);
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
        int num = userDefinedSettingsMapper.deleteByPrimaryKey(id);
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
        return userDefinedSettingsMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param List<String> ids
     * @return int
     */
    public int deleteMore(List<String> ids) {
        return userDefinedSettingsMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param List<String> ids
     * @return int
     */
    public int deleteMoreLogic(List<String> ids) {
        return userDefinedSettingsMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param GridPageRequest 　gridPageRequest
     * @return　GridReturnData<UserDefinedSettingsDto>
     */
    public GridReturnData<UserDefinedSettingsDto> selectPage(GridPageRequest gridPageRequest) {
        Integer user_id = SecurityUtils.getCurrentUserId();

        GridReturnData<UserDefinedSettingsDto> mGridReturnData = new GridReturnData<>();
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

        List<UserDefinedSettings> list = userDefinedSettingsMapper.selectPage(map);

        PageInfo<UserDefinedSettingsDto> pageInfo = new PageInfo<>(userDefinedSettingsMapStruct.toDto(list));
        mGridReturnData.setPageInfo(pageInfo);

        return mGridReturnData;
    }

    /**
     * 根据字段选择性查询
     *
     * @param UserDefinedSettingsDto record
     * @return List<UserDefinedSettingsDto>
     */
    public List<UserDefinedSettingsDto> selectByName(UserDefinedSettingsDto userDefinedSettingsDto) {
        UserDefinedSettingsDto record = new UserDefinedSettingsDto();
        record.setSettingsClass(userDefinedSettingsDto.getSettingsClass());
        Integer userId = SecurityUtils.getCurrentUserId();
        record.setCreatedBy(userId);
        List<UserDefinedSettings> userDefinedSettingsList = userDefinedSettingsMapper.selectByName(record);
        return userDefinedSettingsMapStruct.toDto(userDefinedSettingsList);
    }
}
