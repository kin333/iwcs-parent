package com.wisdom.iwcs.mapstruct.base;


import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.dto.BaseMapBerthDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseMapBerth and its DTO BaseMapBerthDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseMapBerthMapStruct extends EntityMapper<BaseMapBerthDTO, BaseMapBerth> {

}
