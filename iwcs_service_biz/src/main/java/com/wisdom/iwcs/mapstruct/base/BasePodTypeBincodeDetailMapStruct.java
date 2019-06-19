package com.wisdom.iwcs.mapstruct.base;


import com.wisdom.iwcs.domain.base.BasePodTypeBincodeDetail;
import com.wisdom.iwcs.domain.base.dto.BasePodTypeBincodeDetailDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BasePodTypeBincodeDetail and its DTO BasePodTypeBincodeDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BasePodTypeBincodeDetailMapStruct extends EntityMapper<BasePodTypeBincodeDetailDTO, BasePodTypeBincodeDetail> {

}
