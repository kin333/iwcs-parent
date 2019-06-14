package com.wisdom.service.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.system.TableColumnsSettingsMapStruct;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.system.TableColumnsSettings;
import com.wisdom.iwcs.domain.system.dto.TableColumnsSettingsDto;
import com.wisdom.iwcs.mapper.system.TableColumnsSettingsMapper;
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
public class TableColumnsSettingsService {
    private final Logger logger = LoggerFactory.getLogger(TableColumnsSettingsService.class);

    @Autowired
    TableColumnsSettingsMapper tableColumnsSettingsMapper;

    @Autowired
    TableColumnsSettingsMapStruct tableColumnsSettingsMapStruct;

    /**
     * 写入记录
     *
     * @param TableColumnsSettingsDto record
     * @return int
     */
    public int insert(TableColumnsSettingsDto record) {
        Integer userId = SecurityUtils.getCurrentUserId();
        TableColumnsSettingsDto tableColumnsSettingsDtoOld = new TableColumnsSettingsDto();
        tableColumnsSettingsDtoOld.setTableName(record.getTableName());
        tableColumnsSettingsDtoOld.setCreatedBy(userId);
        List<TableColumnsSettingsDto> tableColumnsSettingsDtoList = selectSelective(tableColumnsSettingsDtoOld);
        int num;
        if (tableColumnsSettingsDtoList.size() > 0) {
            record.setId(tableColumnsSettingsDtoList.get(0).getId());
            num = updateByPrimaryKeySelective(record);
        } else {
            TableColumnsSettings tableColumnsSettings = tableColumnsSettingsMapStruct.toEntity(record);
            tableColumnsSettings.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            tableColumnsSettings.setCreatedTime(new Date());
            tableColumnsSettings.setCreatedBy(userId);
            num = tableColumnsSettingsMapper.insert(tableColumnsSettings);
            Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        }
        return num;
    }

    /**
     * 批量写入记录
     *
     * @param List<TableColumnsSettingsDto> records
     * @return int
     */
    public int insertBatch(List<TableColumnsSettingsDto> records) {
        List<TableColumnsSettings> recordList = tableColumnsSettingsMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = tableColumnsSettingsMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param Integer id
     * @return TableColumnsSettingsDto
     */
    public TableColumnsSettingsDto selectByPrimaryKey(Integer id) {

        TableColumnsSettings tableColumnsSettings = tableColumnsSettingsMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(tableColumnsSettings, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return tableColumnsSettingsMapStruct.toDto(tableColumnsSettings);
    }

    /**
     * 根据字段选择性查询
     *
     * @param TableColumnsSettingsDto record
     * @return List<TableColumnsSettingsDto>
     */
    public List<TableColumnsSettingsDto> selectSelective(TableColumnsSettingsDto record) {
        TableColumnsSettings tableColumnsSettings = tableColumnsSettingsMapStruct.toEntity(record);
        Integer userId = SecurityUtils.getCurrentUserId();
        tableColumnsSettings.setCreatedBy(userId);
        List<TableColumnsSettings> tableColumnsSettingsList = tableColumnsSettingsMapper.select(tableColumnsSettings);
        return tableColumnsSettingsMapStruct.toDto(tableColumnsSettingsList);
    }

    /**
     * 根据字段选择性查询
     *
     * @param TableColumnsSettingsDto record
     * @return List<TableColumnsSettingsDto>
     */
    public List<TableColumnsSettingsDto> selectLayout(TableColumnsSettingsDto record) {
        TableColumnsSettings tableColumnsSettings = tableColumnsSettingsMapStruct.toEntity(record);
        List<TableColumnsSettings> tableColumnsSettingsList = tableColumnsSettingsMapper.select(tableColumnsSettings);
        return tableColumnsSettingsMapStruct.toDto(tableColumnsSettingsList);
    }

    /**
     * 根据主键更新
     *
     * @param TableColumnsSettingsDto record
     * @return int
     */
    public int updateByPrimaryKey(TableColumnsSettingsDto record) {
        TableColumnsSettings tableColumnsSettings = tableColumnsSettingsMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        tableColumnsSettings.setLastModifiedBy(userId);
        tableColumnsSettings.setLastModifiedTime(new Date());

        int num = tableColumnsSettingsMapper.updateByPrimaryKey(tableColumnsSettings);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param TableColumnsSettingsDto record
     * @return int
     */
    public int updateByPrimaryKeySelective(TableColumnsSettingsDto record) {
        TableColumnsSettings tableColumnsSettings = tableColumnsSettingsMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        tableColumnsSettings.setLastModifiedBy(userId);
        tableColumnsSettings.setLastModifiedTime(new Date());

        int num = tableColumnsSettingsMapper.updateByPrimaryKeySelective(tableColumnsSettings);
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
        int num = tableColumnsSettingsMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键逻辑删除记录
     *
     * @param String tableName
     * @return int
     */
    public int deleteByTableName(TableColumnsSettingsDto tableColumnsSettingsDto) {
        Integer userId = SecurityUtils.getCurrentUserId();
        tableColumnsSettingsDto.setCreatedBy(userId);
        return tableColumnsSettingsMapper.deleteByTableName(tableColumnsSettingsDto);
    }


    /**
     * 删除布局
     *
     * @param String tableName
     * @return int
     */
    public int deleteByLayout(TableColumnsSettingsDto tableColumnsSettingsDto) {
        Integer userId = SecurityUtils.getCurrentUserId();
        tableColumnsSettingsDto.setCreatedBy(userId);
        return tableColumnsSettingsMapper.deleteByLayout(tableColumnsSettingsDto);
    }

    /**
     * 根据主键逻辑删除记录
     *
     * @param Integer id
     * @return int
     */
    public int deleteLogicByPrimaryKey(Integer id) {
        return tableColumnsSettingsMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param List<String> ids
     * @return int
     */
    public int deleteMore(List<String> ids) {
        return tableColumnsSettingsMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param List<String> ids
     * @return int
     */
    public int deleteMoreLogic(List<String> ids) {
        return tableColumnsSettingsMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param GridPageRequest 　gridPageRequest
     * @return　GridReturnData<TableColumnsSettingsDto>
     */
    public GridReturnData<TableColumnsSettingsDto> selectPage(GridPageRequest gridPageRequest) {
        Integer user_id = SecurityUtils.getCurrentUserId();

        GridReturnData<TableColumnsSettingsDto> mGridReturnData = new GridReturnData<>();
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

        List<TableColumnsSettings> list = tableColumnsSettingsMapper.selectPage(map);

        PageInfo<TableColumnsSettingsDto> pageInfo = new PageInfo<>(tableColumnsSettingsMapStruct.toDto(list));
        mGridReturnData.setPageInfo(pageInfo);

        return mGridReturnData;
    }
}
