package com.wisdom.controller.mapstruct.base;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.base.BaseWh;
import com.wisdom.iwcs.domain.base.dto.BaseWhDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseWh and its DTO BaseWhDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseWhMapStruct extends EntityMapper<BaseWhDTO, BaseWh> {

}
