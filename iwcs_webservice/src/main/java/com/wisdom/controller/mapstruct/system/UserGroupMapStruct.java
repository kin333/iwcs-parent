package com.wisdom.controller.mapstruct.system;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.system.UserGroup;
import com.wisdom.iwcs.domain.system.dto.UserGroupDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity UserGroup and its DTO UserGroupDto.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserGroupMapStruct extends EntityMapper<UserGroupDto, UserGroup> {

}
