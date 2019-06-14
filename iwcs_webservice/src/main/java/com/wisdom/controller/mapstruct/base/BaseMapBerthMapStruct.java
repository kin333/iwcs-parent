package com.wisdom.controller.mapstruct.base;


import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.dto.BaseMapBerthDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseMapBerth and its DTO BaseMapBerthDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseMapBerthMapStruct extends EntityMapper<BaseMapBerthDTO, BaseMapBerth> {

}
