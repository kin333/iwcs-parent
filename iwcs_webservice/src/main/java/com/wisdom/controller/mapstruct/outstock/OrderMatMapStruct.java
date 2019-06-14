package com.wisdom.controller.mapstruct.outstock;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.outstock.OrderMat;
import com.wisdom.iwcs.domain.outstock.dto.OrderMatDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity OrderMat and its DTO OrderMatDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrderMatMapStruct extends EntityMapper<OrderMatDTO, OrderMat> {

}
