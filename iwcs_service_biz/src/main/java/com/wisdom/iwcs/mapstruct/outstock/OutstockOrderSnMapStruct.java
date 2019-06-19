package com.wisdom.iwcs.mapstruct.outstock;

import com.wisdom.iwcs.domain.outstock.OutstockOrderSn;
import com.wisdom.iwcs.domain.outstock.dto.OutstockOrderSnDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity OutstockOrderSn and its DTO OutstockOrderSnDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OutstockOrderSnMapStruct extends EntityMapper<OutstockOrderSnDTO, OutstockOrderSn> {

}
