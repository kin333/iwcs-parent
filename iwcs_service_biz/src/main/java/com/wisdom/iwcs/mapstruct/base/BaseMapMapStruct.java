package com.wisdom.iwcs.mapstruct.base;


import com.wisdom.iwcs.domain.base.BaseMap;
import com.wisdom.iwcs.domain.base.dto.BaseMapDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseMap and its DTO BaseMapDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseMapMapStruct extends EntityMapper<BaseMapDTO, BaseMap> {

}
