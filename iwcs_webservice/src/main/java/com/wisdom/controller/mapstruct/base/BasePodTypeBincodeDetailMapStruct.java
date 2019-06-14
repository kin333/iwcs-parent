package com.wisdom.controller.mapstruct.base;


import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.base.BasePodTypeBincodeDetail;
import com.wisdom.iwcs.domain.base.dto.BasePodTypeBincodeDetailDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BasePodTypeBincodeDetail and its DTO BasePodTypeBincodeDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BasePodTypeBincodeDetailMapStruct extends EntityMapper<BasePodTypeBincodeDetailDTO, BasePodTypeBincodeDetail> {

}
