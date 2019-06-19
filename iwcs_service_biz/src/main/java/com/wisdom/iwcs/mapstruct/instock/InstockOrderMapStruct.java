package com.wisdom.iwcs.mapstruct.instock;

import com.wisdom.iwcs.domain.instock.InstockOrder;
import com.wisdom.iwcs.domain.instock.dto.InstockOrderDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InstockOrder and its DTO InstockOrderDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InstockOrderMapStruct extends EntityMapper<InstockOrderDTO, InstockOrder> {

}
