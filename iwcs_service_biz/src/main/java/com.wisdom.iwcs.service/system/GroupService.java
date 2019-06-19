package com.wisdom.iwcs.service.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.system.Group;
import com.wisdom.iwcs.domain.system.dto.GroupDto;
import com.wisdom.iwcs.mapper.system.GroupMapper;
import com.wisdom.iwcs.mapstruct.system.GroupMapStruct;
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
public class GroupService {
    private final Logger logger = LoggerFactory.getLogger(GroupService.class);

    @Autowired
    GroupMapper groupMapper;

    @Autowired
    GroupMapStruct groupMapStruct;

    /**
     * 写入记录
     *
     * @return int
     */
    public int insert(GroupDto record) {
        Group group = groupMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        group.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        group.setCreatedTime(new Date());
        group.setCreatedBy(userId);
        group.setLastModifiedBy(userId);
        group.setLastModifiedTime(new Date());

        int num = groupMapper.insert(group);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @return int
     */
    public int insertBatch(List<GroupDto> records) {
        List<Group> recordList = groupMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = groupMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @return GroupDto
     */
    public GroupDto selectByPrimaryKey(Integer id) {

        Group group = groupMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(group, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return groupMapStruct.toDto(group);
    }

    /**
     * 根据字段选择性查询
     *
     * @return List<GroupDto>
     */
    public List<GroupDto> selectSelective(GroupDto record) {
        Group group = groupMapStruct.toEntity(record);

        List<Group> groupList = groupMapper.select(group);
        return groupMapStruct.toDto(groupList);
    }

    /**
     * 根据主键更新
     *
     * @return int
     */
    public int updateByPrimaryKey(GroupDto record) {
        Group group = groupMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        group.setLastModifiedBy(userId);
        group.setLastModifiedTime(new Date());

        int num = groupMapper.updateByPrimaryKey(group);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(GroupDto record) {
        Group group = groupMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        group.setLastModifiedBy(userId);
        group.setLastModifiedTime(new Date());

        int num = groupMapper.updateByPrimaryKeySelective(group);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     * @return int
     */
    public int deleteByPrimaryKey(Integer id) {
        int num = groupMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键逻辑删除记录
     *
     * @return int
     */
    public int deleteLogicByPrimaryKey(Integer id) {
        return groupMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @return int
     */
    public int deleteMore(List<String> ids) {
        return groupMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @return int
     */
    public int deleteMoreLogic(List<String> ids) {
        return groupMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @return　GridReturnData<GroupDto>
     */
    public GridReturnData<GroupDto> selectPage(GridPageRequest gridPageRequest) {
        Integer user_id = SecurityUtils.getCurrentUserId();

        GridReturnData<GroupDto> mGridReturnData = new GridReturnData<>();
        List<GridFilterInfo> filterList = gridPageRequest.getFilterList();
        Map map = new HashMap();
        filterList.stream().forEach(gridFilterInfo -> {//封装筛选条件
            if (gridFilterInfo.getFilterKey() != null && gridFilterInfo.getFilterValue() != null) {
                map.put(gridFilterInfo.getFilterKey(), gridFilterInfo.getFilterValue());
            }
        });
        map.put("searchKey", gridPageRequest.getSearchKey());
        //对map中的参数的合法性进行校验
        if (!SecurityUtils.isSuperAdmin()) {

            map.put("companyId", SecurityUtils.getCurrentCompanyId());
        }

        String sortMyBatisByString = gridPageRequest.getSortMybatisString();
        PageHelper.startPage(gridPageRequest.getPageNum(), gridPageRequest.getPageSize(), sortMyBatisByString);

        List<GroupDto> list = groupMapper.selectPage(map);

        PageInfo<GroupDto> pageInfo = new PageInfo<>(list);
        mGridReturnData.setPageInfo(pageInfo);

        return mGridReturnData;
    }
}
