package com.wisdom.controller.mapstruct.instock;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.instock.InstockOrderDetail;
import com.wisdom.iwcs.domain.instock.dto.InstockOrderDetailDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InstockOrderDetail and its DTO InstockOrderDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InstockOrderDetailMapStruct extends EntityMapper<InstockOrderDetailDTO, InstockOrderDetail> {

}
