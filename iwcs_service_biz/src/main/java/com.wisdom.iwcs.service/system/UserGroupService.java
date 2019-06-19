package com.wisdom.iwcs.service.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.codec.GroupTypeEnum;
import com.wisdom.iwcs.domain.system.UserGroup;
import com.wisdom.iwcs.domain.system.dto.UserGroupDto;
import com.wisdom.iwcs.mapper.system.UserGroupMapper;
import com.wisdom.iwcs.mapstruct.system.UserGroupMapStruct;
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
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserGroupService {
    private final Logger logger = LoggerFactory.getLogger(UserGroupService.class);

    @Autowired
    UserGroupMapper userGroupMapper;

    @Autowired
    UserGroupMapStruct userGroupMapStruct;

    /**
     * 写入记录
     *
     * @param UserGroupDto record
     * @return int
     */
    public int insert(UserGroupDto record) {
        UserGroup userGroup = userGroupMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        userGroup.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());

        userGroup.setCreatedTime(new Date());
        userGroup.setCreatedBy(userId);
        userGroup.setLastModifiedBy(userId);
        userGroup.setLastModifiedTime(new Date());

        int num = userGroupMapper.insert(userGroup);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param List<UserGroupDto> records
     * @return int
     */
    public int insertBatch(List<UserGroupDto> records) {
        List<UserGroup> recordList = userGroupMapStruct.toEntity(records);
        recordList = recordList.stream().filter(x -> {

            List<UserGroup> userGroups = userGroupMapper.selectUserIdAndGroupId(x.getUserId(), x.getGroupId());
            return userGroups != null && userGroups.size() <= 0;
        }).collect(Collectors.toList());
        Integer userId = SecurityUtils.getCurrentUserId();

        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = userGroupMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @return UserGroupDto
     */
    public UserGroupDto selectByPrimaryKey(Integer id) {

        UserGroup userGroup = userGroupMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(userGroup, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return userGroupMapStruct.toDto(userGroup);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record
     * @return List<UserGroupDto>
     */
    public List<UserGroupDto> selectSelective(UserGroupDto record) {
        UserGroup userGroup = userGroupMapStruct.toEntity(record);

        List<UserGroup> userGroupList = userGroupMapper.select(userGroup);
        return userGroupMapStruct.toDto(userGroupList);
    }

    /**
     * 根据主键更新
     *
     * @return int
     */
    public int updateByPrimaryKey(UserGroupDto record) {
        UserGroup userGroup = userGroupMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        userGroup.setLastModifiedBy(userId);
        userGroup.setLastModifiedTime(new Date());

        int num = userGroupMapper.updateByPrimaryKey(userGroup);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(UserGroupDto record) {
        UserGroup userGroup = userGroupMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        userGroup.setLastModifiedBy(userId);
        userGroup.setLastModifiedTime(new Date());

        int num = userGroupMapper.updateByPrimaryKeySelective(userGroup);
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
        int num = userGroupMapper.deleteByPrimaryKey(id);
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
        return userGroupMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param List<String> ids
     * @return int
     */
    public int deleteMore(List<String> ids) {
        return userGroupMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param List<String> ids
     * @return int
     */
    public int deleteMoreLogic(List<String> ids) {
        return userGroupMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param GridPageRequest 　gridPageRequest
     * @return　GridReturnData<UserGroupDto>
     */
    public GridReturnData<UserGroupDto> selectPage(GridPageRequest gridPageRequest) {
        Integer user_id = SecurityUtils.getCurrentUserId();

        GridReturnData<UserGroupDto> mGridReturnData = new GridReturnData<>();
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

        List<UserGroup> list = userGroupMapper.selectPage(map);

        PageInfo<UserGroupDto> pageInfo = new PageInfo<>(userGroupMapStruct.toDto(list));
        mGridReturnData.setPageInfo(pageInfo);

        return mGridReturnData;
    }

    public Result getGroupUsers(UserGroupDto userGroupDto) {
        Map map = new HashMap();
        map.put("groupId", userGroupDto.getGroupId());
        List<UserGroupDto> list = userGroupMapper.getGroupUsers(map);
        return new Result(list);
    }

    //----------------------------------------------------------------------------------------------------------------

    /**
     * 获取是组长的工作组
     *
     * @param userId
     * @return
     */
    public List<UserGroupDto> getManagedGroupByUserId(Integer userId, Integer groupType) {
        List<UserGroup> userGroups = userGroupMapper.getManagedGroupByUserId(userId, groupType);
        return userGroupMapStruct.toDto(userGroups);
    }

    /*public List<Integer> getManagedGroupIdByUserId(Integer userId) {
        List<Integer> userGroupIds = userGroupMapper.getManagedGroupIdByUserId(userId);
        return userGroupIds;
    }*/

    /**
     * 获取可看到的揽货工作组的人员列表
     *
     * @param userId
     * @return
     */
    public List<Integer> getCanvassUserIdsInManagedGroup(Integer userId, Integer companyId) {
        List<Integer> userGroupIds = userGroupMapper.getManagedGroupIdByUserId(userId, GroupTypeEnum.CanvassGroup.getTypeId(), companyId);
        List<Integer> userIds = userGroupMapper.getUserIdListInManagedGroup(userGroupIds);
        return userIds;
    }

    /**
     * 获取可看到的制单工作组的人员列表
     *
     * @param userId
     * @return
     */
    public List<Integer> getOperUserIdsInManagedGroup(Integer userId, Integer companyId) {
        List<Integer> userGroupIds = userGroupMapper.getManagedGroupIdByUserId(userId, GroupTypeEnum.OperGroup.getTypeId(), companyId);
        List<Integer> userIds = userGroupMapper.getUserIdListInManagedGroup(userGroupIds);
        return userIds;
    }

    public List<Integer> getUserIdsFromBusinessUserGroupAndOrganizeById(Integer currentUserId, Integer companyId) {
        List<Integer> userGroupIds = userGroupMapper.getManagedGroupIdByUserId(currentUserId, GroupTypeEnum.BUSINESS.getTypeId(), companyId);
        List<Integer> userIds = userGroupMapper.getUserIdListInManagedGroup(userGroupIds);
        return userIds;
    }
}
