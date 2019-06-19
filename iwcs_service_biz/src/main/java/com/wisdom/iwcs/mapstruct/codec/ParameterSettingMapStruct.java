package com.wisdom.iwcs.mapstruct.codec;

import com.wisdom.iwcs.domain.codec.ParameterSetting;
import com.wisdom.iwcs.domain.codec.dto.ParameterSettingDto;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity ParameterSetting and its DTO ParameterSettingDto.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ParameterSettingMapStruct extends EntityMapper<ParameterSettingDto, ParameterSetting> {

}
