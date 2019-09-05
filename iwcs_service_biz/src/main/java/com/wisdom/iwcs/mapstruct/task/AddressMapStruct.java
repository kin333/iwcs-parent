package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.Address;
import com.wisdom.iwcs.domain.task.dto.AddressDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Address and its DTO AddressDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AddressMapStruct extends EntityMapper<AddressDTO, Address> {

}
