package com.wisdom.controller.mapstruct.system;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.system.TableColumnsSettings;
import com.wisdom.iwcs.domain.system.dto.TableColumnsSettingsDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity TableColumnsSettings and its DTO TableColumnsSettingsDto.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TableColumnsSettingsMapStruct extends EntityMapper<TableColumnsSettingsDto, TableColumnsSettings> {

}
