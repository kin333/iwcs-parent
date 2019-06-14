package com.wisdom.controller.mapstruct.base;


import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.base.BasePodBincode;
import com.wisdom.iwcs.domain.base.dto.BasePodBincodeDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BasePodBincode and its DTO BasePodBincodeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BasePodBincodeMapStruct extends EntityMapper<BasePodBincodeDTO, BasePodBincode> {

}
