package com.wisdom.iwcs.mapper.system;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.system.Group;
import com.wisdom.iwcs.domain.system.dto.GroupDto;

import java.util.List;
import java.util.Map;

public interface GroupMapper extends MyMapperAndIds<Group>, DeleteLogicMapper<Group> {
    List<GroupDto> selectPage(Map map);
}