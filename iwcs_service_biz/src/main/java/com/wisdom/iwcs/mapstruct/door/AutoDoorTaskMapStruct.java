package com.wisdom.iwcs.mapstruct.door;

import com.wisdom.iwcs.domain.door.AutoDoorTask;
import com.wisdom.iwcs.domain.door.dto.AutoDoorTaskDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity AutoDoorTask and its DTO AutoDoorTaskDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AutoDoorTaskMapStruct extends EntityMapper<AutoDoorTaskDTO, AutoDoorTask> {

}
