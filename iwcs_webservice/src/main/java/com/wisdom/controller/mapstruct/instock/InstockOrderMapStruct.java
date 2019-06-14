package com.wisdom.controller.mapstruct.instock;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.instock.InstockOrder;
import com.wisdom.iwcs.domain.instock.dto.InstockOrderDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InstockOrder and its DTO InstockOrderDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InstockOrderMapStruct extends EntityMapper<InstockOrderDTO, InstockOrder> {

}
