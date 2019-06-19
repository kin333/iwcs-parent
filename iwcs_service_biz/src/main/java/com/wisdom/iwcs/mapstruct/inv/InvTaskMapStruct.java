package com.wisdom.iwcs.mapstruct.inv;

import com.wisdom.iwcs.domain.inv.InvTask;
import com.wisdom.iwcs.domain.inv.dto.InvTaskDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InvTask and its DTO InvTaskDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InvTaskMapStruct extends EntityMapper<InvTaskDTO, InvTask> {

}
