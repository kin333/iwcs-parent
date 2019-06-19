package com.wisdom.iwcs.mapstruct.instock;

import com.wisdom.iwcs.domain.instock.InstockOrderDetail;
import com.wisdom.iwcs.domain.instock.dto.InstockOrderDetailDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InstockOrderDetail and its DTO InstockOrderDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InstockOrderDetailMapStruct extends EntityMapper<InstockOrderDetailDTO, InstockOrderDetail> {

}
