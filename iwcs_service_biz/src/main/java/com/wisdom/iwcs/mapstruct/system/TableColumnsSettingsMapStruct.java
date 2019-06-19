package com.wisdom.iwcs.mapstruct.system;

import com.wisdom.iwcs.domain.system.TableColumnsSettings;
import com.wisdom.iwcs.domain.system.dto.TableColumnsSettingsDto;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity TableColumnsSettings and its DTO TableColumnsSettingsDto.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TableColumnsSettingsMapStruct extends EntityMapper<TableColumnsSettingsDto, TableColumnsSettings> {

}
