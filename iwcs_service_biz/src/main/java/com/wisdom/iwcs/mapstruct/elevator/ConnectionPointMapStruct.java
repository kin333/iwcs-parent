package com.wisdom.iwcs.mapstruct.elevator;

import com.wisdom.iwcs.domain.elevator.ConnectionPoint;
import com.wisdom.iwcs.domain.elevator.dto.ConnectionPointDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity ConnectionPoint and its DTO ConnectionPointDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ConnectionPointMapStruct extends EntityMapper<ConnectionPointDTO, ConnectionPoint> {
}
