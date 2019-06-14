package com.wisdom.controller.mapstruct.inv;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.inv.InvTask;
import com.wisdom.iwcs.domain.inv.dto.InvTaskDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InvTask and its DTO InvTaskDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InvTaskMapStruct extends EntityMapper<InvTaskDTO, InvTask> {

}
