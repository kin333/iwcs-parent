package com.wisdom.controller.mapstruct.system;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.system.UserDefinedSettings;
import com.wisdom.iwcs.domain.system.dto.UserDefinedSettingsDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity UserDefinedSettings and its DTO UserDefinedSettingsDto.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserDefinedSettingsMapStruct extends EntityMapper<UserDefinedSettingsDto, UserDefinedSettings> {

}
