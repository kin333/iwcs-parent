package com.wisdom.controller.mapstruct.inv;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.inv.InvTaskBincodeProcess;
import com.wisdom.iwcs.domain.inv.dto.InvTaskBincodeProcessDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InvTaskBincodeProcess and its DTO InvTaskBincodeProcessDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InvTaskBincodeProcessMapStruct extends EntityMapper<InvTaskBincodeProcessDTO, InvTaskBincodeProcess> {
}
