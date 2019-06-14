package com.wisdom.controller.mapstruct.base;


import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.base.BaseMap;
import com.wisdom.iwcs.domain.base.dto.BaseMapDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseMap and its DTO BaseMapDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseMapMapStruct extends EntityMapper<BaseMapDTO, BaseMap> {

}
