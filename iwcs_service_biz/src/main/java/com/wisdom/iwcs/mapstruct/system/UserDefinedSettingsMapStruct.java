package com.wisdom.iwcs.mapstruct.system;

import com.wisdom.iwcs.domain.system.UserDefinedSettings;
import com.wisdom.iwcs.domain.system.dto.UserDefinedSettingsDto;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity UserDefinedSettings and its DTO UserDefinedSettingsDto.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserDefinedSettingsMapStruct extends EntityMapper<UserDefinedSettingsDto, UserDefinedSettings> {

}
