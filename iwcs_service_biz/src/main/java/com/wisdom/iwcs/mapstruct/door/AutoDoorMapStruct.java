package com.wisdom.iwcs.mapstruct.door;


import com.wisdom.iwcs.domain.door.AutoDoor;
import com.wisdom.iwcs.domain.door.dto.AutoDoorDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity AutoDoor and its DTO AutoDoorDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AutoDoorMapStruct extends EntityMapper<AutoDoorDTO, AutoDoor> {

}
