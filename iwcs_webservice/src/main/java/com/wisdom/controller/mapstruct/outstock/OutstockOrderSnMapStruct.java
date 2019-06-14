package com.wisdom.controller.mapstruct.outstock;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.outstock.OutstockOrderSn;
import com.wisdom.iwcs.domain.outstock.dto.OutstockOrderSnDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity OutstockOrderSn and its DTO OutstockOrderSnDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OutstockOrderSnMapStruct extends EntityMapper<OutstockOrderSnDTO, OutstockOrderSn> {

}
