package com.wisdom.iwcs.mapstruct.outstock;

import com.wisdom.iwcs.domain.outstock.OrderActionConfig;
import com.wisdom.iwcs.domain.outstock.dto.OrderActionConfigDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity OrderActionConfig and its DTO OrderActionConfigDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrderActionConfigMapStruct extends EntityMapper<OrderActionConfigDTO, OrderActionConfig> {

}
