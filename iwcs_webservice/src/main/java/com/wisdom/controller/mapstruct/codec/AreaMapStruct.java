package com.wisdom.controller.mapstruct.codec;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.codec.Area;
import com.wisdom.iwcs.domain.codec.dto.AreaDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Area and its DTO AreaDto.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AreaMapStruct extends EntityMapper<AreaDto, Area> {

}
