package com.wisdom.controller.mapstruct.base;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.base.BaseBincodeDetail;
import com.wisdom.iwcs.domain.base.dto.BaseBincodeDetailDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseBincodeDetail and its DTO BaseBincodeDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseBincodeDetailMapStruct extends EntityMapper<BaseBincodeDetailDTO, BaseBincodeDetail> {

}
