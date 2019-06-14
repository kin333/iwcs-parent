package com.wisdom.controller.mapstruct.base;


import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.base.BaseWaMap;
import com.wisdom.iwcs.domain.base.dto.BaseWaMapDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseWaMap and its DTO BaseWaMapDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseWaMapMapStruct extends EntityMapper<BaseWaMapDTO, BaseWaMap> {

}
