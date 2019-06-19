package com.wisdom.iwcs.mapstruct.system;

import com.wisdom.iwcs.domain.system.UserGroup;
import com.wisdom.iwcs.domain.system.dto.UserGroupDto;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity UserGroup and its DTO UserGroupDto.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserGroupMapStruct extends EntityMapper<UserGroupDto, UserGroup> {

}
