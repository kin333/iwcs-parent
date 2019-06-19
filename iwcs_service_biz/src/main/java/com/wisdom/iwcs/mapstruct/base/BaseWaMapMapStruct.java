package com.wisdom.iwcs.mapstruct.base;


import com.wisdom.iwcs.domain.base.BaseWaMap;
import com.wisdom.iwcs.domain.base.dto.BaseWaMapDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseWaMap and its DTO BaseWaMapDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseWaMapMapStruct extends EntityMapper<BaseWaMapDTO, BaseWaMap> {

}
