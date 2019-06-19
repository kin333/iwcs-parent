package com.wisdom.iwcs.mapstruct.outstock;

import com.wisdom.iwcs.domain.outstock.OrderMat;
import com.wisdom.iwcs.domain.outstock.dto.OrderMatDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity OrderMat and its DTO OrderMatDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrderMatMapStruct extends EntityMapper<OrderMatDTO, OrderMat> {

}
