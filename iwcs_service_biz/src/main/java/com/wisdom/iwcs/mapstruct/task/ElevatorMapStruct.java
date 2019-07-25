package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.Elevator;
import com.wisdom.iwcs.domain.task.dto.ElevatorDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Elevator and its DTO ElevatorDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ElevatorMapStruct extends EntityMapper<ElevatorDTO, Elevator> {

}
