package com.wisdom.iwcs.mapstruct.elevator;

import com.wisdom.iwcs.domain.elevator.Elevator;
import com.wisdom.iwcs.domain.elevator.dto.ElevatorDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {})
public interface ElevatorMapStruct extends EntityMapper<ElevatorDTO, Elevator> {

}
