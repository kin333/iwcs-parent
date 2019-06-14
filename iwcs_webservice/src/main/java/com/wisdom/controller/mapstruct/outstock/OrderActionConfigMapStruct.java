package com.wisdom.controller.mapstruct.outstock;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.outstock.OrderActionConfig;
import com.wisdom.iwcs.domain.outstock.dto.OrderActionConfigDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity OrderActionConfig and its DTO OrderActionConfigDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrderActionConfigMapStruct extends EntityMapper<OrderActionConfigDTO, OrderActionConfig> {

}
