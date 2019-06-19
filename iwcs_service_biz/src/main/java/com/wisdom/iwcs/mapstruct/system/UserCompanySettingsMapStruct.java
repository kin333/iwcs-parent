package com.wisdom.iwcs.mapstruct.system;

import com.wisdom.iwcs.domain.system.UserCompanySettings;
import com.wisdom.iwcs.domain.system.dto.UserCompanySettingsDto;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity UserCompanySettings and its DTO UserCompanySettingsDto.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserCompanySettingsMapStruct extends EntityMapper<UserCompanySettingsDto, UserCompanySettings> {

}
