package com.wisdom.iwcs.mapstruct.inv;

import com.wisdom.iwcs.domain.inv.InvTaskBincodeProcess;
import com.wisdom.iwcs.domain.inv.dto.InvTaskBincodeProcessDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InvTaskBincodeProcess and its DTO InvTaskBincodeProcessDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InvTaskBincodeProcessMapStruct extends EntityMapper<InvTaskBincodeProcessDTO, InvTaskBincodeProcess> {
}
