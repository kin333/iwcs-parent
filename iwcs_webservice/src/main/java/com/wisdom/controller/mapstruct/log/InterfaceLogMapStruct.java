package com.wisdom.controller.mapstruct.log;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.log.InterfaceLog;
import com.wisdom.iwcs.domain.log.dto.InterfaceLogDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity SystemInterfaceLog and its DTO InterfaceLogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InterfaceLogMapStruct extends EntityMapper<InterfaceLogDTO, InterfaceLog> {

}
