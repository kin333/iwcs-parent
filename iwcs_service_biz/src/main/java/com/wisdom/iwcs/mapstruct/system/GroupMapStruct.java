package com.wisdom.iwcs.mapstruct.system;

import com.wisdom.iwcs.domain.system.Group;
import com.wisdom.iwcs.domain.system.dto.GroupDto;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Group and its DTO GroupDto.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GroupMapStruct extends EntityMapper<GroupDto, Group> {

}
