package com.wisdom.iwcs.mapstruct.base;

import com.wisdom.iwcs.domain.base.BaseBincodeDetail;
import com.wisdom.iwcs.domain.base.dto.BaseBincodeDetailDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseBincodeDetail and its DTO BaseBincodeDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseBincodeDetailMapStruct extends EntityMapper<BaseBincodeDetailDTO, BaseBincodeDetail> {

}
