package com.wisdom.iwcs.service.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.system.UserCompanySettings;
import com.wisdom.iwcs.domain.system.dto.UserCompanySettingsDto;
import com.wisdom.iwcs.mapper.system.UserCompanySettingsMapper;
import com.wisdom.iwcs.mapstruct.system.UserCompanySettingsMapStruct;
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
public class UserCompanySettingsService {
    private final Logger logger = LoggerFactory.getLogger(UserCompanySettingsService.class);

    @Autowired
    UserCompanySettingsMapper userCompanySettingsMapper;

    @Autowired
    UserCompanySettingsMapStruct userCompanySettingsMapStruct;

    /**
     * 写入记录
     *
     * @param UserCompanySettingsDto record
     * @return int
     */
    public int insert(UserCompanySettingsDto record) {
        UserCompanySettings userCompanySettings = userCompanySettingsMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        userCompanySettings.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        userCompanySettings.setCreatedTime(new Date());
        userCompanySettings.setCreatedBy(userId);
        userCompanySettings.setLastModifiedBy(userId);
        userCompanySettings.setLastModifiedTime(new Date());

        int num = userCompanySettingsMapper.insert(userCompanySettings);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param List<UserCompanySettingsDto> records
     * @return int
     */
    public int insertBatch(List<UserCompanySettingsDto> records) {
        List<UserCompanySettings> recordList = userCompanySettingsMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = userCompanySettingsMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param Integer id
     * @return UserCompanySettingsDto
     */
    public UserCompanySettingsDto selectByPrimaryKey(Integer id) {

        UserCompanySettings userCompanySettings = userCompanySettingsMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(userCompanySettings, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return userCompanySettingsMapStruct.toDto(userCompanySettings);
    }

    /**
     * 根据字段选择性查询
     *
     * @param UserCompanySettingsDto record
     * @return List<UserCompanySettingsDto>
     */
    public List<UserCompanySettingsDto> selectSelective(UserCompanySettingsDto record) {
        UserCompanySettings userCompanySettings = userCompanySettingsMapStruct.toEntity(record);

        List<UserCompanySettings> userCompanySettingsList = userCompanySettingsMapper.select(userCompanySettings);
        return userCompanySettingsMapStruct.toDto(userCompanySettingsList);
    }

    /**
     * 根据主键更新
     *
     * @param UserCompanySettingsDto record
     * @return int
     */
    public int updateByPrimaryKey(UserCompanySettingsDto record) {
        UserCompanySettings userCompanySettings = userCompanySettingsMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        userCompanySettings.setLastModifiedBy(userId);
        userCompanySettings.setLastModifiedTime(new Date());

        int num = userCompanySettingsMapper.updateByPrimaryKey(userCompanySettings);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param UserCompanySettingsDto record
     * @return int
     */
    public int updateByPrimaryKeySelective(UserCompanySettingsDto record) {
        UserCompanySettings userCompanySettings = userCompanySettingsMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        userCompanySettings.setLastModifiedBy(userId);
        userCompanySettings.setLastModifiedTime(new Date());

        int num = userCompanySettingsMapper.updateByPrimaryKeySelective(userCompanySettings);
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
        int num = userCompanySettingsMapper.deleteByPrimaryKey(id);
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
        return userCompanySettingsMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param List<String> ids
     * @return int
     */
    public int deleteMore(List<String> ids) {
        return userCompanySettingsMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param List<String> ids
     * @return int
     */
    public int deleteMoreLogic(List<String> ids) {
        return userCompanySettingsMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param GridPageRequest 　gridPageRequest
     * @return　GridReturnData<UserCompanySettingsDto>
     */
    public GridReturnData<UserCompanySettingsDto> selectPage(GridPageRequest gridPageRequest) {
        Integer user_id = SecurityUtils.getCurrentUserId();

        GridReturnData<UserCompanySettingsDto> mGridReturnData = new GridReturnData<>();
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

        List<UserCompanySettings> list = userCompanySettingsMapper.selectPage(map);

        PageInfo<UserCompanySettingsDto> pageInfo = new PageInfo<>(userCompanySettingsMapStruct.toDto(list));
        mGridReturnData.setPageInfo(pageInfo);

        return mGridReturnData;
    }
}
