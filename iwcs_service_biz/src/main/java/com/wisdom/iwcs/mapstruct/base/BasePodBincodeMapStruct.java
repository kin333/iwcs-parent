package com.wisdom.iwcs.mapstruct.base;


import com.wisdom.iwcs.domain.base.BasePodBincode;
import com.wisdom.iwcs.domain.base.dto.BasePodBincodeDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BasePodBincode and its DTO BasePodBincodeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BasePodBincodeMapStruct extends EntityMapper<BasePodBincodeDTO, BasePodBincode> {

}
