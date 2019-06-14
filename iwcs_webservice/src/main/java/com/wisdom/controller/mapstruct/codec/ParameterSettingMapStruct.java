package com.wisdom.controller.mapstruct.codec;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.codec.ParameterSetting;
import com.wisdom.iwcs.domain.codec.dto.ParameterSettingDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity ParameterSetting and its DTO ParameterSettingDto.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ParameterSettingMapStruct extends EntityMapper<ParameterSettingDto, ParameterSetting> {

}
